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
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CourseRepository courseRepository;

    @GetMapping(value="/customers")
    public ResponseEntity<List<Customer>> getAllCustomersAndFilters(
            @RequestParam(name = "courseName", required = false) String courseName,
            @RequestParam(name = "town", required = false) String town,
            @RequestParam(name = "age", required = false) Integer age

    ){
        if(age != null && town != null && courseName != null){
            return new ResponseEntity<>(customerRepository.findByAgeGreaterThanAndTownIgnoreCaseAndBookingsCourseCourseNameIgnoreCase(age, town, courseName), HttpStatus.OK);
        }
        if(town != null && courseName != null){
            return new ResponseEntity<>(customerRepository.findByTownIgnoreCaseAndBookingsCourseCourseNameIgnoreCase(town, courseName), HttpStatus.OK);
        }
        if(courseName != null){
            return new ResponseEntity<>(customerRepository.findByBookingsCourseCourseNameIgnoreCase(courseName), HttpStatus.OK);
        }
        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value="/customers/{id}")
    public ResponseEntity getCustomer(@PathVariable Long id){
        return new ResponseEntity<>(customerRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> postCustomer(@RequestBody Customer customer){
        customerRepository.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Customer> putCustomer(@RequestBody Customer customer, @PathVariable Long id){
        Customer foundCustomer = customerRepository.findById(id).get();
        foundCustomer.setCustomerName(customer.getCustomerName());
        foundCustomer.setAge(customer.getAge());
        foundCustomer.setBookings(customer.getBookings());
        foundCustomer.setTown(customer.getTown());
        customerRepository.save(foundCustomer);
        return new ResponseEntity<>(foundCustomer, HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Long> deleteCustomer(@PathVariable Long id){
        customerRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping(value = "/customers/{customerId}/courses")
    public ResponseEntity<List<Course>> getCoursesForCustomer(@PathVariable Long customerId) {
        return new ResponseEntity<>(courseRepository.findAllByBookingsCustomerId(customerId), HttpStatus.OK);
    }
}
