package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "topic_notes")
public class TopicNotes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "note_id")
	private Long noteId;

	@Column(name = "term_topic_id_fk")
	private Long termTopicIdFk;

	@Column(name = "notes_name")
	private String notesName;

	@Column(name = "notes_url")
	private String notesUrl;

	public Long getNoteId() {
		return noteId;
	}

	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}

	public String getNotesName() {
		return notesName;
	}

	public void setNotesName(String notesName) {
		this.notesName = notesName;
	}

	public String getNotesUrl() {
		return notesUrl;
	}

	public void setNotesUrl(String notesUrl) {
		this.notesUrl = notesUrl;
	}

	public Long getTermTopicIdFk() {
		return termTopicIdFk;
	}

	public void setTermTopicIdFk(Long termTopicIdFk) {
		this.termTopicIdFk = termTopicIdFk;
	}

}
