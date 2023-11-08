package Windows;

import utils.BasicSubWindow;
import utils.Button;
import utils.Turnos;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TurnosAgendados extends BasicSubWindow {
	private final Calendar m_calendar;
	private static final String[] s_days = {"Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};

	public TurnosAgendados() {
		super("Turnos agendados", 500, 250, false, false);
		getContentPane().setBackground(colorNegro);
		setBackground(colorNegro);
		getContentPane().setForeground(colorNegro);
		m_calendar = new GregorianCalendar();
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		var controlPanel = new JPanel(new BorderLayout());
		var prevButton = new Button("Anterior");
		var nextButton = new Button("Siguiente");
		var monthLabel = new JLabel("", SwingConstants.CENTER);
		var calendarPanel = new JPanel(new GridLayout(0, 7));

		prevButton.addActionListener(e -> {
			m_calendar.add(Calendar.MONTH, -1);
			updateCalendar(monthLabel, calendarPanel);
		});

		nextButton.addActionListener(e -> {
			m_calendar.add(Calendar.MONTH, 1);
			updateCalendar(monthLabel, calendarPanel);
		});

		controlPanel.add(prevButton, BorderLayout.WEST);
		controlPanel.add(monthLabel, BorderLayout.CENTER);
		controlPanel.add(nextButton, BorderLayout.EAST);

		add(controlPanel, BorderLayout.NORTH);

		var headerPanel = new JPanel(new GridLayout(1, 7));
		for (var dayName : s_days) {
			var dayLabel = new JLabel(dayName, SwingConstants.CENTER);
			headerPanel.add(dayLabel);
		}

		add(headerPanel, BorderLayout.CENTER);
		add(calendarPanel, BorderLayout.SOUTH);

		updateCalendar(monthLabel, calendarPanel);
	}

	private void updateCalendar(JLabel monthLabel, JPanel calendarPanel) {
		m_calendar.set(Calendar.DAY_OF_MONTH, 1);

		final var month = m_calendar.get(Calendar.MONTH);
		final var year = m_calendar.get(Calendar.YEAR);

		var sdf = new SimpleDateFormat("MMMM yyyy");
		monthLabel.setText(sdf.format(m_calendar.getTime()));

		calendarPanel.removeAll();

		m_calendar.set(Calendar.DAY_OF_MONTH, 1);

		final var firstDayOfWeek = m_calendar.get(Calendar.DAY_OF_WEEK) - 1;

		final var lastDayOfMonth = m_calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		for (var i = 0; i < firstDayOfWeek; i++) {
			calendarPanel.add(new JLabel(""));
		}

		for (var day = 1; day <= lastDayOfMonth; day++) {
			final var dayButton = getButton(day);

			calendarPanel.add(dayButton);
		}

		calendarPanel.revalidate();
		calendarPanel.repaint();
	}

	private Button getButton(int day) {
		final var selectedDay = day;
		var selectedDate = getDateFromButton(m_calendar.get(Calendar.YEAR), m_calendar.get(Calendar.MONTH), selectedDay);
		var turnos = Turnos.GetTurnos(selectedDate);

		final var dayButton = new Button(String.valueOf(day), turnos.length == 0 ? colorVerde : colorRojo);

		dayButton.addActionListener(e -> {
			if (turnos.length == 0) {
				JOptionPane.showMessageDialog(null, "No hay turnos el dia " + selectedDay);
			}
			else {
				var text = new StringBuilder("Turnos del dia " + selectedDay + ":\n");

				for (var t : turnos) {
					text.append(t.toString());
				}

				JOptionPane.showMessageDialog(null, text.toString());
			}
		});
		return dayButton;
	}

	private static java.sql.Date getDateFromButton(int year, int month, int day) {
		var calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		return new java.sql.Date(calendar.getTime().getTime());
	}
}
