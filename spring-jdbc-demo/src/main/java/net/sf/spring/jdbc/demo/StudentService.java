package net.sf.spring.jdbc.demo;

import java.util.List;

public interface StudentService {

		/**
		 * This method is used to debug spring transaction management.
		 * There're 2 database hit.
		 */
		public void testCreate();
	   /** 
	    * This is the method to be used to create
	    * a record in the Student table.
	    */
	   public void create(String name, Integer age);
	   /** 
	    * This is the method to be used to list down
	    * a record from the Student table corresponding
	    * to a passed student id.
	    */
	   public Student getStudent(Integer id);
	   /** 
	    * This is the method to be used to list down
	    * all the records from the Student table.
	    */
	   public List<Student> listStudents();
	   /** 
	    * This is the method to be used to delete
	    * a record from the Student table corresponding
	    * to a passed student id.
	    */
	   public void delete(Integer id);
	   /** 
	    * This is the method to be used to update
	    * a record into the Student table.
	    */
	   public void update(Integer id, Integer age);
	   
}
