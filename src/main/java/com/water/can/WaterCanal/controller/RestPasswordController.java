package com.water.can.WaterCanal.controller;

import com.water.can.WaterCanal.Common.CommonUtil;
import com.water.can.WaterCanal.bean.EmailRequest;
import com.water.can.WaterCanal.bean.ForgetPasswordRequest;
import com.water.can.WaterCanal.bean.GenericResponse;
import com.water.can.WaterCanal.bean.RestPasswordRequest;
import com.water.can.WaterCanal.model.Register;
import com.water.can.WaterCanal.service.EmailService.EmailService;
import com.water.can.WaterCanal.service.UserAuth.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class RestPasswordController {

    @Autowired
    private AuthService authService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/rest-password")
    private ResponseEntity<GenericResponse<String>> forgetPassword(@Valid @RequestBody RestPasswordRequest restPasswordRequest)
    {

        Register register = authService.userID(Integer.parseInt(restPasswordRequest.getUserId())).get();
        if(restPasswordRequest.getToken().equals(register.getPassword())) {
            if(register != null)
            {
                register.setPassword(passwordEncoder.encode(restPasswordRequest.getPassword()));
                register.setConfirmPassword(passwordEncoder.encode(restPasswordRequest.getConfirmPassword()));
                authService.registerUpdate(register);
            }
        }else {
            return ResponseEntity.ok(new GenericResponse<>(0,"Your Token In-Valid "));
        }

        return ResponseEntity.ok(new GenericResponse<>(1,"Your Password Rest Successfully"));
    }
}
