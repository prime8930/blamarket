package com.yoho.blamarket.repository;

import com.yoho.blamarket.vo.UserVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserVo, String> {

    List<UserVo> findAll();

    UserVo findByEmail(String email);


}
