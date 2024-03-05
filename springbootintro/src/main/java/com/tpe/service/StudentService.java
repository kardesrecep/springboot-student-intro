package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAll() {
        return studentRepository.findAll();

    }

    public void createStudent(Student student) {

        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new ConflictException("Email  already exists");
        }
        studentRepository.save(student);

    }


    public Student findStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));

    }

    public void deleteStudent(Long id) {

        Student student = findStudentById(id);
        studentRepository.delete(student);
    }

    public void updateStudent(Long id, StudentDTO studentDTO) {
        Student student = findStudentById(id);
        boolean emailExist = studentRepository.existsByEmail(studentDTO.getEmail());
        if (emailExist && !studentDTO.getEmail().equals(student.getEmail())) {
            throw new ConflictException("Email  already exists");

        }
        //DTO--->pojo
        student.setName(studentDTO.getName());
        student.setLastName(studentDTO.getLastName());
        student.setGrade(studentDTO.getGrade());
        student.setPhoneNumber(studentDTO.getPhoneNumber());
        student.setEmail(student.getEmail());
        studentRepository.save(student);



    }

    public Page<Student> getAllWithpage(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    public List<Student> getByLastname(String lastname) {

       return studentRepository.findByLastName(lastname);

    }

    public List<Student> getStudentEqualsGrade(Integer grade) {
        return studentRepository.findAllEqualsGrade(grade);

    }
}
