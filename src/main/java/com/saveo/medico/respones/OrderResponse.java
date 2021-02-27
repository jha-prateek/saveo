package com.saveo.medico.respones;

import java.util.ArrayList;
import java.util.List;

public class OrderResponse {
    Long orderId;
    List<Long> failedMedicineIds = new ArrayList<>();
    double totalAmount;

    public List<Long> getFailedMedicineIds() {
        return failedMedicineIds;
    }

    public void setFailedMedicineIds(Long failedMedicineId) {
        this.failedMedicineIds.add(failedMedicineId);
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderResponse{" +
                "orderId='" + orderId + '\'' +
                ", failedMedicines=" + failedMedicineIds.toString() +
                '}';
    }
}
