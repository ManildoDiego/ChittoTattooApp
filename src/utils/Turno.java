package utils;

import java.sql.Date;

public class Turno {
	private final Date fecha;
	private final String detalles;

	public Turno(Date fecha, String detalles) {
		this.fecha = fecha;
		this.detalles = detalles;
	}

	@Override
	public String toString() {
		return fecha.toString() + ": " + detalles;
	}

	public Date getFecha() {
		return fecha;
	}

	public String getDetalles() {
		return detalles;
	}
}
