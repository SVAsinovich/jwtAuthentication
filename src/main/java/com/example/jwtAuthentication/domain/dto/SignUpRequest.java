package com.example.jwtAuthentication.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request for sign up")
public class SignUpRequest {

    @Schema(description = "Username", example = "Sergey")
    @Size(min = 5, max = 50, message = "Username should be between 5 and 50 characters")
    @NotBlank(message = "Username cannot be blank")
    private String username;

    @Schema(description = "Email", example = "sergey@gmail.com")
    @Size(min = 5, max = 255, message = "Email should be between 5 and 255 characters")
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be in the format user@example.com")
    private String email;

    @Schema(description = "Password", example = "newPass1")
    @Size(min = 8, max = 255, message = "Password length should be between 8 and 255 characters")
    @NotBlank(message = "Password cannot be blank")
    private String password;
}
