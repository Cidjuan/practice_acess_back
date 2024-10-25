package com.acess.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "medicine")
public class Medicine {

    @Id
    @GeneratedValue
    @Column(unique = true)
    private UUID medicineId;

    @NonNull
    @Column(unique = true)
    private String healthRecord;

    private String description;
    private String manufacturer;

    @OneToOne
    @JoinColumn(name = "medicine_type_id", referencedColumnName = "medicineTypeId", nullable = false)
    private MedicineType medicineType;

}
