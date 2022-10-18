package generalized;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class ColorFrame extends JFrame implements Observer {
	Color color;
	String colorName = "black";
	JPanel p = new JPanel(true);

	public ColorFrame() {
		super("Colors"); //set frame caption
		getContentPane().add("Center", p);
//		s.addObserver(this); //register with Observable
		setBounds(100, 100, 100, 100);
		setVisible(true);
	}

	public void paint(Graphics g) {
		g.drawString(colorName, 20, 50);
	}

	@Override
	public void update(Observable o, Object arg) {
		//  old.Observer is notified of change here
		System.out.print(arg);
		colorName = (String)arg; //save color name
		//  set background to that color
		if (colorName.equalsIgnoreCase("RED"))
			color = Color.red;
		if (colorName.equalsIgnoreCase("BLUE"))
			color = Color.blue;
		if (colorName.equalsIgnoreCase("GREEN"))
			color = Color.green;
		p.setBackground(color);
	}
} // class ColorFrame
