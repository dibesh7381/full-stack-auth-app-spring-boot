package Authcom.example.Authentication.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // 1️⃣ Header se token lo
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        // 2️⃣ Token se data nikaalo
        Long userId = jwtUtil.extractUserId(token);
        String role = jwtUtil.extractRole(token);

        // 3️⃣ Agar already authenticated nahi hai
        if (SecurityContextHolder.getContext().getAuthentication() == null) {

            // 4️⃣ Token validate
            if (jwtUtil.validateToken(token, userId)) {

                var authorities = List.of(new SimpleGrantedAuthority(role));

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userId,
                                null,
                                authorities
                        );

                // 5️⃣ Security context me set
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 6️⃣ Next filter
        filterChain.doFilter(request, response);
    }
}
