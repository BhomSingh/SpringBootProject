package com.water.can.WaterCanal.service.UserAuth;

import com.water.can.WaterCanal.bean.SignupRequest;
import com.water.can.WaterCanal.model.*;

import java.util.List;
import java.util.Optional;

public interface AuthService {

    Register save(SignupRequest request);

    Register registerUpdate(Register register);

    Register passwordRest(Register register);

    Register getUserRegisterByI(int registerId);

    Optional<Register> isEmailRegister(String email);

    Optional<Register> userID(int id);

    void deleteRegisterID(int registerId);

    void deleteAll();

    boolean isEmailAlreadyRegistered(String email);

    boolean isMobileNoAlreadyRegistered(String mobileNo);

}  