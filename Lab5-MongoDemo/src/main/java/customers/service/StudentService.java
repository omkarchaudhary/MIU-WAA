package customers.service;

import customers.entity.Student;
import customers.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public List<Student> getAllStudentsByName(String studentName){
        return studentRepository.findAllStudentByName(studentName);
    }
    public Student getStudentByPhoneNumber(String phoneNumber){
        return studentRepository.findByPhoneNumber(phoneNumber);
    }
    public List<Student> getAllStudentByCity(String city){
        return studentRepository.findAllStudentByCity(city);
    }
}
