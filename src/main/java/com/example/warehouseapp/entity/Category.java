package com.example.warehouseapp.entity;

import com.example.warehouseapp.entity.abs.AbsNameEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Category extends AbsNameEntity {
    @ManyToOne
    private Category parentCategory;
}
