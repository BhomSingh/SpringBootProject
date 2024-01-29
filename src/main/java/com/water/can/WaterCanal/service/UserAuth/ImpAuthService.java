package com.water.can.WaterCanal.service.UserAuth;

import com.water.can.WaterCanal.bean.SignupRequest;
import com.water.can.WaterCanal.dao.AuthRepository;
import com.water.can.WaterCanal.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImpAuthService implements AuthService {

    @Autowired
    AuthRepository authRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    // getting a specific record by using the method findById() of JPA Repository
    @Override
    public Register getUserRegisterByI(int registerId) {
        return authRepository.findById(registerId).get();
    }

    @Override
    public Optional<Register> isEmailRegister(String email) {
        return authRepository.findByEmail(email);
    }

    @Override
    public Optional<Register> userID(int id) {
        return authRepository.findById(id);
    }

    // deleting a specific record by using the method deleteById() of JPA Repository
    @Override
    public void deleteRegisterID(int registerId) {
        authRepository.deleteById(registerId);
    }

    @Override
    public void deleteAll() {
        authRepository.deleteAll();
    }

    @Override
    public Register save(SignupRequest request) {
        Register register = new Register();
        register.setEmail(request.getEmail());
        register.setUserName(request.getUsername());
        register.setMobileNo(request.getMobileNo());
        register.setPassword(passwordEncoder.encode(request.getPassword()));
        register.setConfirmPassword(passwordEncoder.encode(request.getConfirmPassword()));
        return authRepository.save(register);
    }

    @Override
    public Register registerUpdate(Register register) {
        return authRepository.save(register);
    }

    @Override
    public Register passwordRest(Register register) {
        return authRepository.save(register);
    }

    @Override
    public boolean isEmailAlreadyRegistered(String email) {
        return authRepository.existsByEmail(email);
    }

    @Override
    public boolean isMobileNoAlreadyRegistered(String mobileNo) {
        return authRepository.existsByMobileNo(mobileNo);
    }

}
