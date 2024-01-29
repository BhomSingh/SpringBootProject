package com.water.can.WaterCanal.bean;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProductSessionBean {

    private String productSessionName;

    private String productDec;

    private String originalPrice;

    private String DiscountPrice;

    private String url;

}
