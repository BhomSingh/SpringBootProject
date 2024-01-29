package com.water.can.WaterCanal.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterBean {

    private Integer id;

    @NotBlank(message = "Username cannot be blank")
    private String email;

    @NotBlank(message = "Username cannot be blank")
    private String userName;

    @NotBlank(message = "Username cannot be blank")
    private String mobileNo;

    @NotBlank(message = "Username cannot be blank")
    private String password;

    @NotBlank(message = "Username cannot be blank")
    private String confirmPassword;

}
