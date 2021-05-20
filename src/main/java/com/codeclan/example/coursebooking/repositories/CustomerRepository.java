package com.codeclan.example.coursebooking.repositories;

import com.codeclan.example.coursebooking.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByBookingsCourseCourseNameIgnoreCase(String courseName);

    List<Customer> findByTownIgnoreCaseAndBookingsCourseCourseNameIgnoreCase(String town, String courseName);

    List<Customer> findByAgeGreaterThanAndTownIgnoreCaseAndBookingsCourseCourseNameIgnoreCase(int age, String town, String courseName);
}
