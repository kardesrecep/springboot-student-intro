package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {

    Logger logger = LoggerFactory.getLogger(StudentController.class);
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
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        String message = "Student is deleted successfully";

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //update
    @PutMapping("/{id}")

    public ResponseEntity<String> updateStudent(@PathVariable("id") Long id, @Valid @RequestBody StudentDTO studentDTO) {

        studentService.updateStudent(id, studentDTO);
        String message = "Student is updated successfully";
        return ResponseEntity.ok(message);


    }

    //getAllWithPage
    @GetMapping("/page")
    public ResponseEntity<Page<Student>> getAllWithPage(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String prop,
            @RequestParam("direction") Sort.Direction direction) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, prop));

        Page<Student> studentPage = studentService.getAllWithpage(pageable);

        return ResponseEntity.ok(studentPage);


    }


    //getByLastName
    @GetMapping("/querylastname")
    public ResponseEntity<List<Student>> getByLastName(@RequestParam("lastname") String lastname) {

        List<Student> students = studentService.getByLastname(lastname);
        return ResponseEntity.ok(students);
    }

    //getGrade -->JPQL
    @GetMapping("/grade/{grade}")
    public ResponseEntity<List<Student>> getStudentsEqualsGrade(@PathVariable("grade") Integer grade) {
        List<Student> studentsGrade = studentService.getStudentEqualsGrade(grade);
        return ResponseEntity.ok(studentsGrade);

    }

    //logger

    @GetMapping("/welcome")
    public String welcome(HttpServletRequest request) {
        logger.warn("------------Welcome {}",request.getServletPath() );

        return "welcome to spring boot";

    }


}



