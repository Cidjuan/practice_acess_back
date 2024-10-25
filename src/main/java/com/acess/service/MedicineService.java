package com.acess.service;

import com.acess.entity.Medicine;
import com.acess.presentation.presenter.MedicinePresenter;
import org.springframework.stereotype.Service;


import com.acess.presentation.presenter.Paginator;

import java.util.UUID;

@Service
public interface MedicineService {

    Medicine saveOrUpdate(MedicinePresenter medicinePresenter);

    Paginator findAllMedicine(Integer page, Integer size, String search);

    void deleteById(UUID medicineId);
}
