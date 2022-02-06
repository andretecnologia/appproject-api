package com.project.api.controllers;

import com.project.api.models.ERole;
import com.project.api.models.Role;
import com.project.api.models.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
class AuthControllerTest extends BaseController {

    @Test
    void registerUserTest() throws Exception {

        Mockito.when(roleService.findByName(Mockito.any())).thenReturn(Optional.of(new Role(ERole.ROLE_USER)));
        Mockito.when(userService.save(Mockito.any())).thenReturn(new User());

        String url = "/api/auth/signup";
        User testUser = new User();
        testUser.setEmail("test@test");
        testUser.setUsername("test");
        testUser.setPassword("123456");
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(ERole.ROLE_ADMIN));
        testUser.setRoles(roles);
        //Json payload
        String payload = om.writeValueAsString(testUser);
        //Perform test
        MvcResult result = mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(payload)
        ).andExpect(status().isOk()).andReturn();

        assertEquals("{\"message\":\"User registered successfully!\"}", result.getResponse()
                .getContentAsString());
    }
}
