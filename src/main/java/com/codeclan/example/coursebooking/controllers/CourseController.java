package com.codeclan.example.coursebooking.controllers;

import com.codeclan.example.coursebooking.models.Course;
import com.codeclan.example.coursebooking.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    CourseRepository courseRepository;

    @GetMapping(value="/courses")
    public ResponseEntity<List<Course>> getAllCoursesAndFilters(
            @RequestParam(required = false, name = "rating") Integer rating,
            @RequestParam(required = false, name = "customerName") String customerName
    ){
        if(rating != null){
            return new ResponseEntity(courseRepository.findByRating(rating), HttpStatus.OK);
        }
        if(customerName != null){
            return new ResponseEntity(courseRepository.findByBookingsCustomerCustomerNameIgnoreCase(customerName),
                 HttpStatus.OK);
        }
        return new ResponseEntity<>(courseRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value="/courses/{id}")
    public ResponseEntity getCourse(@PathVariable Long id){
        return new ResponseEntity<>(courseRepository.findById(id), HttpStatus.OK);
    }
}
