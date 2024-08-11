package com.spring.jpa.mappings.jpa_mappings.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.jpa.mappings.jpa_mappings.entity.Course;
import com.spring.jpa.mappings.jpa_mappings.entity.Instructor;
import com.spring.jpa.mappings.jpa_mappings.entity.InstructorDetail;
import com.spring.jpa.mappings.jpa_mappings.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class AppDAOImpl implements AppDAO {

	@Autowired
	private EntityManager entityManager;

	@Override
	@Transactional
	public void save(Instructor theInstructor) {
		// TODO Auto-generated method stub

		entityManager.persist(theInstructor);

	}

	@Override
	public Instructor findInstructorById(int theId) {
		// TODO Auto-generated method stub

		return entityManager.find(Instructor.class, theId);
	}

	@Override
	@Transactional
	public void deleteInstructorById(int theId) {
		// TODO Auto-generated method stub

		Instructor tempInstructor = entityManager.find(Instructor.class, theId);

		// get the courses
		List<Course> courses = tempInstructor.getCourses();

		// break association of all courses for the instructor
		for (Course tempCourse : courses) {
			tempCourse.setInstructor(null);
		}

		entityManager.remove(tempInstructor);

	}

	@Override
	public InstructorDetail findInstructorDetailsById(int theId) {
		// TODO Auto-generated method stub

		return entityManager.find(InstructorDetail.class, theId);
	}

	@Override
	@Transactional
	public void deleteInstructorDetailById(int theId) {
		// TODO Auto-generated method stub

		InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);

		tempInstructorDetail.getInstructor().setInstructorDetail(null);

		entityManager.remove(tempInstructorDetail);

	}

	@Override
	public List<Course> findCoursesByInstructorId(int theId) {
		// TODO Auto-generated method stub
		// create query
		TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);
		query.setParameter("data", theId);

		// execute query
		List<Course> courses = query.getResultList();

		return courses;
	}

	@Override
	public Instructor findInstructorByIdJoinFetch(int theId) {

		// create query
		TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i " + "JOIN FETCH i.courses "
				+ "JOIN FETCH i.instructorDetail " + "where i.id = :data", Instructor.class);
		query.setParameter("data", theId);

		// execute query
		Instructor instructor = query.getSingleResult();

		return instructor;
	}

	@Override
	@Transactional
	public void update(Instructor tempInstructor) {
		entityManager.merge(tempInstructor);
	}

	@Override
	@Transactional
	public void update(Course tempCourse) {
		entityManager.merge(tempCourse);
	}

	@Override
	public Course findCourseById(int theId) {
		return entityManager.find(Course.class, theId);
	}

	@Override
	@Transactional
	public void deleteCourseById(int theId) {

		// retrieve the course
		Course tempCourse = entityManager.find(Course.class, theId);

		// delete the course
		entityManager.remove(tempCourse);
	}

	@Override
	@Transactional
	public void save(Course theCourse) {
		entityManager.persist(theCourse);
	}

	@Override
	public Course findCourseAndReviewsByCourseId(int theId) {
		// TODO Auto-generated method stub
		// create query
		TypedQuery<Course> query = entityManager
				.createQuery("select c from Course c " + "JOIN FETCH c.reviews " + "where c.id = :data", Course.class);

		query.setParameter("data", theId);

		// execute query
		Course course = query.getSingleResult();

		return course;
	}

	@Override
	public Course findCourseAndStudentsByCourseId(int theId) {
		// TODO Auto-generated method stub
		// create query
		TypedQuery<Course> query = entityManager
				.createQuery("select c from Course c " + "JOIN FETCH c.students " + "where c.id = :data", Course.class);

		query.setParameter("data", theId);

		// execute query
		Course course = query.getSingleResult();

		return course;
	}

	@Override
	public Student findStudentAndCoursesByStudentId(int theId) {
		// TODO Auto-generated method stub
		// create query
		TypedQuery<Student> query = entityManager.createQuery(
				"select s from Student s " + "JOIN FETCH s.courses " + "where s.id = :data", Student.class);

		query.setParameter("data", theId);

		// execute query
		Student student = query.getSingleResult();

		return student;
	}

	@Override    
	@Transactional
	public void update(Student tempStudent) {
		// TODO Auto-generated method stub
        entityManager.merge(tempStudent);

	}

	@Override
	@Transactional
	public void deleteStudentById(int theId) {
		// TODO Auto-generated method stub
		
		Student tempStudent = entityManager.find(Student.class, theId);

		// delete the course
		entityManager.remove(tempStudent);
		
	}
}
