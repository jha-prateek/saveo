package com.saveo.medico.models;

import javax.persistence.*;

@Entity
@Table(name = "orderDetails")
public class OrderDetail {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "quantity", nullable = false,
            columnDefinition = "integer default 1")
    private int quantity;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Order order;

    @OneToOne
    @JoinColumn(name="c_unique_id", nullable = false)
    private Medicine medicine;

    public OrderDetail() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", order=" + order +
                ", medicine=" + medicine +
                '}';
    }
}

