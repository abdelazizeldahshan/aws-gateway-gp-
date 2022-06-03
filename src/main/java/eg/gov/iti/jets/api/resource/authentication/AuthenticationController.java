package eg.gov.iti.jets.api.resource.authentication;

import eg.gov.iti.jets.api.util.JwtUtil;
import eg.gov.iti.jets.service.management.UserManagement;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/")
public class AuthenticationController {
    private final UserManagement userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthenticationController( UserManagement userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil ) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticateUser(@RequestBody AuthenticationRequest authReq) {
        String jwt = authenticate(authReq.getUsername(), authReq.getPassword());
        return new AuthenticationResponse(jwt);
    }

    @GetMapping("/users")
    public String test() {
        return "test";
    }

    private String authenticate(String username, String password){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        }catch (BadCredentialsException e){
            throw new RuntimeException("Incorrect username or password", e);
        }
        UserDetails userDetails = userService.loadUserByUsername(username);
        return jwtUtil.generateToken(userDetails);
    }

}