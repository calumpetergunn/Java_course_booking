package com.codeclan.example.coursebooking.components;

import com.codeclan.example.coursebooking.models.Booking;
import com.codeclan.example.coursebooking.models.Course;
import com.codeclan.example.coursebooking.models.Customer;
import com.codeclan.example.coursebooking.repositories.BookingRepository;
import com.codeclan.example.coursebooking.repositories.CourseRepository;
import com.codeclan.example.coursebooking.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    CourseRepository courseRepository;

    public DataLoader(){

    }

    public void run(ApplicationArguments args){

        Course course1 = new Course("Crystals and Stuff", "Slough", 1);
        courseRepository.save(course1);

        Course course2 = new Course("Mindfulness and also Crystals", "Atlantis", 5);
        courseRepository.save(course2);

        Course course3 = new Course("Reiki and Crystals", "Portobello", 4);
        courseRepository.save(course3);

        Customer customer1 = new Customer("Skaiyy", "Skye", 22);
        customerRepository.save(customer1);

        Customer customer2 = new Customer("Pete", "Edinburgh", 34);
        customerRepository.save(customer2);

        Customer customer3 = new Customer("Calum", "Matamata", 33);
        customerRepository.save(customer3);

        Booking booking1 = new Booking("22-01-2022", customer1, course1);
        bookingRepository.save(booking1);

        Booking booking2 = new Booking("28-09-2021", customer2, course2);
        bookingRepository.save(booking2);

        Booking booking3 = new Booking("01-01-2022", customer3, course3);
        bookingRepository.save(booking3);

        Booking booking4 = new Booking("25-12-1995", customer2, course1);
        bookingRepository.save(booking4);

        Booking booking5 = new Booking("25-11-2011", customer3, course1);
        bookingRepository.save(booking5);

        Booking booking6 = new Booking("10-12-1905", customer1, course2);
        bookingRepository.save(booking6);

        Booking booking7 = new Booking("10-12-1905", customer2, course3);
        bookingRepository.save(booking7);



    }
}
