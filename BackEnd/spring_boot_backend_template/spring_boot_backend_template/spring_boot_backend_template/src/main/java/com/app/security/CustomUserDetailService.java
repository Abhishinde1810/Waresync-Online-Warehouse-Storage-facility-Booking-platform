package com.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.model.Customer;
import com.app.model.Owner;
import com.app.repository.CustomerRepository;
import com.app.repository.OwnerRepository;
@Service
public class CustomUserDetailService implements UserDetailsService{
    @Autowired
	private OwnerRepository repo;
    
    @Autowired
    private CustomerRepository cusrepo;
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Owner owner = repo.findByEmail(username).orElse(null);
        if (owner != null) {
            return owner;
        }

        Customer customer = cusrepo.findByEmail(username).orElse(null);
        if (customer != null) {
            return customer;
        }

        throw new UsernameNotFoundException("User not found with email: " + username);
    }
}
