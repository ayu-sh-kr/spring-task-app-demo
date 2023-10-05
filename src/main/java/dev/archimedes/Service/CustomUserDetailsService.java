package dev.archimedes.Service;

import dev.archimedes.models.User;
import dev.archimedes.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public CustomUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info(email);
        User user = repository.findByEmail(email);
        log.info(user.getName() + " " +  user.getEmail());
        if(user == null){
            throw new UsernameNotFoundException("user not found with email: " + email);
        }

        return new CustomUserDetails(user);
    }
}
