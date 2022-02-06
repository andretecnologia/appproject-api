package com.project.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.api.security.jwt.AuthEntryPointJwt;
import com.project.api.security.jwt.JwtUtils;
import com.project.api.security.services.RefreshTokenService;
import com.project.api.security.services.UserDetailsServiceImpl;
import com.project.api.service.RoleService;
import com.project.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

public class BaseController {

    @Autowired
    WebApplicationContext context;

    @Autowired
    ObjectMapper om;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RoleService roleService;

    @MockBean
    UserDetailsServiceImpl userDetailsService;

    @MockBean
    AuthEntryPointJwt authEntryPointJwt;

    @MockBean
    RefreshTokenService refreshTokenService;

    @MockBean
    JwtUtils jwtUtils;

    @MockBean
    UserService userService;
}
