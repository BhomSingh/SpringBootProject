package com.water.can.WaterCanal.config;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JwtResponse {

   private String jwtToken;

   private String username;

   private String email;

   private String mobileNo;

}
