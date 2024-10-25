package com.acess.presentation.controller;

import com.acess.entity.Medicine;
import com.acess.presentation.presenter.MedicinePresenter;
import com.acess.presentation.presenter.Paginator;
import com.acess.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MedicineController {
    private final MedicineService medicineService;


    @PostMapping("/save")
    public Medicine save(@RequestBody MedicinePresenter medicinePresenter) {
        return medicineService.saveOrUpdate(medicinePresenter);
    }

    @GetMapping("/medicineBySearch")
    public Paginator findMedicine(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "search", defaultValue = "") String search
    ) {
        return medicineService.findAllMedicine(page, size, search);
    }

    @DeleteMapping("/deleteMedicine")
    public ResponseEntity<String> deleteMedicine(@RequestParam UUID medicineId) {
        medicineService.deleteById(medicineId);
        return ResponseEntity.ok("Medicamento eliminado exitosamente");
    }

}
