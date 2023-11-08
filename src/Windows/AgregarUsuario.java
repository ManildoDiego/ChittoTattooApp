package Windows;

import utils.BasicSubWindow;
import utils.Database;
import utils.Label;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

public class AgregarUsuario extends BasicSubWindow {
    public AgregarUsuario() {
        super("Agregar Usuario", 400, 300, false, false);
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
            var password = Arrays.toString(passwordTextField.getPassword());
            var direccionChars = direccionField.getText();

            try {
                Database.insertarDatosPersonal(nombreUsuario + "_" + password, "", 0);
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

        panel.setLayout(new GridLayout(4, 3, 10, 10));

        getContentPane().add(panel);
    }
}
