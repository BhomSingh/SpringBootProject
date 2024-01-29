package com.water.can.WaterCanal.dao;

import com.water.can.WaterCanal.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Register,Integer> {

    boolean existsByEmail(String name);

    boolean existsByMobileNo(String mobileNo);

    Optional<Register> findByEmail(String email);

    Optional<Register> findById(int id);

}
