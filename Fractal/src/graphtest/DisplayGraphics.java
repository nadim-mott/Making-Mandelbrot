package graphtest;

import java.awt.*;  
import java.util.Random;
import javax.swing.JFrame;

public class DisplayGraphics extends Canvas {  

    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static final int MAX_ITER = 100;

    private static final double RE_MIN = -3;
    private static final double RE_MAX = 3;
    private static final double IM_MIN = -2;
    private static final double IM_MAX = 2;

    // Set to -1 to use full grid rendering, or set a number for randomized sampling
    private static final int RANDOMIZED_ITERATIONS = 9000;

    private int mandelbrot(Complex c, int maxIter) {
        Complex z = new Complex(0, 0);
        int iter = 0;
        while (z.mod() <= 2 && iter < maxIter) {
            z = z.times(z).plus(c);
            iter++;
        }
        return iter;
    }

    // Maps a real coordinate to a pixel location
    private int mapToPixel(double value, double min, double max, int size) {
        return (int) ((value - min) / (max - min) * size);
    }

    @Override
    public void paint(Graphics g) {
        int insideCount = 0;

        if (RANDOMIZED_ITERATIONS < 0) {
            // Deterministic full grid
            for (int y = 0; y < HEIGHT; y++) {
                for (int x = 0; x < WIDTH; x++) {
                    double real = RE_MIN + (RE_MAX - RE_MIN) * x / WIDTH;
                    double imag = IM_MIN + (IM_MAX - IM_MIN) * y / HEIGHT;
                    Complex c = new Complex(real, imag);

                    int iter = mandelbrot(c, MAX_ITER);

                    if (iter == MAX_ITER) {
                        g.setColor(Color.BLACK);
                        insideCount++;
                    } else {
                        float hue = iter / (float) MAX_ITER;
                        g.setColor(Color.getHSBColor(hue, 1.0f, 1.0f));
                    }

                    g.fillRect(x, y, 1, 1);
                }
            }
        } else {
            // Randomized sampling
            Random rand = new Random();
            for (int i = 0; i < RANDOMIZED_ITERATIONS; i++) {
                double real = RE_MIN + (RE_MAX - RE_MIN) * rand.nextDouble();
                double imag = IM_MIN + (IM_MAX - IM_MIN) * rand.nextDouble();
                Complex c = new Complex(real, imag);
                int iter = mandelbrot(c, MAX_ITER);

                int x = mapToPixel(real, RE_MIN, RE_MAX, WIDTH);
                int y = mapToPixel(imag, IM_MIN, IM_MAX, HEIGHT);

                if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) continue;

                if (iter == MAX_ITER) {
                    g.setColor(Color.BLACK);
                    insideCount++;
                } else {
                    float hue = iter / (float) MAX_ITER;
                    g.setColor(Color.getHSBColor(hue, 1.0f, 1.0f));
                }

                g.fillRect(x, y, 1, 1);
            }
        }

        // Area estimate (fraction of points in Mandelbrot set * total complex plane area)
        double areaTotal = (RE_MAX - RE_MIN) * (IM_MAX - IM_MIN);
        double numberOfPoints = (RANDOMIZED_ITERATIONS < 0) ? WIDTH * HEIGHT : RANDOMIZED_ITERATIONS;
        double pixelFraction = insideCount / numberOfPoints;
        double areaEstimate = pixelFraction * areaTotal;

        g.setColor(Color.BLACK);
        g.drawString("Made by Nadim A. Mottu", 10, HEIGHT - 30);
        g.drawString(String.format("Approximate Mandelbrot Area: %.4f", areaEstimate), 10, HEIGHT - 10);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Mandelbrot Set Viewer");
        DisplayGraphics m = new DisplayGraphics();
        f.add(m);
        f.setSize(WIDTH, HEIGHT);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
