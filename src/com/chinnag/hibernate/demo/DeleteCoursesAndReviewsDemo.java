package com.chinnag.hibernate.demo;

import com.chinnag.hibernate.demo.entity.Course;
import com.chinnag.hibernate.demo.entity.Instructor;
import com.chinnag.hibernate.demo.entity.Review;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCoursesAndReviewsDemo {

	public static void main(String[] args) {
		Logger logger = Logger.getLogger(DeleteCoursesAndReviewsDemo.class);
		
		// create session factory
		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			int courseId = 17;
			Course course = session.get(Course.class, courseId);
			logger.log(Level.INFO, "Deleting the course...");
			logger.log(Level.INFO,course);
			logger.log(Level.INFO,course.getReviews());
			session.delete(course);

			session.getTransaction().commit();
			
			logger.log(Level.INFO, "Done");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
