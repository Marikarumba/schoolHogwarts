package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;
import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository <Student, Long> {

    Collection<Student> findByAge(int age);

    Collection<Student> findByAgeBetween(int minAge, int maxAge);

    @Query (value = "SELECT COUNT(*) FROM students", nativeQuery = true)
    long countStudent();

    @Query (value = "SELECT AVG(age) FROM students", nativeQuery = true)
    double averageAge();

    @Query (value = "SELECT * FROM students, ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List <Student> findLastStudents();
}
