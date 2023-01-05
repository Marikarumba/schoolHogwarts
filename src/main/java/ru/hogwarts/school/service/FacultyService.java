package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty){
       return facultyRepository.save(faculty);
    }
    public Collection<Faculty> getAll(){
        return facultyRepository.findAll();
    }
    public Faculty editFaculty (Long id, Faculty faculty) {
        Faculty dbFaculty = facultyRepository.findById(id)
                .orElseThrow(StudentNotFoundException::new);
        dbFaculty.setName(faculty.getName());
        dbFaculty.setColor(faculty.getColor());
        return facultyRepository.save(dbFaculty);
    }

    public Faculty getFaculty (Long id) {
        return facultyRepository.findById(id)
                .orElseThrow(FacultyNotFoundException::new);
    }

    public void removeFaculty ( Long id){
        facultyRepository.deleteById(id);
    }
    public Collection<Faculty> getFacultyByColor(String color) {
        return facultyRepository.findByColor(color);
    }

}
