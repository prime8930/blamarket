package com.yoho.blamarket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="category")
public class CategoryEntity {

    @Id
    long id;

    @Column(nullable = false)
    String name;

    long parentCategory;
}
