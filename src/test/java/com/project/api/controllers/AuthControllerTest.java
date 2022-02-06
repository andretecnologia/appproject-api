package com.project.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.api.models.ERole;
import com.project.api.models.Role;
import com.project.api.models.User;
import com.project.api.service.RoleService;
import com.project.api.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@EnableWebMvc
public class AuthControllerTest {

    @Autowired
    WebApplicationContext context;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    //    @BeforeEach
    //    public void setup() {
    //        roleService.save(new Role(ERole.ROLE_ADMIN));
    //        roleService.save(new Role(ERole.ROLE_USER));
    //        roleService.save(new Role(ERole.ROLE_MODERATOR));
    //    }

    @Test
    public void registerUserTest() throws Exception {

//        Mockito.when(roleService.findByName(Mockito.any())).thenReturn(Optional.of(new Role(ERole.ROLE_USER)));
//        Mockito.when(userService.save(Mockito.any())).thenReturn(new User());
//        Mockito.when(userService.existsByUsername(Mockito.any())).thenReturn(false);
//        Mockito.when(userService.existsByEmail(Mockito.any())).thenReturn(false);

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
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(payload)
        ).andExpect(status().isOk()).andReturn();

        Assertions.assertEquals("{\"message\":\"User registered successfully!\"}", result.getResponse()
                .getContentAsString());
    }
}
