package com.sivalabs.usermanagement.api;

import com.sivalabs.usermanagement.domain.UserRepository;
import com.sivalabs.usermanagement.domain.events.UserCreatedEvent;
import com.sivalabs.usermanagement.domain.registration.CreateUserRequest;
import com.sivalabs.usermanagement.infra.events.SpringUserEventListener;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

    @Autowired
    UserRepository userRepository;

    @MockBean
    SpringUserEventListener userEventListener;

    @Captor
    ArgumentCaptor<UserCreatedEvent> eventCaptor;

    @BeforeEach
    void setup() {
        userRepository.save(new CreateUserRequest("Admin", "admin@gmail.com", "12345678"));
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

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

    @Test
    void shouldReturnBadRequestError() throws Exception {
        String payload = """
                {
                    "name": "Admin",
                    "email": "",
                    "phone": "91919191"
                }
                """;
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload)
        ).andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUserExistsError() throws Exception {
        String payload = """
                {
                    "name": "Admin",
                    "email": "admin@gmail.com",
                    "phone": "91919191"
                }
                """;
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload)
        ).andExpect(status().isConflict());
    }
}