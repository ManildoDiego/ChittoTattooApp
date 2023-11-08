package Windows;

import utils.BasicSubWindow;
import utils.Database;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AgregarUsuario extends BasicSubWindow {
    public AgregarUsuario() {
        super("Agregar Usuario", 400, 225, false, false);
        setLocationRelativeTo(null);

        var panel = new JPanel();
        var usuarioLabel = new JLabel("Nombre de usuario:");
        var usuarioTextField = new JTextField(20);
        var passwordLabel = new JLabel("Contrasenia:");
        var passwordTextField = new JPasswordField(20);
        var direccionLabel = new JLabel("Direccion:");
        var direccionField = new JTextField(20);
        var addButton = new JButton("Agregar");

        addButton.addActionListener(e -> {
            var nombreUsuario = usuarioTextField.getText();
            var direccionChars = direccionField.getText();
            var password = new String(passwordTextField.getPassword());
            
            var fullNombre = nombreUsuario + "_" + password;

            try {
                Database.insertarDatosPersonal(fullNombre, direccionChars, 0);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            JOptionPane.showMessageDialog(this, "Usuario agregado correctamente");
            usuarioTextField.setText("");
            direccionField.setText("");
            passwordTextField.setText("");
        });

        panel.add(usuarioLabel);
        panel.add(usuarioTextField);
        panel.add(passwordLabel);
        panel.add(passwordTextField);
        panel.add(direccionLabel);
        panel.add(direccionField);
        panel.add(addButton);

        panel.setLayout(new GridLayout(4, 2, 10, 10));

        getContentPane().add(panel);
    }
}
