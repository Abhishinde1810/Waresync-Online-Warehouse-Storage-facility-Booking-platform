package com.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.OwnerLoginDto;
import com.app.security.CustomUserDetailService;
import com.app.security.JwtAuthResponse;
import com.app.security.JwtTokenHelper;

@RestController
@RequestMapping("/api")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private CustomUserDetailService userDetailsService;
	
	@Autowired
	private AuthenticationManager authManager;
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody OwnerLoginDto request){
		
		this.authenticate(request.getEmail(),request.getPassword());
		UserDetails userDetails=this.userDetailsService.loadUserByUsername(request.getEmail());
		String token=this.jwtTokenHelper.generateToken(userDetails);
		
		JwtAuthResponse res=new JwtAuthResponse();
		res.setToken(token);
		return new ResponseEntity<JwtAuthResponse>(res,HttpStatus.OK); 
	}
	private void authenticate(String email, String password) {
		UsernamePasswordAuthenticationToken user=new UsernamePasswordAuthenticationToken(email,password);
		try {
			this.authManager.authenticate(user);
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	@PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        // Clear the authentication from SecurityContextHolder
        SecurityContextHolder.clearContext();
        
        // Invalidate the session
        new SecurityContextLogoutHandler().logout(request, response, null);

        return ResponseEntity.ok().build();
    }
}
