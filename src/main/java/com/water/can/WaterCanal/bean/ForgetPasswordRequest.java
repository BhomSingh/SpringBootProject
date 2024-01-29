package com.water.can.WaterCanal.bean;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ForgetPasswordRequest {

    public String emailAddress;

    public String url;

}
