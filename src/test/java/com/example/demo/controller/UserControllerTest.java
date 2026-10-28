package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserService userService;

    private UserDto userDto;
    private final Integer licenceNumber = 123456;

    @BeforeEach
    public void setup() {
        userDto = new UserDto(licenceNumber, null);
    }

    @Test
    void givenUsers_whenFindAll_thenReturnAll() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of(userDto));

        mockMvc.perform(get("/api/user"))
                .andExpect(status().isOk()) // Code 200 OK
                .andExpect(jsonPath("$[0].licenceNumber").value(licenceNumber));

        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void givenLicenceNumber_whenCreateUser_thenUserIsCreated() throws Exception {
        when(userService.createUser(licenceNumber)).thenReturn(userDto);

        mockMvc.perform(post("/api/user/{licenceNumber}", licenceNumber))
                .andExpect(status().isCreated()) // Code 201 Created
                .andExpect(jsonPath("$.licenceNumber").value(licenceNumber));

        verify(userService, times(1)).createUser(licenceNumber);
    }

    @Test
    void givenUserDto_whenDeleteUser_thenUserIsDeleted() throws Exception {
        doNothing().when(userService).deleteUser(any(Integer.class));

        mockMvc.perform(delete("/api/user/{licenceNumber}", licenceNumber))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).deleteUser(any(Integer.class));
    }
}
