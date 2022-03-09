package com.example.warehouseapp.entity;

import com.example.warehouseapp.entity.abs.AbsNameEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "users")

public class User extends AbsNameEntity {
    private String lastName;
    private String phoneNumber;
    private String code;
    private String password;

    @ManyToMany
    private List<Warehouse> warehouseList;
}
