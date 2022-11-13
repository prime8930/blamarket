package com.yoho.blamarket.repository;

import com.yoho.blamarket.entity.CompanyEntity;
import com.yoho.blamarket.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, String> {

    List<CompanyEntity> findAll();

    CompanyEntity findByAccessDomain(String accessDomain);

    List<CompanyEntity> findTopByOrderByIdDesc();


}
