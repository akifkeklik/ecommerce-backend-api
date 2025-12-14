package com.ecommerce.api.v1;

import com.ecommerce.application.dto.auth.AuthResponse;
import com.ecommerce.application.dto.auth.LoginRequest;
import com.ecommerce.application.dto.auth.RegisterRequest;
import com.ecommerce.application.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for AuthController.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthControllerIntegrationTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @MockBean
        private AuthService authService;

        private AuthResponse authResponse;

        @BeforeEach
        void setUp() {
                AuthResponse.UserDto userDto = new AuthResponse.UserDto(
                                "123e4567-e89b-12d3-a456-426614174000",
                                "testuser",
                                "test@example.com",
                                "Test",
                                "User",
                                "CUSTOMER",
                                null);

                authResponse = new AuthResponse(
                                "test-access-token",
                                "test-refresh-token",
                                "Bearer",
                                900,
                                userDto);
        }

        @Test
        @DisplayName("POST /api/v1/auth/register - should register user successfully")
        void register_ValidRequest_ReturnsCreated() throws Exception {
                RegisterRequest request = new RegisterRequest();
                request.setUsername("newuser");
                request.setEmail("newuser@example.com");
                request.setPassword("password123");
                request.setFirstName("New");
                request.setLastName("User");

                when(authService.register(any(RegisterRequest.class))).thenReturn(authResponse);

                mockMvc.perform(post("/api/v1/auth/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.accessToken").value("test-access-token"))
                                .andExpect(jsonPath("$.tokenType").value("Bearer"))
                                .andExpect(jsonPath("$.user.username").value("testuser"));
        }

        @Test
        @DisplayName("POST /api/v1/auth/register - should return 400 for invalid request")
        void register_InvalidRequest_ReturnsBadRequest() throws Exception {
                RegisterRequest request = new RegisterRequest();
                request.setUsername("ab"); // Too short
                request.setEmail("invalid-email"); // Invalid format
                request.setPassword("short"); // Too short

                mockMvc.perform(post("/api/v1/auth/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("POST /api/v1/auth/login - should login successfully")
        void login_ValidCredentials_ReturnsOk() throws Exception {
                LoginRequest request = new LoginRequest();
                request.setUsernameOrEmail("testuser");
                request.setPassword("password123");

                when(authService.login(any(LoginRequest.class))).thenReturn(authResponse);

                mockMvc.perform(post("/api/v1/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.accessToken").value("test-access-token"))
                                .andExpect(jsonPath("$.user.email").value("test@example.com"));
        }

        @Test
        @DisplayName("POST /api/v1/auth/login - should return 400 for missing credentials")
        void login_MissingCredentials_ReturnsBadRequest() throws Exception {
                LoginRequest request = new LoginRequest();
                request.setUsernameOrEmail("");
                request.setPassword("");

                mockMvc.perform(post("/api/v1/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                                .andExpect(status().isBadRequest());
        }
}
