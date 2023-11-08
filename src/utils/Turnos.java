package utils;

import javax.swing.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class Turnos implements IColors {
	public static Turno[] getTurnosModel() {
	    List<Turno> turnosList = null;
	    try {
	        turnosList = Database.obtenerTurnos();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    if (turnosList != null) {
	        Turno[] turnosArray = new Turno[turnosList.size()];
	        turnosArray = turnosList.toArray(turnosArray);
	        return turnosArray;
	    } else {
	        return new Turno[0];
	    }
	}

	public static JList<Turno> getList() {
		var list = new JList<Turno>(getTurnosModel());
		list.setBackground(colorNegro);
		list.setForeground(colorVioleta);
		return list;
	}

	public static Turno getTurnoSeleccionado() {
		return getList().getSelectedValue();
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
	
	public static void refreshList() {
		getList();
	}
}
