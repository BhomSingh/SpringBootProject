package com.water.can.WaterCanal.controller;

import com.water.can.WaterCanal.Common.CommonUtil;
import com.water.can.WaterCanal.bean.EmailRequest;
import com.water.can.WaterCanal.bean.ForgetPasswordRequest;
import com.water.can.WaterCanal.bean.GenericResponse;
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
public class ForgetPasswordController {

    @Autowired
    private AuthService authService;

    @Autowired
    private EmailService emailService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/forgot-password")
    private ResponseEntity<GenericResponse<String>> forgetPassword(@Valid @RequestBody ForgetPasswordRequest forgetPasswordRequest)
    {
        if (!authService.isEmailAlreadyRegistered(forgetPasswordRequest.getEmailAddress())) {
            return ResponseEntity.ok(new GenericResponse<>(0,"Email not found!"));
        }

            Register register = authService.isEmailRegister(forgetPasswordRequest.getEmailAddress()).get();
            if(register != null)
            {

                String randomPass = passwordEncoder.encode(CommonUtil.alphaNumericString(10));
                EmailRequest emailRequest = new EmailRequest();
                emailRequest.setTo(forgetPasswordRequest.getEmailAddress());
                emailRequest.setSubject("Url Click go to Page");
                emailRequest.setBody("https://www.befiler.com/dashboard/auth/forgot-password?Code="+randomPass+"UserID&&="+register.getId());
                emailService.sendEmail(emailRequest);

                register.setPassword(randomPass);
                authService.registerUpdate(register);
            }

        return ResponseEntity.ok(new GenericResponse<>(1,"Please Check Your Email"));
    }

}
