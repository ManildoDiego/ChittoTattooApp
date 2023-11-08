package utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class Turnos implements IColors {
	private static Turno[] getTurnosModel() {
		List<Turno> t = null;
		try {
			t = Database.obtenerTurnos();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		assert t != null;
		var turnosArray = new Turno[t.size()];
		turnosArray = t.toArray(turnosArray);
		return turnosArray;
	}

	public static JList getListaTurnos() {
		var turnosModel = getTurnosModel();
		var list = new JList(turnosModel);
		list.setBackground(colorNegro);
		list.setForeground(colorVioleta);
		return list;
	}

	public static Turno getTurnoSeleccionado() {
		return (Turno) getListaTurnos().getSelectedValue();
	}

	public static void add(Turno nuevoTurno) {
		try {
			Database.insertarDatosTurno(nuevoTurno.getFecha(), nuevoTurno.getDetalles());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void delete(Turno t) {
		Database.deleteTurno(t);
	}

	public static Turno[] toArray() {
		return getTurnosModel();
	}

	public static Object[] GetTurnos(Date fecha) {
		var f = new Object[0];
		try {
			f = Database.obtenerTurnos(fecha);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return f;
	}

	public static boolean isEmpty() {
		return getTurnosModel().length == 0;
	}
}
