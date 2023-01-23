package ru.hogwarts.school;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentTests {
    private Long testIdFaculty;
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    StudentRepository studentRepository;

    @LocalServerPort
    int port;

    private TestRestTemplate testRestTemplate= new TestRestTemplate();

    @BeforeEach
    public void cleanUp(){
      studentRepository.deleteAll();
      facultyRepository.deleteAll();
        Faculty testF = new Faculty();
        testF.setName("testN");
        testF.setColor("testC");
        testIdFaculty = facultyRepository.save(testF).getId();
    }


    @Test
    void createStudentTest() throws Exception {
        Student student = new Student();
        student.setName("testName");
        student.setAge(23);
        org.assertj.core.api.Assertions.assertThat(testRestTemplate.postForObject
                ("http://localhost:" + port + "/student",
                student, String.class)).isNotNull();

    }

    @Test
    void getAllTest(){
        org.assertj.core.api.Assertions.assertThat(testRestTemplate.getForObject
                        ("http://localhost:" + port + "/student", String.class))
                .isNotEmpty();
    }

    @Test
    void getStudentInfoTest() {
        org.assertj.core.api.Assertions.assertThat(testRestTemplate.getForObject
                        ("http://localhost:" + port + "/student/1", String.class))
                .isNotEmpty();
    }

    @Test
    void findFacultyStudents() {
        org.assertj.core.api.Assertions.assertThat(testRestTemplate.getForObject
                        ("http://localhost:" + port + "/student/1", String.class))
                .isNotEmpty();
    }

    @Test
    void filterStudentAge()  {
        org.assertj.core.api.Assertions.assertThat(testRestTemplate.getForObject
                        ("http://localhost:" + port + "/student/14", String.class))
                .isNotEmpty();
    }

    @Test
    public void deleteByIdTest(){
        Student testST = new Student();
        testST.setName("testName");
        testST.setAge(20);
        testST.setFaculty(facultyRepository.getReferenceById(testIdFaculty));
        Long id = studentRepository.save(testST).getId();
        Assertions.assertEquals(1, studentRepository.findAll().size());

        testRestTemplate.delete("http://localhost:" + port + "/student/" + id);
        Assertions.assertTrue(studentRepository.findAll().isEmpty());
    }
}
