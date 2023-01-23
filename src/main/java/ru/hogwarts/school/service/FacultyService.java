package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Stream;

@Service
public class FacultyService {

    Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        logger.info("the following method was called: addFaculty");
        return facultyRepository.save(faculty);
    }

    public Collection<Faculty> getAll() {
        logger.info("the following method was called: getAll");
        return facultyRepository.findAll();
    }

    public Faculty editFaculty(Long id, Faculty faculty) {
        Faculty dbFaculty = facultyRepository.findById(id)
                .orElseThrow(StudentNotFoundException::new);
        dbFaculty.setName(faculty.getName());
        dbFaculty.setColor(faculty.getColor());
        logger.info("the following method was called: editFaculty");
        return facultyRepository.save(dbFaculty);
    }

    public Faculty getFaculty(Long id) {
        logger.info("the following method was called: getFaculty");
        return facultyRepository.findById(id)
                .orElseThrow(FacultyNotFoundException::new);
    }

    public void removeFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getFacultyByColor(String color) {
        logger.info("the following method was called: getFacultyByColor");
        return facultyRepository.findByColor(color);
    }

    public Faculty findByNameOrColor(String nameOrColor) {
        logger.info("the following method was called: findByNameOrColor");
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(nameOrColor, nameOrColor)
                .orElseThrow(FacultyNotFoundException::new);
    }

    public String getLongestNameFaculty() {
        return facultyRepository.findAll()
                .stream().map(Faculty::getName)
                .max(Comparator.comparingInt(String::length))
                .get();
    }

        public int getInt() {
            return Stream.iterate(1, a -> a + 1).
                    limit(1_000_000).
                    parallel().
                    reduce(0, Integer::sum);
        }
    }


