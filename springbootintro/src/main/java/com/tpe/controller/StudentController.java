package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {

        List<Student> students = studentService.getAll();
        return ResponseEntity.ok(students);

    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createStudents(@Valid @RequestBody Student student) {
        studentService.createStudent(student);

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("message", "Student is  created successfully");
        responseMap.put("status", "true");

        return new ResponseEntity<>(responseMap, HttpStatus.CREATED);

    }

    //getStudentByIdWithRequestParam
    @GetMapping("/query")
    public ResponseEntity<Student> getStudent(@RequestParam("id") Long id) {

        Student student = studentService.findStudentById(id);
        return ResponseEntity.ok(student);
    }

    //getStudentByIdWithPathVariable

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {
        Student student = studentService.findStudentById(id);

        return ResponseEntity.ok(student);

    }
    @DeleteMapping("/{id}")

    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long id){

      studentService.deleteStudent(id);
      String message = "Student is deleted successfully";

      return new ResponseEntity<>(message,HttpStatus.OK);
    }



}
