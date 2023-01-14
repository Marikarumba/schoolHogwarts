package ru.hogwarts.school.controller;

import java.util.Collection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;


@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService)
    {
        this.studentService = studentService;
    }

    @GetMapping
    public Collection <Student> getAll(){
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable("id")long id){
        return studentService.getStudent(id);
    }


    @GetMapping("/age/{age}")
    public Collection <Student> getStudentsByAge(@PathVariable("age") int age){
        return studentService.getStudentsByAge(age);
    }

    @GetMapping("/age/between")
    public Collection<Student>findStudentByAge(@RequestParam("minAge")int minAge,
                                               @RequestParam("maxAge")int maxAge){
        return studentService.findStudentByAge(minAge,maxAge);
    }

    @PostMapping
    public  Student createStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @PutMapping("/{id}")
    public Student changeStudent(@PathVariable("id") long id, @RequestBody Student student){
        return studentService.editStudent(id,student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") long id){
        studentService.removeStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("countStudents")
    public long countStudent() {
        return studentService.getStudentCount();
    }

    @GetMapping("avgAgeStudents")
    public double getStudentByAgeAvg() {
        return studentService.getStudentByAgeAvg();
    }

    @GetMapping("last5Students")
    public Collection<Student> getLastStudent() {
        return studentService.getLastStudent();
    }


}
