package Authcom.example.Authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {

    private Long id;
    private String username;
    private String email;
    private String role;
    private String token;
}
