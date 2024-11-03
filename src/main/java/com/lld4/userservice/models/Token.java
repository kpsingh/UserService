package com.lld4.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Token extends BaseModel {
    private String value;
    private Long userid;
    private Date expiryDate;
    private boolean isExpired;
    private Date issuedDate;

    public static Token create(User user) {
        Token token = new Token();
        token.setUserid(user.getId());
        token.setValue("MyToken " + user.getHashedPassword());

        Date date = new Date();
        token.setIssuedDate(date); // token creation date

        date.setTime(date.getTime() + 30 + 24 * 60 * 60 * 1000);
        token.setExpiryDate(date); // expiry date 30 days after creation

        return token;
    }
}
