package Authcom.example.Authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SignupResponseDto {

    private Long id;
    private String username;
    private String email;
    private String role;
    private LocalDateTime createdAt;
}