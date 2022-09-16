# jwt-springboot
**How to secure API using Jwt**
How to secure API using Jwt
Prerequisite
1. You are familiar create spring boot app from scratch
2. understand concept of JWT(if not understand, try to read this article https://jwt.io/introduction)
3. Postgresql already installed on your laptop
4. Understand to create password using BCryptPasswordEncoder 
5. spring-boot-version =2.7.2

Let's jump right in
**Dependency needed**
- spring-boot-starter-security
- spring-boot-starter-web
- spring-boot-starter-data-jpa
- spring-boot-starter-validation
- java-jwt
- lombok
- postgresql
after dependency added, reload project.

**Packaging Spring boot application. The application divided into layers**
- controler package  = Package to organize controller classes
- dto package        = Pacakage to organize DTO class(The main benefit using DTO is reduces the amount of data that needs to be sent across the wire in distributed applications or mapping JSON request to DTO. )
- entity pacakage    = Package to organize Entity classes(Typically, an entity class represents a table in a relational database.)
- repository pacakage= Pacakage to organize Repository interface
- security package   = Pacakage to organize security classes(when we need custom security, we can create custom security class in this package)
- services  package  = Pacakage to organize services classes(to separate bussines logic with persistence layer)
- exception	package  = Package to Organize Custom Exception Classes

**Let's Code**
- Create configuration in application.properties
   - Logging Configuration
   - Connection configuration/Database configuration
   - Error Message Configuration(Starting from the 2.3 version, Spring Boot doesn't include an error message on the default error page, so to show an error message must configure in application.properties)
   - Jwt secret key
- Create client class in entity package
- Create ClientRepository interface
- Create AuthenticationRequest class ->to handle request
- Create AuthenticationResponse class ->to send Access Token(JWT)
- Create UsernameNotFoundException class ->to handle exception when user not found in database
- Create AuthenticationController class
  - create authenticate method to handle user during login if login succeed user get response(acces token/jwt)
  - create method hello(protected resource)user can access this method when user succeed login and get acces token(jwt)
- implement UserDetailsService -> to retrieve spesific user from database 
- create class JwtTokenService -> JwtTokenService is responsible for generating and validating the token
- create JwtRequestFilter to filtering request ->This filter ensures that a valid token is passed in the header and will store the UserDetails in the SecurityContext for the duration of the request.
- Create JwtSecurityConfig	-> ensures that only authenticated users can access protected API
- insert user into client table
  INSERT INTO public.client
	(id, "password", user_name)
	VALUES(1, '$2a$10$BySDZZFduuHfDgGb4ImDgOFEXeZhqm4aZSotaJwp6W/rwWx2Mg5WC', 'amin');
- Run the application and Test application with this Scenario
  - access protected resources without login
  - access protected resources after login and get acces token(JWT)
  -  after acces token/jwt retrieve, try to access protected resource again
Succeed, Happy learning and happy sharing!!!
