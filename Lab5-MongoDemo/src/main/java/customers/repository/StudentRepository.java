package customers.repository;

import customers.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student,Long> {
    @Query("{name: ?0}")
    List<Student> findAllStudentByName(String studentName);
    Student findByPhoneNumber(String phoneNumber);
    @Query("{'address.city' : ?0}")
    List<Student> findAllStudentByCity(String city);
}
