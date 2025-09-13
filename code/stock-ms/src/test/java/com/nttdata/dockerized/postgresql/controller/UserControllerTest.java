package com.nttdata.dockerized.postgresql.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private User user1;
    private User user2;

    @BeforeEach
    void setup() {
        user1 = new User();
        user1.setId(1L);
        user1.setName("Alex");
        user1.setEmail("alex@test.com");
        user1.setActive(true);

        user2 = new User();
        user2.setId(2L);
        user2.setName("John");
        user2.setEmail("john@test.com");
        user2.setActive(false);
    }

    @Test
    void testGetAllUsers() throws Exception {
        List<User> users = Arrays.asList(user1, user2);
        Mockito.when(userService.listAll()).thenReturn(users);

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(user1.getId()))
                .andExpect(jsonPath("$[0].name").value(user1.getName()))
                .andExpect(jsonPath("$[0].email").value(user1.getEmail()))
                .andExpect(jsonPath("$[0].status").value("Active"))
                .andExpect(jsonPath("$[1].status").value("Inactive"));
    }

    @Test
    void testGetUserById() throws Exception {
        Mockito.when(userService.findById(1L)).thenReturn(user1);

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user1.getId()))
                .andExpect(jsonPath("$.name").value(user1.getName()))
                .andExpect(jsonPath("$.email").value(user1.getEmail()))
                .andExpect(jsonPath("$.status").value("Active"));
    }

    @Test
    void testSaveUser() throws Exception {
        UserSaveRequestDto requestDto = new UserSaveRequestDto();
        requestDto.setName("Alex");
        requestDto.setEmail("alex@test.com");

        Mockito.when(userService.save(any(User.class))).thenReturn(user1);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user1.getId()))
                .andExpect(jsonPath("$.name").value(user1.getName()))
                .andExpect(jsonPath("$.email").value(user1.getEmail()));
    }

    @Test
    void testUpdateUser() throws Exception {
        UserSaveRequestDto requestDto = new UserSaveRequestDto();
        requestDto.setName("Alex Updated");
        requestDto.setEmail("alex.updated@test.com");

        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setName(requestDto.getName());
        updatedUser.setEmail(requestDto.getEmail());
        updatedUser.setActive(true);

        Mockito.when(userService.update(eq(1L), any(User.class))).thenReturn(updatedUser);

        mockMvc.perform(put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(updatedUser.getId()))
                .andExpect(jsonPath("$.name").value(updatedUser.getName()))
                .andExpect(jsonPath("$.email").value(updatedUser.getEmail()));
    }

    @Test
    void testDeleteUser() throws Exception {
        Mockito.doNothing().when(userService).delete(1L);

        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isOk());
    }

}