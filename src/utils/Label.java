package utils;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Label extends JLabel {
	public Label() {
		super();
	}

	public Label(String text) {
		super(text);
	}

	public Label(String text, String iconPath) throws IOException {
		this(text);

		setImage(iconPath);
	}

	private void setImage(String iconPath) throws IOException {
		iconPath = System.getProperty("user.dir") + "\\src\\" + iconPath;

		var file = new File(iconPath);

		if (!file.exists()) {
			throw new IOException("File \"" + iconPath + "\" is not valid");
		}

		setIcon(new ImageIcon(iconPath));
	}
}
