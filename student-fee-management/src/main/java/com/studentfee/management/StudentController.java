package com.studentfee.management;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    private List<Student> students = new ArrayList<>();

    @GetMapping("/")
    public String home(Model model,
            @RequestParam(required = false) Integer searchId,
            @RequestParam(required = false) String status) {

        List<Student> result = new ArrayList<>(students);

        if (searchId != null) {
            result.removeIf(s -> s.getId() != searchId);
        }

        if (status != null && !status.isEmpty()) {
            result.removeIf(s -> !s.getStatus().equals(status));
        }

        model.addAttribute("students", result);
        model.addAttribute("totalStudents", students.size());

        return "index";
    }

    @PostMapping("/add")
    public String addStudent(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam String department,
            @RequestParam double totalFee) {

        if (id <= 0 || totalFee <= 0 ||
                name.trim().isEmpty() || department.trim().isEmpty()) {
            return "redirect:/?message=Invalid student details";
        }

        for (Student s : students) {
            if (s.getId() == id) {
                return "redirect:/?message=Student ID already exists";
            }
        }

        students.add(new Student(id, name, department, totalFee));

        return "redirect:/?message=Student added successfully";
    }

    @PostMapping("/payment")
    public String recordPayment(
            @RequestParam int id,
            @RequestParam double amount) {

        for (Student s : students) {

            if (s.getId() == id) {

                if (amount > 0 && amount <= s.getBalance()) {
                    s.addPayment(amount);
                    return "redirect:/?message=Payment recorded successfully";
                }

                return "redirect:/?message=Invalid payment amount";
            }
        }

        return "redirect:/?message=Student not found";
    }
}