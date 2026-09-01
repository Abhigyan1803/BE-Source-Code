package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "term_topic")
public class TermTopic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "term_topic_id")
	private Long termTopicId;

	@Column(name = "topic_name")
	private String topicName;

//	@Column(name = "notes_name")
//	private String notesName;
	@OneToMany(targetEntity = TopicNotes.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "term_topic_id_fk", referencedColumnName = "term_topic_id")
	List<TopicNotes> notesList;

	@Column(name = "learning_outcomes")
	private String learningOutcomes;

	@Column(name = "required_reading")
	private String requiredReading;

	@Column(name = "bones_reading")
	private String bonesReading;

//	@Lob
//	@Column(name = "notes_url")
//	private String notesUrl;

	@Column(name = "instruction")
	private String instruction;
	
	@Column(name = "instruction_url")
	private String instructionUrl;  //Akash 08/08/2023 V1

	@Column(name = "academicTermId_fk")
	private Long academicTermIdfk;

	public Long getTermTopicId() {
		return termTopicId;
	}

	public void setTermTopicId(Long termTopicId) {
		this.termTopicId = termTopicId;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getLearningOutcomes() {
		return learningOutcomes;
	}

	public void setLearningOutcomes(String learningOutcomes) {
		this.learningOutcomes = learningOutcomes;
	}

	public String getRequiredReading() {
		return requiredReading;
	}

	public void setRequiredReading(String requiredReading) {
		this.requiredReading = requiredReading;
	}

	public String getBonesReading() {
		return bonesReading;
	}

	public void setBonesReading(String bonesReading) {
		this.bonesReading = bonesReading;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public Long getAcademicTermIdfk() {
		return academicTermIdfk;
	}

	public void setAcademicTermIdfk(Long academicTermIdfk) {
		this.academicTermIdfk = academicTermIdfk;
	}

	public List<TopicNotes> getNotesList() {
		return notesList;
	}

	public void setNotesList(List<TopicNotes> notesList) {
		this.notesList = notesList;
	}

	public String getInstructionUrl() {
		return instructionUrl;
	}

	public void setInstructionUrl(String instructionUrl) {
		this.instructionUrl = instructionUrl;
	}

//	public String getNotesName() {
//		return notesName;
//	}
//
//	public void setNotesName(String notesName) {
//		this.notesName = notesName;
//	}
//
//	public String getNotesUrl() {
//		return notesUrl;
//	}
//
//	public void setNotesUrl(String notesUrl) {
//		this.notesUrl = notesUrl;
//	}

}
