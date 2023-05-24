package ru.practicum.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.practicum.config.WebConfig;
import org.hamcrest.Matchers.*;

import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig({UserController.class, WebConfig.class, UserControllerTestConfig.class})
public class UserControllerTestWithContext {
    private final ObjectMapper mapper = new ObjectMapper();

    private final UserService userService;

    private MockMvc mvc;

    private UserDto userDto;

    @Autowired
    public UserControllerTestWithContext(UserService userService) {
        this.userService = userService;
    }

    @BeforeEach
    void setUp(WebApplicationContext wac) {
        mvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .build();

        userDto = UserDto.builder()
                .id(1L)
                .email("john.doe@mail.com")
                .firstName("John")
                .lastName("Doe")
                .registrationDate("2022.07.03 19:55:00")
                .state(UserState.ACTIVE)
                .build();
    }

    @Test
    void saveNewUser() throws Exception {
        when(userService.saveUser(any()))
                .thenReturn(userDto);

        mvc.perform(post("/users")
                        .content(mapper.writeValueAsString(userDto))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(userDto.getId()), Long.class))
                .andExpect(jsonPath("$.firstName", Matchers.is(userDto.getFirstName())))
                .andExpect(jsonPath("$.lastName", Matchers.is(userDto.getLastName())))
                .andExpect(jsonPath("$.email", Matchers.is(userDto.getEmail())));
    }
}
