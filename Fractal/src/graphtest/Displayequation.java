package graphtest;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.reflect.Array;

public class Displayequation {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		System.out.println("Have you used this program before? (true/false");
		boolean speedrun = input.nextBoolean();
		if (!speedrun) {
		System.out.println("Hello!");
		System.out.println("This program aims to find the nth term for a reiterative equation 'Zn+1 = (Zn)^2 + c'");
		System.out.println("please type in the command line what you wish the real component Z1 to be equal to?");
		} else {
			System.out.println("real z1 , img z1 , real c, img c, show all, n");
		}

		double realcompz1 = input.nextDouble();
		if (!speedrun) {
		System.out.println("Thanks! now please state the imaginary component of Z1");
		}
		double imgcompz1 = input.nextDouble();
		Complex Z1 = new Complex(realcompz1, imgcompz1);
		if (!speedrun) {
		System.out.println("thank you, your Z1 is equal to '" + Z1.toString());
		System.out.println("What would you like your 'c' value's real component to be?");
		}
		double realcompc = input.nextDouble();
		if (!speedrun) {
		System.out.println("Thanks, what would you like your 'c' value's imaginary component to be?");
		}
		double imgcompc = input.nextDouble();
		Complex c = new Complex(realcompc, imgcompc);
		if (!speedrun) {
		System.out.println("thank you, your c is equal to '" + c.toString());
		System.out.println("Great! We are ready to start computing your equation, but first there are a couple questions to answer.");
		System.out.println("Firstly, would you like us to display each value leading up to your desired n (true/false)");
		}
		boolean DispAll = input.nextBoolean();
		if (!speedrun) {
		System.out.println("Ok what what is the maximum term you would like to display");
		}
		int n = input.nextInt();
		System.out.println("Here it is then!");
		Complex Zi = new Complex(realcompz1,imgcompz1);
		//createWindow();
		if (DispAll) System.out.println("Z1 = " + Zi.toString());
		//Complex points = Zi;
		
		for (int i = 0; i <= n-1; i += 1) {
			//points[i] = Zi;
			Zi = Zi.times(Zi);
			Zi = Zi.plus(c);
			if (DispAll || i == n) System.out.println("Z" + String.valueOf(i+2) + " = " + Zi.toString());
		}
		/*
		for (int p = 0; p <= n-1; p++) {
			Graphics[] g;
			g[p].drawLine((int)points[p+1].real(), (int)points[p+1].imag(), (int)points[p].real(), (int)points[p].imag());
		}
**/

		
	}

}
