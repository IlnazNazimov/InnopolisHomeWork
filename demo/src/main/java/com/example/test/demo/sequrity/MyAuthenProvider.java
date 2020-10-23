package com.example.test.demo.sequrity;

import com.example.test.demo.UserRepository;
import com.example.test.demo.entity.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MyAuthenProvider implements AuthenticationProvider {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public MyAuthenProvider(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        User user = userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("not found"));
        boolean matches = passwordEncoder.matches(password, user.getPassword());
        if (matches) {
            return new UsernamePasswordAuthenticationToken(user, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
        } else {
            throw new BadCredentialsException("Bad password");
        }
    }

    @Override
    public boolean supports(Class<?> authenticate) {
        return UsernamePasswordAuthenticationToken.class.equals(authenticate);
    }
}
