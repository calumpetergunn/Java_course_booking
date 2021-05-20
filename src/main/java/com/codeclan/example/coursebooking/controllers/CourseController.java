package com.codeclan.example.coursebooking.controllers;

import com.codeclan.example.coursebooking.models.Course;
import com.codeclan.example.coursebooking.models.Customer;
import com.codeclan.example.coursebooking.repositories.CourseRepository;
import com.codeclan.example.coursebooking.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CustomerRepository customerRepository;

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

    @PostMapping("/courses")
    public ResponseEntity<Course> postCourse(@RequestBody Course course){
        courseRepository.save(course);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

    @PutMapping(value="/courses/{id}")
    public ResponseEntity<Course> putCourse(@RequestBody Course course, @PathVariable Long id){
        Course courseToUpdate = courseRepository.findById(id).get();
        courseToUpdate.setCourseName(course.getCourseName());
        courseToUpdate.setBookings(course.getBookings());
        courseToUpdate.setRating(course.getRating());
        courseToUpdate.setTown(course.getTown());
        courseRepository.save(courseToUpdate);
        return new ResponseEntity<>(courseToUpdate, HttpStatus.OK);
    }

    @DeleteMapping(value="/courses/{id}")
    public ResponseEntity<Long> deleteCourse(@PathVariable Long id){
        courseRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
