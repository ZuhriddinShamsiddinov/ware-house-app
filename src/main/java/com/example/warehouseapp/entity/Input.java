package com.example.warehouseapp.entity;

import com.example.warehouseapp.entity.abs.AbsEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Input extends AbsEntity {
    private Date date;

    @ManyToOne
    private Warehouse warehouse;

    @ManyToOne
    private Supplier supplier;

    @ManyToOne
    private Currency currency;
    @Column(nullable = false)
    private boolean active = true;

    private String factureNumber;
    private String code;
}
