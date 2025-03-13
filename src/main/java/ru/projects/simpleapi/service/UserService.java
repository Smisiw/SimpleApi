package ru.projects.simpleapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.projects.simpleapi.model.User;
import ru.projects.simpleapi.repository.UserRepository;
import ru.projects.simpleapi.security.UserDetailsImpl;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                "Пользователь с именем " + username + " не найден"
        ));
        return UserDetailsImpl.build(user);
    }

}
