package com.example.warehouseapp.entity;

import com.example.warehouseapp.entity.abs.AbsEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class OutputProduct extends AbsEntity {

    @OneToOne
    private Product product;
    private Double amount;
    private Double price;

    @ManyToOne
    private Output output;


}
