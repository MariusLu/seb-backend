package com.example.sebapplication.Controller;

import com.example.sebapplication.Entity.Customer;
import org.apache.commons.validator.EmailValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CustomerController {

    LinkedList<Customer> customerList = new LinkedList<Customer>();

    @RequestMapping(
            value = "/createCustomer",
            method = POST,
            headers = "Accept=application/json")
    @ResponseBody
    ResponseEntity<String> createNewCustomer(@RequestBody Customer customer) {
        if (customer.getName() == null ||
                customer.getSurname() == null ||
                customer.getBirthDate() == null ||
                customer.getEmail() == null ||
                customer.getTelephoneNumber() == null) {
            return ResponseEntity.badRequest().body("Empty fields");
        }
        if (customer.getName().length() < 2 || customer.getName().length() > 30) {
            return ResponseEntity.badRequest().body("Incorrect name");
        }
        if (customer.getSurname().length() < 2 || customer.getSurname().length() > 30) {
            return ResponseEntity.badRequest().body("Incorrect surname");
        }
        if (!EmailValidator.getInstance().isValid(customer.getEmail())) {
            return ResponseEntity.badRequest().body("Incorrect email");
        }
        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-mm-dd");
            dateFormat.parse(customer.getBirthDate());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Incorrect birth date");
        }
        if (customer.getTelephoneNumber().matches(".*[a-z].*")) {
            return ResponseEntity.badRequest().body("Incorrect telephone number");
        }
        customerList.add(customer);
        return ResponseEntity.ok().body("Created");
    }
    @RequestMapping(
            value = "/getCustomer",
            method = GET)
    @ResponseBody
    ResponseEntity<?> getCustomers(){
       return ResponseEntity.ok().body(customerList);
    }

}
