package com.water.can.WaterCanal.dao;

import com.water.can.WaterCanal.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface FileRepository extends CrudRepository<FileEntity, Long> {
    // additional methods if needed
}