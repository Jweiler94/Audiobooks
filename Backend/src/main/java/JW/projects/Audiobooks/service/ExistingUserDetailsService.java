package JW.projects.Audiobooks.service;

import java.util.Collections;
import JW.projects.Audiobooks.models.User;
import JW.projects.Audiobooks.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ExistingUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public ExistingUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsernameIgnoreCase(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        } else {
            return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority("USER")));
        }
    }
}
