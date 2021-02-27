package com.saveo.medico.services;

import com.saveo.medico.dtos.OrderDTO;
import com.saveo.medico.models.Medicine;
import com.saveo.medico.models.Order;
import com.saveo.medico.models.OrderDetail;
import com.saveo.medico.repositories.MedicineRepository;
import com.saveo.medico.repositories.OrderDetailsRepository;
import com.saveo.medico.repositories.OrderRepository;
import com.saveo.medico.respones.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    MedicineService medicineService;

    public OrderResponse saveOrder(List<OrderDTO> orderDTOs){
        OrderResponse orderResponse = new OrderResponse();
        Order order = new Order();
        List<OrderDetail> orderDetails = new ArrayList<>();
        double total = 0;
        for (OrderDTO orderDTO : orderDTOs) {
            Medicine medicine = medicineService.findMedicineById(orderDTO.getC_unique_id());
            if(medicineService.canPlaceOrder(medicine, orderDTO.getQuantity())){
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setQuantity(orderDTO.getQuantity());
                orderDetail.setMedicine(medicine);
                orderDetail.setOrder(order);
                total += medicine.getMrp() * orderDTO.getQuantity();
                orderDetails.add(orderDetail);
            }
            else{
                orderResponse.setFailedMedicineIds(medicine.getId());
            }
        }
        if (orderDetails.size() > 0){
            order.setAmount(total);
            order.setOrderDetails(orderDetails);
            Order savedResult = orderRepository.save(order);
            orderResponse.setOrderId(savedResult.getId());
            orderResponse.setTotalAmount(savedResult.getAmount());
        }
        return orderResponse;
    }
}
