package com.acess.repository;

import com.acess.entity.Medicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface MedicineRepository extends JpaRepository<Medicine, UUID> {
    @Query("select m.medicineId, m.healthRecord, m.description, m.manufacturer, m.medicineType.medicineTypeId, m.medicineType.description " +
            "from Medicine m " +
            "where (:search is null or LOWER(m.medicineType.description) LIKE LOWER(CONCAT('%', :search, '%'))) " +
            "or (:search is null or LOWER(m.description) LIKE LOWER(CONCAT('%', :search, '%'))) " +
            "order by m.description asc")
    Page<Object[]> findAllMedicine(@Param("search") String search, Pageable pageable);
}
