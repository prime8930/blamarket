package com.yoho.blamarket.repository;

import com.yoho.blamarket.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {

    CategoryEntity findById(long id);

}
