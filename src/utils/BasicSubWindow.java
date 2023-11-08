package utils;

import javax.swing.*;
import java.awt.*;

public abstract class BasicSubWindow extends BasicWindow {
	protected BasicSubWindow(String title, int width, int height, boolean isResizable, boolean isMaximized, Color background) {
		super(title, width, height, isResizable, isMaximized, background, JFrame.DISPOSE_ON_CLOSE);
	}

	protected BasicSubWindow(String title, int width, int height, boolean isResizable, boolean isMaximized) {
		this(title, width, height, isResizable, isMaximized, colorNegro);
	}

	protected BasicSubWindow(String title, int width, int height, boolean isResizable) {
		this(title, width, height, isResizable, false);
	}

	protected BasicSubWindow(String title, int width, int height) {
		this(title, width, height, true);
	}

	protected BasicSubWindow(String title) {
		super(title, 500, 400, false, false, JFrame.DISPOSE_ON_CLOSE);
	}
}
