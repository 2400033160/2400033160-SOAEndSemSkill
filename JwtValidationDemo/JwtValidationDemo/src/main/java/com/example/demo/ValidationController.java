package com.example.demo;
import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ValidationController {
 @GetMapping("/validate")
 public Map<String, String> validate(Authentication authentication) {
  return Map.of("message", "JWT Token is Valid", "username", authentication.getName());
 }
}
