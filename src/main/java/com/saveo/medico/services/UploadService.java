package com.saveo.medico.services;

import com.saveo.medico.models.Medicine;
import com.saveo.medico.repositories.MedicineRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class UploadService {

    @Autowired
    MedicineRepository repository;

    public boolean hasCSVFormat(MultipartFile file) {
        String EXCEL_TYPE = "application/vnd.ms-excel";
        String CSV_TYPE = "text/csv";
        return CSV_TYPE.equals(file.getContentType()) || EXCEL_TYPE.equals(file.getContentType());
    }

    public void save(MultipartFile file) {
        try {
            List<Medicine> medicines = csvToMedicines(file.getInputStream());
            repository.saveAll(medicines);
        } catch (Exception e) {
            throw new RuntimeException("Failed to Store CSV Data: " + e.getMessage());
        }
    }

    public List<Medicine> csvToMedicines(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<Medicine> medicines = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
            for (CSVRecord csvRecord : csvRecords) {
                Medicine medicine = new Medicine(
                        csvRecord.get("c_name"),
                        csvRecord.get("c_batch_no"),
                        dateFormatter.parse(csvRecord.get("d_expiry_date")),
                        Integer.parseInt(csvRecord.get("n_balance_qty")),
                        csvRecord.get("c_packaging"),
                        csvRecord.get("c_unique_code"),
                        csvRecord.get("c_schemes"),
                        Double.parseDouble(csvRecord.get("n_mrp")),
                        csvRecord.get("c_manufacturer"),
                        csvRecord.get("hsn_code")
                );
                medicines.add(medicine);
            }
            return medicines;
        } catch (IOException | ParseException e) {
            throw new RuntimeException("Failed to parse: " + e.getMessage());
        }
    }
}
