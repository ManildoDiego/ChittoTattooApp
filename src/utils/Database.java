package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
	public static final String DBNAME = "chittotattoobd";
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/" + DBNAME;
	private static final String USER = "root";
	private static final String PASSWORD = "";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
	}

	public static void insertarDatosPersonal(String nombre, String direccion, int telefono) throws SQLException {
		try (var connection = getConnection();
			 var statement = connection.prepareCall("{call insertar_datos_p(?, ?, ?)}")) {
			statement.setString(1, nombre);
			statement.setString(2, direccion);
			statement.setInt(3, telefono);
			statement.execute();
		}
	}

	public static void insertarDatosTurno(Date fecha, String detalles) throws SQLException {
		try (var connection = getConnection();
			 var statement = connection.prepareCall("{call insertar_datos_t(?, ?)}")) {
			statement.setDate(1, fecha);
			statement.setString(2, detalles);
			statement.execute();
		}
	}

	public static void insertarDatosFactura(float monto, int personalId) throws SQLException {
		try (var connection = getConnection();
			 var statement = connection.prepareCall("{call insertar_datos_f(?, ?)}")) {
			statement.setFloat(1, monto);
			statement.setInt(2, personalId);
			statement.execute();
		}
	}

	public static String obtenerNombrePersonal(int personalId) throws SQLException {
		try (var connection = getConnection();
			 var statement = connection.prepareCall("{call nombre_personal(?)}")) {
			statement.setInt(1, personalId);
			var resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getString("nombre");
			}
		}
		return null;
	}

	public static Timestamp obtenerFechaTurno(int turnoId) throws SQLException {
		try (var connection = getConnection();
			 var statement = connection.prepareCall("{call fecha_turno(?)}")) {
			statement.setInt(1, turnoId);
			var resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getTimestamp("fecha");
			}
		}
		return null;
	}

	public static int obtenerIdPersonal(String nombre) throws SQLException {
		try (var connection = getConnection();
			 var statement = connection.prepareCall("{call obtenerIdPersonal(?)}")) {
			statement.setString(1, nombre);
			var resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt("id");
			}
		}
		return -1;
	}

	public static String obtenerDetallesTurno(int turnoId) throws SQLException {
		try (var connection = getConnection();
			 var statement = connection.prepareCall("{call detalles_turno(?)}")) {
			statement.setInt(1, turnoId);
			var resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getString("detalles");
			}
		}
		return null;
	}

	public static Timestamp obtenerFechaEmisionFactura(int facturaId) throws SQLException {
		try (var connection = getConnection();
			 var statement = connection.prepareCall("{call fecha_emision_f(?)}")) {
			statement.setInt(1, facturaId);
			var resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getTimestamp("fecha_emision");
			}
		}
		return null;
	}

	public static float obtenerMontoFactura(int facturaId) throws SQLException {
		try (var connection = getConnection();
			 var statement = connection.prepareCall("{call monto_f(?)}")) {
			statement.setInt(1, facturaId);
			var resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getFloat("monto");
			}
		}
		return 0.0f;
	}

	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static List<Turno> obtenerTurnos() throws SQLException {
		List<Turno> turnos = new ArrayList<>();
		try (var connection = getConnection();
			 var statement = connection.prepareCall("{call obtenerTurnos()}")) {
			var resultSet = statement.executeQuery();
			while (resultSet.next()) {
				var fecha = resultSet.getDate("fecha");
				var detalles = resultSet.getString("detalles");
				var turno = new Turno(fecha, detalles);
				turnos.add(turno);
			}
		}
		return turnos;
	}

	public static Object[] obtenerTurnos(Date fecha) throws SQLException {
		List<Turno> turnosList = new ArrayList<>();
		try (var connection = getConnection();
			 var statement = connection.prepareStatement("SELECT * FROM turnos WHERE DATE(fecha) = ?")) {
			statement.setDate(1, new java.sql.Date(fecha.getTime()));
			var resultSet = statement.executeQuery();
			while (resultSet.next()) {
				var fechaTurno = resultSet.getDate("fecha");
				var detalles = resultSet.getString("detalles");
				var turno = new Turno(fechaTurno, detalles);
				turnosList.add(turno);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return turnosList.toArray();
	}

	public static void deleteTurno(Turno t) {
		try (var connection = getConnection();
			 var statement = connection.prepareStatement("DELETE FROM turnos WHERE fecha = ? AND detalles = ?")) {
			statement.setDate(1, t.getFecha());
			statement.setString(2, t.getDetalles());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
