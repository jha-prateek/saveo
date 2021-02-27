package com.saveo.medico.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "medicines",
        uniqueConstraints = @UniqueConstraint(columnNames = {"c_batch_no", "c_unique_code"})
)
public class Medicine {

    @Id
    @Column(name = "c_unique_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "c_name", nullable = false)
    private String name;

    @Column(name = "c_batch_no", nullable = false)
    private String batchNumber;

    @Column(name = "d_expiry_date")
    private Date expiryDate;

    @Column(name = "n_balance_qty")
    private int balanceQty;

    @Column(name = "c_packaging")
    private String packaging;

    @Column(name = "c_unique_code", nullable = false)
    private String uniqueCode;

    @Column(name = "c_schemes")
    private String schemes;

    @Column(name = "n_mrp", nullable = false)
    private double mrp;

    @Column(name = "c_manufacturer")
    private String manufacturer;

    @Column(name = "hsn_code")
    private String hsnCode;

    public Medicine() {
    }

    public Medicine(String name, String batchNumber, Date expiryDate, int balanceQty,
                    String packaging, String uniqueCode, String schemes,
                    double mrp, String manufacturer, String hsnCode) {
        this.name = name;
        this.batchNumber = batchNumber;
        this.expiryDate = expiryDate;
        this.balanceQty = balanceQty;
        this.packaging = packaging;
        this.uniqueCode = uniqueCode;
        this.schemes = schemes;
        this.mrp = mrp;
        this.manufacturer = manufacturer;
        this.hsnCode = hsnCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getBalanceQty() {
        return balanceQty;
    }

    public void setBalanceQty(int balanceQty) {
        this.balanceQty = balanceQty;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public String getSchemes() {
        return schemes;
    }

    public void setSchemes(String schemes) {
        this.schemes = schemes;
    }

    public double getMrp() {
        return mrp;
    }

    public void setMrp(double mrp) {
        this.mrp = mrp;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", batchNumber='" + batchNumber + '\'' +
                ", expiryDate=" + expiryDate +
                ", balanceQty=" + balanceQty +
                ", packaging='" + packaging + '\'' +
                ", uniqueCode='" + uniqueCode + '\'' +
                ", schemes='" + schemes + '\'' +
                ", mrp=" + mrp +
                ", manufacturer='" + manufacturer + '\'' +
                ", hsnCode='" + hsnCode + '\'' +
                '}';
    }
}
