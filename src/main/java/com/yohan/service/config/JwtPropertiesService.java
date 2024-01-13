package com.yohan.service.config;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import java.time.Duration;
import javax.crypto.SecretKey;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtPropertiesService {

  @Value("${app.jwt.key}")
  private String key;

  @Getter
  @Value("${app.jwt.issuer}")
  private String issuer;

  @Value("${app.jwt.algorithm}")
  private String algorithm;

  @Value("${app.jwt.expiresIn}")
  private String expiresIn;

  public JWSAlgorithm getAlgorithm() {
    return JWSAlgorithm.parse(algorithm);
  }

  public SecretKey getKey() {
    var jwk = new OctetSequenceKey.Builder(key.getBytes()).algorithm(getAlgorithm()).build();
    return jwk.toSecretKey();
  }

  public Duration getExpiresIn() {
    return Duration.parse(expiresIn);
  }
}
