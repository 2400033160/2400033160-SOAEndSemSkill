package com.example.demo;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/auth")
public class AuthController {
 private final JwtService jwtService;
 public AuthController(JwtService jwtService) { this.jwtService = jwtService; }
 // Demo-only token issuer so the experiment can be tested immediately.
 @PostMapping("/token")
 public Map<String, String> createToken() {
  return Map.of("token", jwtService.generateToken("student"), "tokenType", "Bearer");
 }
}
