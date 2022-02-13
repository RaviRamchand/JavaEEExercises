package ca.ravi.thymeleafex.controllers;

import ca.ravi.thymeleafex.beans.Course;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class CourseController {
    List<Course> courseList = new CopyOnWriteArrayList<>();

    @GetMapping("/")
    public String getIndex(Model model){
        model.addAttribute("courseList", courseList);
        return "index";
    }

    @PostMapping("/courseForm")
    public String getCourses(@RequestParam Long courseId, @RequestParam String prefix,
                             @RequestParam String code, @RequestParam String name, Model model){
        courseList.add(new Course(courseId, prefix, code, name));
        model.addAttribute("courseList", courseList);
        return "index";
    }

}
