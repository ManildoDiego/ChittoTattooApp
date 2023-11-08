package Windows;

import Windows.Interfaz;
import utils.*;

import javax.swing.*;
import java.sql.SQLException;

public class LoginMenu extends BasicWindow {
	private static final String s_user = "admin";
	private static final String s_password = "admin";
	private static final boolean s_debugMode = true;

	public static int idPersonal;

	public LoginMenu() {
		super("Inicio de Sesion", 300, 200, false, false);
		setLocationRelativeTo(null);

		var panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		var usernameLabel = new Label("Usuario:");
		var usernameField = new JTextField();
		var passwordLabel = new Label("Contrasenia:");
		var passwordField = new JPasswordField();
		var loginButton = new JButton("Iniciar Sesion");

		loginButton.addActionListener(e -> {
			var username = usernameField.getText();
			var passwordChars = passwordField.getPassword();
			var password = new String(passwordChars);

			var id = -1;

			try {
				id = Database.obtenerIdPersonal(username);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}

			var isValid = id != -1;
			var debug = s_debugMode && (username.isEmpty() && password.isEmpty());

			if (isValid || debug) {
				var w = new Interfaz();
				w.run();
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Usuario o contrasenia incorrecta. Vuelva a intentar");
			}

			idPersonal = id;

			usernameField.setText("");
			passwordField.setText("");
		});

		panel.add(usernameLabel);
		panel.add(usernameField);
		panel.add(passwordLabel);
		panel.add(passwordField);
		panel.add(loginButton);

		add(panel);
	}
}
