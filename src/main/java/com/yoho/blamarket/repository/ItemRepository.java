package com.yoho.blamarket.repository;

import com.yoho.blamarket.entity.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, String> {

    Page<ItemEntity> findAllByCompanyId(PageRequest registDate, long companyId);

    ItemEntity findById(long itemId);

    @Transactional
    @Modifying
    @Query("update item set viewCount = viewCount + 1 where id = :itemId")
    int updateView(long itemId);

    @Transactional
    int deleteById(long itemId);
}
