package com.acess.presentation.presenter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicinePresenter {

    private UUID medicineId;
    private String healthRecord;
    private String description;
    private String manufacturer;
    private MedicineTypePresenter medicineTypePresenter;

}
