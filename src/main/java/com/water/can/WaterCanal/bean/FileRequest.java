package com.water.can.WaterCanal.bean;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class FileRequest {

    private String filename;

    private String filetype;

    private String base64;


}
