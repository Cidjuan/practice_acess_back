package com.acess.repository;

import com.acess.entity.MedicineType;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MedicineTypeRepository extends CrudRepository <MedicineType, UUID> {

}
