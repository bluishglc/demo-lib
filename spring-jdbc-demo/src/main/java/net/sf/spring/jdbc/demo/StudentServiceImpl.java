package net.sf.spring.jdbc.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("studentService")
@Transactional
public class StudentServiceImpl implements StudentService {
	
	private StudentDao studentDao;
	
	
	public void testCreate() {
		studentDao.create("Zara", 11);
		studentDao.create("Nuha", 2);
	}

	public void create(String name, Integer age) {
		studentDao.create(name, age);
	}

	public Student getStudent(Integer id) {
		return studentDao.getStudent(id);
	}

	public List<Student> listStudents() {
		return studentDao.listStudents();
	}

	public void delete(Integer id) {
		studentDao.delete(id);
	}

	public void update(Integer id, Integer age) {
		studentDao.update(id, age);
	}

	@Autowired
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	
	

}
