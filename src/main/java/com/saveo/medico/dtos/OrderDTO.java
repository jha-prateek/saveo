package com.saveo.medico.dtos;

public class OrderDTO {
    private long c_unique_id;
    private int quantity;
    private String c_name;

    public long getC_unique_id() {
        return c_unique_id;
    }

    public void setC_unique_id(long c_unique_id) {
        this.c_unique_id = c_unique_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public OrderDTO(long c_unique_id, int quantity, String c_name) {
        this.c_unique_id = c_unique_id;
        this.quantity = quantity;
        this.c_name = c_name;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "c_unique_id=" + c_unique_id +
                ", quantity=" + quantity +
                ", c_name='" + c_name + '\'' +
                '}';
    }
}


