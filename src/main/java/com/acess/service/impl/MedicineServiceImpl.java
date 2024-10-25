package com.acess.service.impl;

import com.acess.entity.Medicine;
import com.acess.entity.MedicineType;
import com.acess.presentation.presenter.MedicinePresenter;
import com.acess.presentation.presenter.MedicineTypePresenter;
import com.acess.presentation.presenter.Paginator;
import com.acess.repository.MedicineRepository;
import com.acess.repository.MedicineTypeRepository;
import com.acess.service.MedicineService;
import liquibase.repackaged.net.sf.jsqlparser.util.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {
    private final MedicineRepository medicineRepository;
    private final MedicineTypeRepository medicineTypeRepository;

    @Override
    public Medicine saveOrUpdate(MedicinePresenter medicinePresenter) {
        Medicine medicine;


        if (medicinePresenter.getMedicineId() == null) {
            medicine = new Medicine();
            medicine.setMedicineId(UUID.fromString(UUID.randomUUID().toString()));
        } else {
            Optional<Medicine> existingMedicine = medicineRepository.findById(medicinePresenter.getMedicineId());
            if (existingMedicine.isPresent()) {
                medicine = existingMedicine.get();
            } else {
                medicine = new Medicine();
                medicine.setMedicineId(medicinePresenter.getMedicineId());
            }
        }

        if (medicinePresenter.getHealthRecord() == null || medicinePresenter.getHealthRecord().trim().isEmpty()) {
            throw new ValidationException("El campo Health Record es requerido.");
        }

        if (medicinePresenter.getManufacturer() == null || medicinePresenter.getManufacturer().trim().isEmpty()) {
            throw new ValidationException("El campo Manufacturer es requerido.");
        }

        if (medicinePresenter.getMedicineTypePresenter() == null ||
                medicinePresenter.getMedicineTypePresenter().getMedicineTypeId() == null) {
            throw new ValidationException("El campo Medicine Type es requerido.");
        }


        medicine.setHealthRecord(medicinePresenter.getHealthRecord());
        medicine.setDescription(medicinePresenter.getDescription());
        medicine.setManufacturer(medicinePresenter.getManufacturer());

        MedicineType medicineType = medicineTypeRepository.findById(medicinePresenter.getMedicineTypePresenter().getMedicineTypeId())
                .orElseThrow(() -> new ValidationException("Tipo de medicina no encontrada"));

        medicine.setMedicineType(medicineType);

        return medicineRepository.save(medicine);
    }

    @Override
    public Paginator findAllMedicine(Integer page, Integer size, String search) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Object[]> resultPage = medicineRepository.findAllMedicine(search, pageable);

        List<MedicinePresenter> collect = resultPage.getContent().stream()
                .map(this::buildMedicinePrsenterFromObjectArray)
                .collect(Collectors.toList());

        return Paginator.builder()
                .size((long) size)
                .page(page)
                .data(collect)
                .totalElements(resultPage.getTotalElements())
                .totalPages(resultPage.getTotalPages())
                .build();
    }

    @Override
    public void deleteById(UUID medicineId) {
        Medicine medicine = medicineRepository.findById(medicineId)
                .orElseThrow(() -> new ValidationException("Este medicamento ya no existe" + medicineId));
        medicineRepository.delete(medicine);
    }

    private MedicinePresenter buildMedicinePrsenterFromObjectArray(Object[] objectArray) {

        return MedicinePresenter.builder()
                .medicineId((UUID) objectArray[0])
                .healthRecord((String) objectArray[1])
                .description((String) objectArray[2])
                .manufacturer((String) objectArray[3])
                .medicineTypePresenter(buildMedicineTypePresenterFromObjectArray(objectArray))
                .build();
    }

    private MedicineTypePresenter buildMedicineTypePresenterFromObjectArray(Object[] objectArray) {

        return MedicineTypePresenter.builder()
                .medicineTypeId((UUID) objectArray[4])
                .description((String) objectArray[5])
                .build();
    }
}
