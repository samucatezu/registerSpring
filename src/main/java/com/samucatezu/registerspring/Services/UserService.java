package com.samucatezu.registerspring.Services;

import com.samucatezu.registerspring.DTO.UserDto;
import com.samucatezu.registerspring.Exception.UserAlreadyExistException;
import com.samucatezu.registerspring.Model.User;
import com.samucatezu.registerspring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;

@Service
@Transactional
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException {
        if (emailExists(userDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + userDto.getEmail());
        }

        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setRoles(Arrays.asList("ROLE_USER"));

        return userRepository.save(user);

    }
    private boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }
}
