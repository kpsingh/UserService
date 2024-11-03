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
    @ManyToOne
    private User user;
    private Date expiryDate;
    private Date issuedDate;

    public static Token create(User user) {
        LocalDate now = LocalDate.now();
        Date issuedDate = Date.from(now.atStartOfDay(ZoneId.systemDefault()).toInstant());

        LocalDate thirtyDayFromNow = now.plusDays(30);
        Date expirayDate = Date.from(thirtyDayFromNow.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Token token = new Token();
        token.setUser(user);
        token.setIsDeleted(Boolean.FALSE);
        token.setIssuedDate(issuedDate);
        token.setExpiryDate(expirayDate);
        // token value are randomly generated 128 bits string
        token.setValue(RandomStringUtils.secure().nextAlphanumeric(128));
        return token;
    }
}
