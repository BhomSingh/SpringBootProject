package com.water.can.WaterCanal.service.UserAuth;

import com.water.can.WaterCanal.dao.AuthRepository;
import com.water.can.WaterCanal.model.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Implement the logic to load user details from your data source
        // For example, you might fetch user details from a database
        // Return an instance of UserDetails (e.g., org.springframework.security.core.userdetails.User)
        // based on the retrieved user information.
        // If the user is not found, throw UsernameNotFoundException.

        Register register = authRepository.findByEmail(username).orElseThrow(()-> new RuntimeException("not Found Record"));

        return register;
    }
}