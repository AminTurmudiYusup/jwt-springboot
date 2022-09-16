package com.example.springjwt.services;

import com.example.springjwt.entity.Client;
import com.example.springjwt.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class JwtUserDetailsService  implements UserDetailsService {

    public static final String USER = "USER";
    public static final String ROLE_USER = "ROLE_" + USER;//assign role user, you can assign role by database(in this example i assign role user by hard code)
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Client client=clientRepository.findByUserName(username).orElseThrow(
                () -> new UsernameNotFoundException("User"+username+"not found"));
        return new User(username, client.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(ROLE_USER)));
    }
}
