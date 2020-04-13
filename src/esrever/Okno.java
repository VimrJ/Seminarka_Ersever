package esrever;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Okno {												//Tøída okno má pøekvapivì na starost vlastnosti okna

	public Okno(int w, int h, String nazev, Hra hra) {
		hra.setPreferredSize(new Dimension(w, h));
		hra.setMaximumSize(new Dimension(w, h));
		hra.setMinimumSize(new Dimension(w, h));
		
		JFrame frame = new JFrame(nazev);
		frame.add(hra);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		hra.start();
	}
}
