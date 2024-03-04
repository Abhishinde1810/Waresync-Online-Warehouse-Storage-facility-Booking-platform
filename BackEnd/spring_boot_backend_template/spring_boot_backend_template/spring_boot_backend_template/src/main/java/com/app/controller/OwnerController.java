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
import com.app.dto.OwnerDto;
import com.app.dto.OwnerLoginDto;
import com.app.model.Owner;
import com.app.security.CustomUserDetailService;
import com.app.security.JwtTokenHelper;
import com.app.services.IOwnerService;

import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/owner")
@NoArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.OPTIONS})

public class OwnerController {
	
	@Autowired
	private IOwnerService serv;
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	@Autowired
	private HttpSession session;
	@Autowired
	private CustomUserDetailService userDetailsService;
//	  public void addCorsMappings(CorsRegistry registry) {
//	        registry.addMapping("/**")
//	            .allowedOrigins("http://localhost:3000") // Add the URL of your React app
//	            .allowedMethods("GET", "POST", "PUT", "DELETE")
//	            .allowCredentials(true);
//	    }
	
	@PostMapping(value="/addowner")
	@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.POST})
	public ResponseEntity<OwnerDto> registerOwner(@RequestBody OwnerDto owner){
	    System.out.println(owner);
	    serv.addOwner(owner);
	    return ResponseEntity.ok(owner);
	}
	
	@PostMapping(value="/authowner")
	@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.POST})
    public ResponseEntity<?> authenticate(@RequestBody OwnerLoginDto user) {
        String email = user.getEmail();
        String password = user.getPassword();
        System.out.println(email+password);
        UserDetails userDetails=this.userDetailsService.loadUserByUsername(user.getEmail());
        String token=jwtTokenHelper.generateToken(userDetails);
        System.out.println(token);
        Owner authenticatedOwner = serv.authenticateUser(email, password);
        
        if (authenticatedOwner != null) {
            // Set the user in the session
           // session.setAttribute("authenticatedUser", authenticatedOwner);

            return ResponseEntity.ok(new ApiResponse(true, authenticatedOwner.getOwnerName(),token));
        } else {
            // User authentication failed
            return ResponseEntity.ok(new ApiResponse(false, null,null));
        }
    }

    @GetMapping
    public ResponseEntity<?> checkAuthentication(HttpSession session) {
        Owner authenticatedUser = (Owner) session.getAttribute("authenticatedUser");

        if (authenticatedUser != null) {
            return ResponseEntity.ok(new ApiResponse(true, authenticatedUser.getOwnerName()));
        } else {
            return ResponseEntity.ok(new ApiResponse(false, null));
        }
    }
}