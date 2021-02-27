package com.saveo.medico.services;

import com.saveo.medico.models.Medicine;
import com.saveo.medico.repositories.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MedicineService {

    @Autowired
    MedicineRepository repository;

    public Medicine findMedicineById(Long id){
        Optional<Medicine> medicine = repository.findById(id);
        return medicine.orElse(null);
    }

    public List<Medicine> findMedicineByName(String searchParam){
        return repository.findByNameContains(searchParam);
    }

    public boolean canPlaceOrder(Medicine medicine, int quantity){
        return checkExpiryDate(medicine) && updateMedicineQuantity(medicine, quantity);
    }

    public boolean checkExpiryDate(Medicine medicine){
        Date currentDate = java.sql.Date.valueOf(LocalDate.now().plusMonths(3));
        return !currentDate.after(medicine.getExpiryDate());
    }

    public boolean updateMedicineQuantity(Medicine medicine, int quantity){
        if(medicine.getBalanceQty() >= quantity){
            medicine.setBalanceQty(medicine.getBalanceQty() - quantity);
            repository.save(medicine);
            return true;
        }
        return false;
    }

}
