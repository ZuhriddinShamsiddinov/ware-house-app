package com.example.warehouseapp.entity;

import com.example.warehouseapp.entity.abs.AbsEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class InputProduct extends AbsEntity {
    @OneToOne
    private Product product;

    private double amount;
    private double price;
    private Date expireDate;
    @ManyToOne
    private Input input;



}
