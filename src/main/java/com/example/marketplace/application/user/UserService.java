package com.example.marketplace.application.user;

import com.example.marketplace.domain.entity.User;
import com.example.marketplace.infrastructure.repository.UserRepository;
import com.example.marketplace.presentation.user.dto.commands.CreateUserCommand;
import com.example.marketplace.presentation.user.dto.commands.UpdateUserCommand;
import com.example.marketplace.presentation.user.dto.queries.UserQuery;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserQuery> getAll() {
        List<User> allUsers = userRepository.findAll();
        return userMapper.fromListUserQueryToListUser(allUsers);
    }


    public UserQuery getById(Integer id) {
        User userFromDB = getUserFromDB(id);
        return userMapper.userToUserQuery(userFromDB);
    }

    public UserQuery createUser(CreateUserCommand createUserCommand) {
        User newUser = userMapper.createUserCommandToUser(createUserCommand);
        User savedUser = userRepository.save(newUser);
        return userMapper.userToUserQuery(savedUser);
    }

    public UserQuery updateUser(Integer id, UpdateUserCommand updateUserCommand) {
        User userFromDB = getUserFromDB(id);
        userMapper.updateUserFromUpdateUserCommand(updateUserCommand, userFromDB);
        User savedUser = userRepository.save(userFromDB);
        return userMapper.userToUserQuery(savedUser);
    }

    public void deleteUser(Integer id) {
        User userFromDB = getUserFromDB(id);
        userRepository.delete(userFromDB);
    }

    public User getUserFromDB(Integer id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Не удалось найти пользователя с id = %d", id)));
    }

    public User saveUser(User userFromDB) {
        return userRepository.save(userFromDB);
    }
}
