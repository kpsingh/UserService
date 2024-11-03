package com.lld4.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.time.ZoneId;
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
        LocalDate now = LocalDate.now();
        LocalDate thirtyDayFromNow = now.plusDays(30);
        Date expirayDate = Date.from(thirtyDayFromNow.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Token token = new Token();
        token.setExpiryDate(expirayDate);
        // token value are randemoly generated 128 bits string
        token.setValue(RandomStringUtils.secure().nextAlphanumeric(128));
        token.setUserid(user.getId());

        return token;
    }
}
