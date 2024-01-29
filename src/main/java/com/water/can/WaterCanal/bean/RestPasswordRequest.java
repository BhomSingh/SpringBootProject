package com.water.can.WaterCanal.bean;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RestPasswordRequest {

    private String userId;

    private String token;

    private String password;

    private String confirmPassword;

}
