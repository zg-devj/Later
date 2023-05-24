package ru.practicum.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.practicum.exception.InvalidArgumentExceptionHandler;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController controller;

    private final ObjectMapper mapper = new ObjectMapper();

    private MockMvc mvc;
    private UserDto userDto;
    private UserDto userDto2;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(InvalidArgumentExceptionHandler.class)
                .build();

        userDto = UserDto.builder()
                .id(1L)
                .email("john.doe@mail.com")
                .firstName("John")
                .lastName("Doe")
                .registrationDate("2022.07.03 19:55:00")
                .state(UserState.ACTIVE)
                .build();

        userDto2 = UserDto.builder()
                .id(2L)
                .email("user@example.com")
                .firstName("Tom")
                .lastName("Hank")
                .registrationDate("2022.07.03 19:55:00")
                .state(UserState.ACTIVE)
                .build();
    }

    @Test
    void saveNewUserWithException() throws Exception {
        Mockito.when(userService.saveUser(Mockito.any()))
                .thenThrow(IllegalArgumentException.class);

        mvc.perform(post("/users")
                        .content(mapper.writeValueAsString(userDto))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(500));
    }

    @Test
    void getAllUsers() throws Exception {
        Mockito.when(userService.getAllUsers())
                .thenReturn(List.of(userDto, userDto2));

        mvc.perform(get("/users")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(Arrays.asList(userDto, userDto2))));
    }

    @Test
    void saveNewUser() throws Exception {
        Mockito.when(userService.saveUser(Mockito.any()))
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