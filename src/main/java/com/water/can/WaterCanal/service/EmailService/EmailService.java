package com.water.can.WaterCanal.service.EmailService;

import com.water.can.WaterCanal.bean.EmailRequest;
import org.springframework.stereotype.Service;

public interface EmailService {

    void sendEmail(EmailRequest request);

}
