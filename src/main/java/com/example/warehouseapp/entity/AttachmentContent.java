package com.example.warehouseapp.entity;

import com.example.warehouseapp.entity.abs.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttachmentContent extends AbsEntity {

    @ManyToOne
    private Attachment attachment;

    private byte[] bytes;
}
