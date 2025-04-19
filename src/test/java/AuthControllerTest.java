

import br.com.fiap.lyfecycle.model.User;
import br.com.fiap.lyfecycle.model.controller.AuthController;
import br.com.fiap.lyfecycle.model.security.service.TokenService;
import br.com.fiap.lyfecycle.model.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    private final AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
    private final UserService userService = mock(UserService.class);
    private final TokenService tokenService = mock(TokenService.class);

    private final AuthController authController = new AuthController();

    public AuthControllerTest() {
        authController.authenticationManager = authenticationManager;
        authController.userService = userService;
        authController.tokenService = tokenService;
    }

    @Test
    void testLogin() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(user);
        when(tokenService.createToken(user)).thenReturn("mocked-token");

        ResponseEntity response = authController.login(user);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("mocked-token", response.getBody());
    }

    @Test
    void testRegister() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(userService.save(user)).thenReturn(user);

        User registeredUser = authController.register(user);

        assertEquals(user, registeredUser);
        verify(userService, times(1)).save(user);
    }
}