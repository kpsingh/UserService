package com.lld4.userservice.security.models;

import java.time.Instant;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "`authorization`")
public class Authorization {
    @Id
    @Column
    private String id;
    private String registeredClientId;
    private String principalName;
    private String authorizationGrantType;
    @Column(length = 1000)
    @Lob
    private String authorizedScopes;
    @Column(length = 4000)
    @Lob
    private String attributes;
    @Lob
    @Column(length = 500)
    private String state;
    @Lob
    @Column(length = 4000)
    private String authorizationCodeValue;
    private Instant authorizationCodeIssuedAt;
    private Instant authorizationCodeExpiresAt;
    private String authorizationCodeMetadata;
    @Lob
    @Column(length = 4000)
    private String accessTokenValue;
    private Instant accessTokenIssuedAt;
    private Instant accessTokenExpiresAt;
    @Column(length = 2000)
    @Lob
    private String accessTokenMetadata;
    private String accessTokenType;
    @Column(length = 1000)
    @Lob
    private String accessTokenScopes;

    @Column(length = 4000)
    @Lob
    private String refreshTokenValue;
    private Instant refreshTokenIssuedAt;
    private Instant refreshTokenExpiresAt;
    @Column(length = 2000)
    @Lob
    private String refreshTokenMetadata;

    @Column(length = 4000)
    @Lob
    private String oidcIdTokenValue;
    private Instant oidcIdTokenIssuedAt;
    private Instant oidcIdTokenExpiresAt;

    @Column(length = 2000)
    @Lob
    private String oidcIdTokenMetadata;
    @Column(length = 2000)
    @Lob
    private String oidcIdTokenClaims;

    @Column(length = 4000)
    @Lob
    private String userCodeValue;
    private Instant userCodeIssuedAt;
    private Instant userCodeExpiresAt;
    @Column(length = 2000)
    @Lob
    private String userCodeMetadata;

    @Column(length = 4000)
    @Lob
    private String deviceCodeValue;
    private Instant deviceCodeIssuedAt;
    private Instant deviceCodeExpiresAt;
    @Column(length = 2000)
    @Lob
    private String deviceCodeMetadata;


}