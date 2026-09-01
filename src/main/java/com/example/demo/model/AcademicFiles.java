package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "academic_files")
public class AcademicFiles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String url;

	private Boolean isInUsed;

	public AcademicFiles() {
		super();
	}

	public AcademicFiles(Long id, String url, boolean isInUsed) {
		super();
		this.id = id;
		this.url = url;
		this.isInUsed = isInUsed;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean isInUsed() {
		return isInUsed;
	}

	public void setInUsed(Boolean isInUsed) {
		this.isInUsed = isInUsed;
	}

}
