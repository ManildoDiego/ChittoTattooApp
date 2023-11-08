package Windows;

import utils.*;
import utils.Button;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GestionarTurnos extends BasicSubWindow {
    public GestionarTurnos() {
		super("Gestionar turnos");
        setLayout(new BorderLayout());

		var jscrollPanel = new JScrollPane(Turnos.getList());
		jscrollPanel.setBackground(colorNegro);

        add(jscrollPanel, BorderLayout.CENTER);

        var botonPanel = new JPanel();
        var agregarButton = new Button("Agregar Turno");
        var desagendarButton = new Button("Desagendar Turno");
        var desagendarTodoButton = new Button("Desagendar todos los Turnos");

        agregarButton.addActionListener(e -> {
			var fechaField = new JTextField(10);
			var turnoField = new JTextField(20);
			var inputPanel = new JPanel();

			inputPanel.add(new JLabel("Fecha (dd-MM-yyyy):"));
			inputPanel.add(fechaField);
			inputPanel.add(new JLabel("Detalles del turno:"));
			inputPanel.add(turnoField);

			var result = JOptionPane.showConfirmDialog(
					null,
					inputPanel,
					"Ingrese la fecha y detalles del turno",
					JOptionPane.OK_CANCEL_OPTION
			);

			if (result == JOptionPane.OK_OPTION) {
				var fechaTexto = fechaField.getText();
				var detalles = turnoField.getText();

				var dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date d = null;
				try {
					d = dateFormat.parse(fechaTexto);
				} catch (ParseException ex) {
					ex.printStackTrace();
				}
				assert d != null;
				var fecha = new java.sql.Date(d.getTime());
				Turnos.add(new Turno(fecha, detalles));
				
			}
			
			refresh();
		});
        
        desagendarButton.addActionListener(e -> {
            var t = Turnos.getTurnoSeleccionado();
            if (t != null) {
                var confirmacion = JOptionPane.showConfirmDialog(
                        this,
                        "¿Estas seguro que deseas desagendar este turno?",
                        "Confirmar desagendacion",
                        JOptionPane.YES_NO_OPTION
                );
                
                if (confirmacion == JOptionPane.YES_OPTION) {
                    Turnos.delete(t);
					JOptionPane.showMessageDialog(this, "Turno desagendado correctamente.",
							"Turno Desagendado", JOptionPane.INFORMATION_MESSAGE);
					refresh();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un turno para desagendar.",
                		"Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        
        desagendarTodoButton.addActionListener(e -> {
			for (var t : Turnos.getTurnosModel()) {
				if (t != null) {
					Turnos.delete(t);
				}
			}
			
			refresh();
		});
        
        botonPanel.add(agregarButton);
        botonPanel.add(desagendarButton);
        botonPanel.add(desagendarTodoButton);
		botonPanel.setBackground(colorNegro);
        add(botonPanel, BorderLayout.SOUTH);
    }
    
    private void refresh() {
    	dispose();
    	var w = new GestionarTurnos();
    	w.run();
    }
}