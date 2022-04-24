package com.sivalabs.usermanagement.api;

import com.sivalabs.usermanagement.domain.events.UserCreatedEvent;
import com.sivalabs.usermanagement.domain.events.UserEventListener;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserEventListener userEventListener;

    @Captor
    ArgumentCaptor<UserCreatedEvent> eventCaptor;

    @Test
    void shouldRegisterUserSuccessfully() throws Exception {
        String payload = """
                {
                    "name": "Siva",
                    "email": "siva@gmail.com",
                    "phone": "91919191"
                }
                """;
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload)
        ).andExpect(status().isCreated());

        Mockito.verify(userEventListener).handleUserCreatedEvent(eventCaptor.capture());
        UserCreatedEvent event = eventCaptor.getValue();
        assertThat(event.getName()).isEqualTo("Siva");
    }
}