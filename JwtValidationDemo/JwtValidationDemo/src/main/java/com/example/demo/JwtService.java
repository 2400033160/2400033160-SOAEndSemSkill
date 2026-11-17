package com.example.demo;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
@Service
public class JwtService {
 // Demo key only; store secrets securely in real applications.
 private static final String SECRET_KEY = "MDEyMzQ1Njc4OWFiY2RlZjAxMjM0NTY3ODlhYmNkZWY=";
 private SecretKey signingKey() {
  return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
 }
 public String generateToken(String username) {
  long now = System.currentTimeMillis();
  return Jwts.builder().subject(username).issuedAt(new Date(now))
   .expiration(new Date(now + 60 * 60 * 1000)).signWith(signingKey()).compact();
 }
 public String extractUsername(String token) {
  return Jwts.parser().verifyWith(signingKey()).build()
   .parseSignedClaims(token).getPayload().getSubject();
 }
 public boolean isTokenValid(String token) {
  try { extractUsername(token); return true; }
  catch (Exception exception) { return false; }
 }
}
