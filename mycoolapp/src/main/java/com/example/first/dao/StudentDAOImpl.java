package com.example.first.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.first.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;


@Repository
public class StudentDAOImpl implements StudentDAO {
	
	EntityManager entityManager;
	
	
	
	@Autowired
	public StudentDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}



	@Override
	@Transactional
	public void save(Student student) {
		// TODO Auto-generated method stub
		
		entityManager.persist(student);
		
	}



	@Override
	public Student findById(Integer id) {
		
		return entityManager.find(Student.class, id);
	}



	@Override
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		
		TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student order by lastName", Student.class);
		return theQuery.getResultList();
	}



	@Override
	public List<Student> findByLastName(String theLastName) {
		TypedQuery<Student> theQuery = entityManager.createQuery(
                "FROM Student WHERE lastName=:theData", Student.class);

		// set query parameters
		theQuery.setParameter("theData", theLastName);
		
		// return query results
		return theQuery.getResultList();
	}



	@Override
	@Transactional
	public void update(Student student) {
		// TODO Auto-generated method stub
		entityManager.merge(student);
	}



	@Override
	@Transactional
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		Student student = entityManager.find(Student.class, id);
		entityManager.remove(student);
		
	}



	@Override
	@Transactional
	public int deleteAll() {
		// TODO Auto-generated method stub
		
		int rowDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
		return rowDeleted;
	}

}
