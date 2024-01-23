package com.yohan.service.auth;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.yohan.service.SpringBootComponentTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

class SecurityTests extends SpringBootComponentTest {
  @Autowired private MockMvc mockMvc;

  @Test
  void testCreatingTokenWorks() throws Exception {
    // Call the endpoint and check the response
    String body =
        """
        {
             "sub": "123",
             "scope": [
                 "role1",
                 "role2",
                 "GUEST"
             ]
         }
    """;
    var resp =
        mockMvc
            .perform(post("/auth/token").contentType(MediaType.APPLICATION_JSON).content(body))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isString());
    var token = resp.andReturn().getResponse().getContentAsString();

    // Call the endpoint with the token and check the response
    mockMvc
        .perform(get("/greeting").header("Authorization", "Bearer " + token))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isString());

    // Call the same endpoint without the token and check the response
    mockMvc
        .perform(get("/greeting"))
        .andExpect(status().isUnauthorized())
        .andExpect(jsonPath("$").doesNotExist());
  }
}
