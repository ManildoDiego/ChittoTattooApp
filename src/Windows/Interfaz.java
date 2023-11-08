package Windows;

import utils.BasicWindow;
import utils.Button;
import utils.Label;

import java.awt.Font;
import java.io.IOException;

public class Interfaz extends BasicWindow {
	public Interfaz() {
		super("Chitto Tattoo", 668, 413, false, false);

		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		var preciosBtn = new Button("Precios", colorVioleta, 112, 209);
		preciosBtn.addActionListener(e -> {
			var newframe = new Precios();
			newframe.run();
		});

		var gestionarTurnosBtn = new Button("Gestionar Turnos", colorVioleta, 112, 119);
		gestionarTurnosBtn.addActionListener(e -> {
			var newframe = new GestionarTurnos();
			newframe.run();
		});

		getContentPane().add(gestionarTurnosBtn);
		getContentPane().add(preciosBtn);

		var verTurnosBtn = new Button("Ver turnos", colorVioleta, 357, 119);
		verTurnosBtn.addActionListener(e -> {
			var newframe = new TurnosAgendados();
			newframe.run();
		});

		getContentPane().add(verTurnosBtn);

		var diseniosBtn = new Button("Agregar usuario", colorVioleta, 357, 209);
		diseniosBtn.addActionListener(e -> {
			var newframe = new AgregarUsuario();
			newframe.run();
		});

		getContentPane().add(diseniosBtn);

		var chittoTattoLbl = new Label("Chitto Tattoo");
		chittoTattoLbl.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		chittoTattoLbl.setForeground(colorVioleta);
		chittoTattoLbl.setBounds(220, 30, 262, 53);
		getContentPane().add(chittoTattoLbl);
	}
}
