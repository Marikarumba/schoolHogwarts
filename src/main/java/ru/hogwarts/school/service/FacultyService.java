package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private Long counter = 0L;
    private final Map<Long, Faculty> facultyMap = new HashMap<>();

    public Faculty addFaculty(Faculty faculty){
        Long id = counter++;
        faculty.setId(id);
        facultyMap.put(id,faculty);
        return faculty;
    }

    public Faculty editFaculty (Long id, Faculty faculty){
        if (facultyMap.containsKey(id)){
            Faculty editFac = facultyMap.get(id);
            editFac.setName(faculty.getName());
            editFac.setColor(faculty.getColor());
            return editFac;
        } else {
            throw new FacultyNotFoundException();
        }
    }

    public Faculty getFaculty (Long id) {
        if (facultyMap.containsKey(id)) {
            return facultyMap.get(id);
        } else {
            throw new FacultyNotFoundException();
        }
    }

    public Collection<Faculty> getAll(){
        return facultyMap.values();
    }

    public void removeFaculty ( Long id){
        if (facultyMap.containsKey(id)) {
            facultyMap.remove(id);
        } else {
            throw new FacultyNotFoundException();
        }
    }
    public Collection<Faculty> getFacultyByColor(String color) {
        return facultyMap.values().stream().filter(s->s.getColor().equals(color)).collect(Collectors.toList());
    }

}
