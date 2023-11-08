package utils;

import javax.swing.*;
import java.awt.*;

public abstract class BasicWindow extends JFrame implements IColors {
	protected BasicWindow(String title, int width, int height, boolean isResizable, boolean isMaximized, Color background, int closeOperation) {
		super(title);
		var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setResizable(isResizable);

		getContentPane().setBackground(background);

		if (isMaximized) {
			setExtendedState(JFrame.MAXIMIZED_BOTH);
		}

		var x = (screenSize.width - width) / 2;
		var y = (screenSize.height - height) / 2;

		setBounds(x, y, width, height);
		setDefaultCloseOperation(closeOperation);
	}

	protected BasicWindow(String title, int width, int height, boolean isResizable, boolean isMaximized, int closeOperation) {
		this(title, width, height, isResizable, isMaximized, colorNegro, closeOperation);
	}

	protected BasicWindow(String title, int width, int height, boolean isResizable, boolean isMaximized) {
		this(title, width, height, isResizable, isMaximized, JFrame.EXIT_ON_CLOSE);
	}

	protected BasicWindow(String title, int width, int height, boolean isResizable) {
		this(title, width, height, isResizable, false);
	}

	protected BasicWindow(String title, int width, int height) {
		this(title, width, height, true);
	}

	protected BasicWindow(String title) {
		this(title, 800, 800);
	}

	public void run() {
		setVisible(true);
	}

	protected static Font getBasicFont(int size) {
		return new Font("Segoe UI", Font.PLAIN, size);
	}

	protected int getMiddleX() {
		return getX() / 4;
	}

	protected int getMiddleY() {
		return getY() / 4;
	}
}
