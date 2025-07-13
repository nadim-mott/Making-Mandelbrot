package graphtest;

import java.awt.*;  
import javax.swing.JFrame;  

public class JuliaSetDisplay extends Canvas {  

    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static final int MAX_ITER = 100;

    // Complex constant c for the Julia set
    private static final Complex C = new Complex(-0.8, 0.156);

    // Real and imaginary bounds for the complex plane
    private static final double RE_MIN = -2;
    private static final double RE_MAX = 2;
    private static final double IM_MIN = -1.5;
    private static final double IM_MAX = 1.5;

    private int juliaIterations(Complex z, int maxIter) {
        int iter = 0;
        while (z.mod() <= 2 && iter < maxIter) {
            z = z.times(z).plus(C);
            iter++;
        }
        return iter;
    }

    private double map(int pixel, int size, double min, double max) {
        return min + (max - min) * pixel / size;
    }

    @Override
    public void paint(Graphics g) {
        int insideCount = 0;

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                double real = map(x, WIDTH, RE_MIN, RE_MAX);
                double imag = map(y, HEIGHT, IM_MIN, IM_MAX);
                Complex z = new Complex(real, imag);

                int iter = juliaIterations(z, MAX_ITER);

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

        // Area estimate (fraction of points inside * total complex area)
        double areaTotal = (RE_MAX - RE_MIN) * (IM_MAX - IM_MIN);
        double pixelFraction = insideCount / (double)(WIDTH * HEIGHT);
        double areaEstimate = pixelFraction * areaTotal;

        g.setColor(Color.BLACK);
        g.drawString("Julia Set for c = " + C.toString(), 10, HEIGHT - 25);
        g.drawString(String.format("Approximate Area: %.4f", areaEstimate), 10, HEIGHT - 10);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Julia Set Viewer");
        JuliaSetDisplay canvas = new JuliaSetDisplay();
        f.add(canvas);
        f.setSize(WIDTH, HEIGHT);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
