package com.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.CustomerDto;
import com.app.dto.CustomerLoginDto;
import com.app.model.Customer;
import com.app.security.CustomUserDetailService;
import com.app.security.JwtTokenHelper;
import com.app.services.ICustomerService;
import com.app.services.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.OPTIONS })
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private HttpSession session;

    @Autowired
    private CustomUserDetailService userDetailsService;

    @PostMapping(value = "/register")
    @CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.POST })
    public ResponseEntity<CustomerDto> registerCustomer(@RequestBody CustomerDto customerDto) {
        System.out.println(customerDto);
        customerService.registerCustomer(customerDto);
        return ResponseEntity.ok(customerDto);
    }

    @PostMapping(value = "/login")
    @CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.POST })
    public ResponseEntity<?> authenticate(@RequestBody CustomerLoginDto loginDto) {
        String email = loginDto.getEmail();
        String password = loginDto.getPassword();

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getEmail());
        String token = jwtTokenHelper.generateToken(userDetails);
        System.out.println(token);

        Customer authenticatedCustomer = customerService.authenticateCustomer(email, password);

        if (authenticatedCustomer != null) {
            return ResponseEntity.ok(new ApiResponse(true, authenticatedCustomer.getName(), token));
        } else {
            return ResponseEntity.ok(new ApiResponse(false, null, null));
        }
    }

    @GetMapping
    public ResponseEntity<?> checkAuthentication(HttpSession session) {
        Customer authenticatedCustomer = (Customer) session.getAttribute("authenticatedCustomer");

        if (authenticatedCustomer != null) {
            return ResponseEntity.ok(new ApiResponse(true, authenticatedCustomer.getName()));
        } else {
            return ResponseEntity.ok(new ApiResponse(false, null));
        }
    }
}
