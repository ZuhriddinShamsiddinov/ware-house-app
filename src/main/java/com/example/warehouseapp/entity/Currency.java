package com.example.warehouseapp.entity;

import com.example.warehouseapp.entity.abs.AbsNameEntity;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Entity
@EqualsAndHashCode(callSuper = true)
public class Currency extends AbsNameEntity {
}
