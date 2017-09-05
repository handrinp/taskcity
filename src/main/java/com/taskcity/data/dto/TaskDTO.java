package com.taskcity.data.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;

import org.json.JSONObject;

import com.taskcity.Logger;

public class TaskDTO {
	private static final long URGENT_DURATION = 172_800_000L; // 48 hours, in milliseconds
	private static final long HOURS = 1000L * 60L * 60L;
	private static final long DAYS = HOURS * 24L;
	private static final long WEEKS = DAYS * 7L;

	private String id;
	private String subject;
	private String description;
	private long due;

	public TaskDTO(String id, String subject, String description, long due) {
		this.id = id;
		this.subject = subject;
		this.description = description;
		this.due = due;
	}

	public static TaskDTO of(JSONObject jo) {
		return new TaskDTO(jo.getString("taskid"), jo.getString("subject"), jo.getString("task"), jo.getLong("due"));
	}

	public String getID() {
		return id;
	}

	public String getSubject() {
		return subject;
	}

	public String getDescription() {
		return description;
	}

	public long getDue() {
		return due;
	}

	private boolean hasDueDate() {
		return due != 0;
	}

	public String dueString() {
		StringBuilder dueString = new StringBuilder();

		if (hasDueDate()) {
			long millis = millisLeft();

			if (millis > 0) {
				long hours = (millis / HOURS) % 24L;
				long days = (millis / DAYS) % 7L;
				long weeks = millis / WEEKS;

				if (weeks != 0) {
					dueString.append(weeks)
							.append("w ");
				}

				if (days != 0) {
					dueString.append(days)
							.append("d ");
				}

				if (hours != 0) {
					dueString.append(hours)
							.append("h");
				}
			} else {
				dueString.append("LATE");
			}
		}

		return dueString.toString()
				.trim()
				.replaceAll(" ", "<br>");
	}

	private long millisLeft() {
		return due - System.currentTimeMillis();
	}

	public boolean isUrgent() {
		return hasDueDate() && millisLeft() < URGENT_DURATION;
	}

	private static final String JSON_FORMAT = "{\"subject\":\"%s\",\"task\":\"%s\",\"due\":%d,\"taskid\":\"%s\"}";

	public JSONObject asJSON() {
		String json = String.format(JSON_FORMAT, subject, description, due, id);
		return new JSONObject(json);
	}

	@Override
	public String toString() {
		return String.format("Task %s : [subject: %s, description: %s, due: %d]", id, subject, description, due);
	}

	public static final Comparator<TaskDTO> COMPARATOR = Comparator.comparing(TaskDTO::isUrgent)
			.reversed()
			.thenComparing(TaskDTO::getSubject)
			.thenComparingLong(TaskDTO::getDue);

	public static TaskDTO of(ResultSet rs) {
		TaskDTO task = null;

		String id;
		try {
			id = rs.getString("taskid");
			String subject = rs.getString("subject");
			String description = rs.getString("description");
			long due = rs.getLong("due");
			task = new TaskDTO(id, subject, description, due);
		} catch (SQLException e) {
			Logger.log("couldn't create TaskDTO from ResultSet", e);
		}

		return task;
	}
}
