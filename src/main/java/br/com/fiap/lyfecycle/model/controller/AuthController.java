package br.com.fiap.lyfecycle.model.controller;

import br.com.fiap.lyfecycle.model.User;
import br.com.fiap.lyfecycle.model.security.service.TokenService;
import br.com.fiap.lyfecycle.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    public
    AuthenticationManager authenticationManager;

    @Autowired
    public
    UserService userService;

    @Autowired
    public
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        String token = tokenService.createToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        User newUser = userService.save(user);
        return newUser;
    }

}
