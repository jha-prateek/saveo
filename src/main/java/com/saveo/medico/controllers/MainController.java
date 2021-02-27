package com.saveo.medico.controllers;

import com.saveo.medico.dtos.OrderDTO;
import com.saveo.medico.models.Medicine;
import com.saveo.medico.models.Order;
import com.saveo.medico.services.MedicineService;
import com.saveo.medico.services.OrderService;
import com.saveo.medico.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/v1")
public class MainController {

    @Autowired
    UploadService uploadService;
    @Autowired
    MedicineService medicineService;
    @Autowired
    OrderService orderService;

    @GetMapping(value = "/test")
    public String test(){
        return "hello-world";
    }

    @PostMapping(value = "/uploadCSV")
    public ResponseEntity<String> uploadCSV(@RequestParam("file") MultipartFile csvFile){
        String responseMessage = "";
        if (uploadService.hasCSVFormat(csvFile)) {
            try {
                uploadService.save(csvFile);
                responseMessage = csvFile.getOriginalFilename() + " uploaded Successfully";
                return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
            } catch (Exception e) {
                responseMessage = "Upload Failed: File[" + csvFile.getOriginalFilename() + "] \nOriginal Error: " + e.getMessage();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(responseMessage);
            }
        }

        responseMessage = "Not a CSV file";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
    }

    @GetMapping(value = {"/getMedicineDetails", "/getMedicineDetails/{id}"})
    public ResponseEntity<Object> getMedicineDetails(@PathVariable Optional<Long> id){
        if (id.isPresent()){
            Medicine medicine = medicineService.findMedicineById(id.get());
            if(medicine != null){
                return ResponseEntity.status(HttpStatus.OK).body(medicine);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pass Id in Path Variable: getMedicineDetails/{id}");
    }

    @GetMapping(value = {"/searchMedicine", "/searchMedicine/{params}"})
    public ResponseEntity<Object> searchMedicine(@PathVariable("params") Optional<String> searchParam){
        if(searchParam.isPresent()){
            List<Medicine> result = medicineService.findMedicineByName(searchParam.get());
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pass Medicine Name in Path Variable: searchMedicine/{medicineName}");
    }

    @PostMapping(value = "/placeorder", consumes = "application/json")
    public ResponseEntity<Object> placeOrder(@RequestBody List<OrderDTO> orderDTOs){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(orderService.saveOrder(orderDTOs));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Invalid Format");
        }
    }

}
