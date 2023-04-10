package com.jspiders.hibernateonetomany.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.List;

import lombok.Data;

@Data
@Entity
public class TeacherDTO {
	
	@Id
	private int id;
	private String name;
	private long contact;
	@OneToMany
	private List<StudentDTO> studentDTOs;

}
