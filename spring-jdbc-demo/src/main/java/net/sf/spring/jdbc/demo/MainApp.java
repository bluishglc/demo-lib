package net.sf.spring.jdbc.demo;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MainApp {
   public static void main(String[] args) {
      ApplicationContext context = 
             new ClassPathXmlApplicationContext("applicationContext.xml");

      StudentService studentService = (StudentService)context.getBean("studentService");
      
      System.out.println("------Records Creation--------" );
     
      System.out.println("------Create records via studentService.testCreate() for testing transaction.--------" );
      studentService.testCreate();
     
      System.out.println("------Create records via  studentService.create().--------" );
      studentService.create("Zara", 11);
      studentService.create("Nuha", 2);
      studentService.create("Ayan", 15);

      System.out.println("------Listing Multiple Records--------" );
      List<Student> students = studentService.listStudents();
      for (Student record : students) {
         System.out.print("ID : " + record.getId() );
         System.out.print(", Name : " + record.getName() );
         System.out.println(", Age : " + record.getAge());
      }

      System.out.println("----Updating Record with ID = 2 -----" );
      studentService.update(2, 20);

      System.out.println("----Listing Record with ID = 2 -----" );
      Student student = studentService.getStudent(2);
      System.out.print("ID : " + student.getId() );
      System.out.print(", Name : " + student.getName() );
      System.out.println(", Age : " + student.getAge());
	  
   }
}