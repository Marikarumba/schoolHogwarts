package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private Long counter = 0L;
    private final Map<Long, Student> studentMap = new HashMap<>();

    public Student addStudent(Student student){
        Long id = counter++;
        student.setId(id);
        studentMap.put(id,student);
        return student;
    }

    public Student editStudent (Long id, Student student){
        if (studentMap.containsKey(id)){
            Student editSt = studentMap.get(id);
            editSt.setAge(student.getAge());
            editSt.setName(student.getName());
            return editSt;
        } else {
            throw new StudentNotFoundException();
        }
    }

    public Student getStudent (Long id) {
        if (studentMap.containsKey(id)) {
            return studentMap.get(id);
        } else {
            throw new StudentNotFoundException();
        }
    }

    public Collection<Student> getAll(){
        return studentMap.values();
    }

    public void removeStudent ( Long id){
        if (studentMap.containsKey(id)) {
             studentMap.remove(id);
        } else {
            throw new StudentNotFoundException();
        }
    }

    public Collection<Student> getStudentsByAge(int age) {
        return studentMap.values().stream().filter(s->s.getAge() == age).collect(Collectors.toList());
    }
}


