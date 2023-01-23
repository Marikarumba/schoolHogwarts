package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;


@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public Collection<Faculty> getAll(){
        return facultyService.getAll();
    }

    @GetMapping("/{id}")
    public Faculty getById(@PathVariable("id")long id){
        return facultyService.getFaculty(id);
    }

    @GetMapping("/color/{color}")
    public Collection <Faculty> getFacultyByColor(@PathVariable("color") String color){
        return facultyService.getFacultyByColor(color);
    }

    @GetMapping("/search/{nameOrColor}")
    public Faculty findByNameOrColor(@PathVariable("nameOrColor") String nameOrColor){
        return facultyService.findByNameOrColor(nameOrColor);
    }
    @PostMapping
    public  Faculty createFaculty(@RequestBody Faculty faculty){
        return facultyService.addFaculty(faculty);
    }

    @PutMapping("/{id}")
    public Faculty changeFaculty(@PathVariable("id") long id, @RequestBody Faculty faculty){
        return facultyService.editFaculty(id,faculty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable("id") long id){
        facultyService.removeFaculty(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getLongestName")
    public String getLongName() {
        return   facultyService.getLongestNameFaculty();
    }


}
