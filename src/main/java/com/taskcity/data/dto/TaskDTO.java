package com.taskcity;

import org.json.JSONObject;

public class TaskDTO {
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

	private static final String JSON_FORMAT = "{\"subject\":\"%s\",\"task\":\"%s\",\"due\":%d,\"taskid\":\"%s\"}";

	public JSONObject asJSON() {
		String json = String.format(JSON_FORMAT, subject, description, due, id);
		return new JSONObject(json);
	}

	@Override
	public String toString() {
		return String.format("Task %s : [subject: %s, description: %s, due: %d]", id, subject, description, due);
	}
}
