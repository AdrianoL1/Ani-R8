package com.adrianoL.config.properties_metadata;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "rsa")
public record TokenPropertiesConfig(RsaKeyProperties rsa, AuthenticationToken accessToken) {

    public record RsaKeyProperties(RSAPublicKey publicKey, RSAPrivateKey privateKey){}
    public record AuthenticationToken (Long expirationTime) {}
}
