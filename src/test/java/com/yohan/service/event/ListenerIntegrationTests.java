package com.yohan.service.event;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.yohan.service.SpringBootComponentTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;

class ListenerIntegrationTests extends SpringBootComponentTest {

  @Autowired private MockMvc mockMvc;

  @MockitoSpyBean private Listener listener;

  @Test
  void testEventReceived() throws Exception {
    // Call /api/event
    mockMvc.perform(get("/api/event/send")).andExpect(status().isOk());
    doNothing().when(listener).handleNewRandomEvent(any(BaseEvent.NewRandomEvent.class));
  }
}
