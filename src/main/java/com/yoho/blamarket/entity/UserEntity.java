package com.yoho.blamarket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="user")
public class UserEntity {

    @Id
    private String email;

    private String password;

    private String name;

    private String company;

    private String isEnabled;

    private String deleteFlag;

    private Date regDate;

    private Date editDate;

    public void encryptPassword(PasswordEncoder passwordEncoder){
        password = passwordEncoder.encode(password);
    }


}
