package com.acess.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "medicinetype")
public class MedicineType {

    @Id
    @GeneratedValue
    @Column(unique = true)
    private UUID medicineTypeId;
    private String description;

}
