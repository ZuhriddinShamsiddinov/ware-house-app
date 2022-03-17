package com.example.warehouseapp.entity;

import com.example.warehouseapp.entity.abs.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Attachment extends AbsEntity {
    private String name;
    private String contentType;
    private long size;
}
