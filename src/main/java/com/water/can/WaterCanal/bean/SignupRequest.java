package com.water.can.WaterCanal.bean;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SignupRequest {

    private String username;

    private String email;

    private String mobileNo;

    private String password;

    private String confirmPassword;

}
