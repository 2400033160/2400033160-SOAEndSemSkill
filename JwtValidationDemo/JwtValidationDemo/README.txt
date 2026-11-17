JWT VALIDATION EXPERIMENT - SPRING BOOT
Java 17+, Maven, Spring Boot 3.5.6.

IMPORT:
1. Extract JwtValidationDemo.zip.
2. STS/Eclipse > File > Import > Maven > Existing Maven Projects.
3. Browse to the extracted JwtValidationDemo folder; select it and Finish.
4. Wait for Maven dependencies to download.
5. Run JwtValidationDemoApplication.java > Run As > Spring Boot App.

POSTMAN (server default port 8080):
1) Generate a demo token:
   POST http://localhost:8080/auth/token
   No body and no Authorization header.
   Response is JSON containing "token". Copy only its value.

2) Validate:
   GET http://localhost:8080/validate
   Authorization tab > Bearer Token > paste copied token > Send.
   Expected HTTP 200 and:
   {"message":"JWT Token is Valid","username":"student"}

3) Invalid token:
   Same GET URL; set Bearer Token to abc.invalid.token.
   Expected HTTP 401 Unauthorized.

4) Missing token:
   Same GET URL with no Authorization header.
   Expected HTTP 401 Unauthorized.

HOW IT WORKS:
- POST /auth/token creates a one-hour demo JWT.
- JwtAuthenticationFilter reads Authorization: Bearer <token>.
- JwtService verifies signature and expiry.
- GET /validate is accessible only with a valid token.

The token-issuing endpoint and hardcoded key are for classroom demonstration only, not production.
