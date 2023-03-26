package com.example.spring_project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class StudentController {
    private final StudentRepository repository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/student-list")
    public String getStudents(Model model) {
        var students = repository.findAll();
        model.addAttribute("students", students);

        return "student-list";
    }

    @GetMapping("/add-new-student-form")
    public String createStudentForm() {
        return "add-new-student-form";
    }

    @PostMapping("/create-student")
    public String create(String name, Integer age) {
        var student = new Student();

        student.setId(UUID.randomUUID());
        student.setName(name);
        student.setAge(age);

        repository.save(student);
        return "redirect:/student-list";
    }

    @GetMapping("/delete-student-form")
    public String deleteStudentForm() {
        return "delete-student-form";
    }

    @PostMapping("/delete-student")
    public String delete(String id, String name) {
        if (id != null && !id.isBlank()) {
            repository.deleteById(UUID.fromString(id));
        } else {
            repository.deleteByName(name);
        }

        return "redirect:/student-list";
    }

    @GetMapping("/update-student-form")
    public String updateStudentForm() {
        return "update-student-form";
    }

    @PostMapping("/update-student")
    public String update(String id, String name, Integer age) {
        var student = repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Студент не найден"));

        student.setName(name);
        student.setAge(age);
        repository.save(student);

        return "redirect:/student-list";
    }
}