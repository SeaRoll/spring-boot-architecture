package com.yohan.service.auth;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final JwtGeneratorService jwtGeneratorService;

  @PostMapping(path = "/token")
  public String getToken(@RequestBody Map<String, Object> claims) {
    return jwtGeneratorService.generateJWT(claims);
  }
}
