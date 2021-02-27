package com.saveo.medico.repositories;

import com.saveo.medico.models.Medicine;
import com.saveo.medico.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    List<Medicine> findByNameContains(String c_name);

}

