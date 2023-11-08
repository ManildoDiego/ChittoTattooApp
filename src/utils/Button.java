package utils;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton implements IColors {
	public Button(String title, Color background, Icon icon, int x, int y, int width, int height) {
		super(title);
		setBackground(background);
		setIcon(icon);
		setBounds(x, y, width, height);
	}

	public Button(String title, Color background, Icon icon, int x, int y) {
		this(title, background, icon, x, y, 165, 53);
	}

	public Button(String title, Color background, int x, int y) {
		this(title, background, null, x, y);
	}

	public Button(String title, int x, int y) {
		this(title, colorVioleta, x, y);
	}

	public Button(String title, Color background) {
		this(title, background, 0, 0);
	}

	public Button(String title) {
		this(title, 0, 0);
	}

}
