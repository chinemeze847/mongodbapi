package com.example.mymongo.repository;

import com.example.mymongo.domain.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student, String>
{
    Optional<Student> findStudentByEmail(String email);
    void deleteStudentByFirstName(String firstname);
    Student findStudentByFirstName(String firstname);
}
