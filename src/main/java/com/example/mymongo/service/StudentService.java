package com.example.mymongo.service;

import com.example.mymongo.domain.Address;
import com.example.mymongo.domain.Student;
import com.example.mymongo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService
{
    StudentRepository repository;

    public List<Student> fetchAllStudents()
    {
        return repository.findAll();
    }

    public Student addStudent(Student student)
    {
        return repository.insert(student);
    }

    public void deleteStudentByFirstname(String firstname)
    {
        repository.deleteStudentByFirstName(firstname);
    }

    public Address findStudentAddress(String firstname)
    {
        Student student = repository.findStudentByFirstName(firstname);

        return student.getAddress();
    }

    public Student updateUser(Student student)
    {
        return repository.save(student);
    }

}
