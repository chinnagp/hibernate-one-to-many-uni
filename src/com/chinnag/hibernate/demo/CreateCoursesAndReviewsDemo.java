package com.chinnag.hibernate.demo;

import com.chinnag.hibernate.demo.entity.Course;
import com.chinnag.hibernate.demo.entity.Instructor;
import com.chinnag.hibernate.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesAndReviewsDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		
		// create session
		Session session = sessionFactory.getCurrentSession();
		
		try {					
			session.beginTransaction();

			Course newCourse = new Course("Geology");
			newCourse.addReview(new Review("Great course"));
			newCourse.addReview(new Review("Great course, excellent"));
			newCourse.addReview(new Review("Good one"));
			newCourse.addReview(new Review("Great course, outdoor project activities are great"));

			System.out.println("Saving the course");
			System.out.println(newCourse);
			System.out.println(newCourse.getReviews());

			session.save(newCourse);

			session.getTransaction().commit();
			
			System.out.println("Done");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

}
