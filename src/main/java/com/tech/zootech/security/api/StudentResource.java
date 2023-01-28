package com.tech.zootech.security.api;

import com.tech.zootech.security.DTO.StudentDto;
import com.tech.zootech.security.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/students")
@RequiredArgsConstructor
public class StudentResource {
    private final StudentService studentService;

    @GetMapping("/get_student")
    public ResponseEntity<StudentDto> get(@RequestParam Long id) {
        return ResponseEntity.ok().body(studentService.get(id));
    }

    @PostMapping
    public ResponseEntity<StudentDto> save(@RequestBody StudentDto studentDto) {
        return ResponseEntity.ok().body(studentService.save(studentDto));
    }

    @GetMapping("/get_student_by_name_and_surname")
    public ResponseEntity<StudentDto> getStudentByNameAndSurname(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "surname") String surname) {
        return ResponseEntity.ok().body(studentService.getByNameAndSurname(name, surname));
    }

    @GetMapping("/{surname}")
    public ResponseEntity<List<StudentDto>> getStudentListBySurname(
            @PathVariable(name = "surname") String surname) {
        return ResponseEntity.ok(studentService.getByStudentSurname(surname));
    }
}
