package Windows;

import utils.*;

import javax.swing.*;
import java.sql.SQLException;

public class LoginMenu extends BasicWindow {
	private static final boolean s_debugMode = false;

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
			
			var fullName = username + "_" + password;

			var id = -1;

			try {
				id = Database.obtenerIdPersonal(fullName);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}

			if (id != -1 || s_debugMode) {
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
