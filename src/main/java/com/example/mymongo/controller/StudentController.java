package com.example.mymongo.controller;

import com.example.mymongo.domain.Address;
import com.example.mymongo.domain.Student;
import com.example.mymongo.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class StudentController
{
    StudentService studentService;

    @GetMapping("/students")
    public List<Student> fetchStudents()
    {
        return studentService.fetchAllStudents();
    }

    @PostMapping("/students")
    public ResponseEntity<Student> addStudent(@RequestBody Student student)
    {
        return ResponseEntity.ok().body(studentService.addStudent(student));
    }

    @DeleteMapping("/students/{firstname}")
    public ResponseEntity<Student> deleteStudent(@PathVariable("firstname") String firstname)
    {
        studentService.deleteStudentByFirstname(firstname);
        return ResponseEntity.ok().build();
    }
    @GetMapping("students/{firstname}/address")
    public ResponseEntity<Address> getStudentAddress(@PathVariable("firstname") String firstname)
    {
       return ResponseEntity.ok().body(studentService.findStudentAddress(firstname));
    }

    @PutMapping("/students/update")
    public ResponseEntity<Student> updateUser(@RequestBody Student student)
    {
        return ResponseEntity.ok().body(studentService.updateUser(student));
    }

}
