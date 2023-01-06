package ru.hogwarts.school.service;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import java.util.Collection;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public Student addStudent(Student student){
        return studentRepository.save(student);
    }

    public Student editStudent (Long id, Student student){
        Student dbStudent = studentRepository.findById(id)
                .orElseThrow(StudentNotFoundException::new);
                dbStudent.setName(student.getName());
                dbStudent.setAge(student.getAge());
                    return studentRepository.save(dbStudent);
                }

    public Collection<Student> getAll(){
        return studentRepository.findAll();
    }

    public Student getStudent (Long id) {
      return studentRepository.findById(id)
              .orElseThrow(StudentNotFoundException::new);
    }

    public void removeStudent ( Long id){
       studentRepository.deleteById(id);
    }

    public Collection<Student> getStudentsByAge(int age) {
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findStudentByAge(int minAge, int maxAge) {
        return studentRepository.findByAgeBetween(minAge,maxAge);
    }
}


