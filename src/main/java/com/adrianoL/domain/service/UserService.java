package com.adrianoL.domain.service;

import com.adrianoL.api.dto.UserDTO;
import com.adrianoL.domain.exception.UserAlreadyExistsException;
import com.adrianoL.domain.model.auth.User;
import com.adrianoL.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static com.adrianoL.api.dto_mapper.ObjectMapper.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public User getUserByEmailOrException(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found!")
        );
    }

    public User getUserByUsernameOrException(String username){
        return userRepository.findUserByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found!")
        );
    }

    public UserDTO findByUsername(String username){
        var user = getUserByUsernameOrException(username);
        return parseObject(user, UserDTO.class);
    }

    @Transactional
    public User create(User user) {
        checkIfUsernameOrEmailAreAvailable(user);
        var userRole = roleService.getUserRole();
        user.setRoles(Set.of(userRole));
        if (Objects.nonNull(user.getPassword())) user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return user;
    }

    private void checkIfUsernameOrEmailAreAvailable(User user) {
        Map<String, String> errors = new HashMap<>();

        userRepository.findUserByEmail(user.getEmail())
                .ifPresent(u -> errors.put(
                        "email", "User with this email already exists!"
                ));
        userRepository.findUserByUsername(user.getUsername())
                .ifPresent(u -> errors.put(
                        "username", "This username is already taken!"
                ));

        if (!errors.isEmpty()) {
            var message = "The provided username or email is already in use. Please choose another one.";
            throw new UserAlreadyExistsException(message, errors);
        }
    }
}
