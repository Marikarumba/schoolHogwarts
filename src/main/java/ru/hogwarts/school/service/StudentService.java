package ru.hogwarts.school.service;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    Logger logger = LoggerFactory.getLogger(AvatarService.class);
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public Student addStudent(Student student){
        logger.info("the following method was called: findAvatar");
        return studentRepository.save(student);
    }

    public Student editStudent (Long id, Student student){
        Student dbStudent = studentRepository.findById(id)
                .orElseThrow(StudentNotFoundException::new);
                dbStudent.setName(student.getName());
                dbStudent.setAge(student.getAge());
        logger.info("the following method was called: findAvatar");
                    return studentRepository.save(dbStudent);
                }

    public Collection<Student> getAll(){
        logger.info("the following method was called: getAll");
        return studentRepository.findAll();
    }

    public Student getStudent (Long id) {
        logger.info("the following method was called: getStudent");
      return studentRepository.findById(id)
              .orElseThrow(StudentNotFoundException::new);
    }

    public void removeStudent ( Long id){
        logger.info("the following method was called: removeStudent");
       studentRepository.deleteById(id);
    }

    public Collection<Student> getStudentsByAge(int age) {
        logger.info("the following method was called: getStudentsByAge");
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findStudentByAge(int minAge, int maxAge) {
        logger.info("the following method was called: findStudentByAge");
        return studentRepository.findByAgeBetween(minAge,maxAge);
    }

    public long getStudentCount() {
        logger.info("the following method was called: getStudentCount");
        return studentRepository.countStudent();
    }

    public double getStudentByAgeAvg() {
        logger.info("the following method was called: getStudentByAgeAvg");
        return studentRepository.averageAge();
    }

    public Collection<Student> getLastStudent() {
        logger.info("the following method was called: getLastStudent");
        return studentRepository.findLastStudents();
    }
}


