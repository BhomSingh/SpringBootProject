package com.water.can.WaterCanal.bean;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProductBean {

    private String productName;

    private String price;

    private String minimum_order;

    private boolean subscribe;

    private String url;

}
