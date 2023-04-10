package com.jspiders.hibernateonetomany.dao;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jspiders.hibernateonetomany.dto.StudentDTO;
import com.jspiders.hibernateonetomany.dto.TeacherDTO;

public class TeachStudDAO {

	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;
	
	private static void openConnection() {
		factory=Persistence.createEntityManagerFactory("teacher");
		manager=factory.createEntityManager();
		transaction=manager.getTransaction();
		
	}
	
	private static void closeConnection() {
		if (factory != null) {
			factory.close();
		}
		if (manager != null) {
			manager.close();
		}
		if (transaction.isActive()) {
			transaction.rollback();
		}
	}
	
	public static void main(String[] args) {
		
		try {
			openConnection();
			
			transaction.begin();
			
			StudentDTO student1=new StudentDTO();
			student1.setId(1);
			student1.setName("Mukesh");
			student1.setEmail("mukesh@gmail.com");
			student1.setContact(9898989812l);
			manager.persist(student1);
			
			StudentDTO student2=new StudentDTO();
			student2.setId(2);
			student2.setName("Mahesh");
			student2.setEmail("mahesh@gmail.com");
			student2.setContact(9898989821l);
			manager.persist(student2);
			
			List<StudentDTO> studentDTOs=Arrays.asList(student1,student2);
			
			TeacherDTO teacher=new TeacherDTO();
			teacher.setId(1);
			teacher.setName("Dhananjay Sir");
			teacher.setContact(9090909009l);
			teacher.setStudentDTOs(studentDTOs);
			manager.persist(teacher);
			
			transaction.commit();
			
		} finally {
			closeConnection();
		}
	}
}
