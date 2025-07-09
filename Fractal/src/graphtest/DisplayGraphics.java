package graphtest;

import java.awt.*;  
import javax.swing.JFrame; 
  
public class DisplayGraphics extends Canvas{  
    public static void main(String[] args) {  
        DisplayGraphics m=new DisplayGraphics();  
        JFrame f=new JFrame();  
        f.add(m);  
        f.setSize(600,400);  
        //f.setLayout(null);  
        f.setVisible(true);  
    }  
  
	public int getLocation_x (Complex point) {
		
		return (int) ((point.real()*100)+300);
		
	}
	public int getLocation_y (Complex point) {
		
		return (int) ((point.imag()*100)+200);
		
	}
	public static double getComplex_x (int integerThing) {
		double point = (double) (integerThing);
		return ((point/100)-3);
		
	}
	public static double getComplex_y (int integerThing) {
		double point = (double) (integerThing);
		return (double) (-1*((point/100)-2));
		
	}
	public static boolean checkDiverging(Complex cValue, int n, int d) {
		Complex Zi = new Complex(0,0);
		for (int i = 0; i <= n-1; i ++) {
			Zi = Zi.times(Zi);
			for (int p = 2 ; p <= d ; p++) {
				Zi = Zi.plus(cValue);			
			}
			if (((Zi.real() > 4) || (Zi.imag() > 4))|| ((Zi.imag() < -4) || (Zi.real() < -4))) {
				System.out.println("Diverging   " + Zi.toString());
				return true;
		        
			}
		}
		System.out.println("Converging   " + Zi.toString());
		return false;
	}

    public void paint(Graphics g) {  
    	g.drawString("Made by Nadim A. Mottu", 10, 350);
		for (int i = 0 ; i <= 400 ; i++) {
			for (int r = 0; r <= 600 ; r++) {
				Complex This = new Complex(getComplex_x(r), getComplex_y(i));
				
				if (!checkDiverging(This, 100, 2)) {
			        g.fillRect(r, i,1, 1);

				} //else {
					//System.out.println("Converging   " + This.toString());
			//	}
				
			}
		}
          
    }  

}  