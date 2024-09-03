package com.yohan.service.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  @Value("${app.fe-base-url}")
  private String feBaseUrl;

  private final JwtGeneratorService jwtGeneratorService;

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException {
    OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
    String email = oAuth2User.getAttribute("email");
    log.info("email = {}", email);

    if (email != null) {
      var userToken =
          jwtGeneratorService.generateJWT(Map.of("sub", email, "scopes", List.of("GUEST")));

      getRedirectStrategy()
          .sendRedirect(request, response, feBaseUrl + "/auth/callback?token=" + userToken);
      return;
    }

    log.info("email is null");

    getRedirectStrategy()
        .sendRedirect(request, response, feBaseUrl + "/auth/callback-failure?error=no_email");
  }
}
