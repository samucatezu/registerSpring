package com.samucatezu.registerspring.Services;

import com.samucatezu.registerspring.DTO.UserDto;
import com.samucatezu.registerspring.Model.User;

public interface IUserService {
    User registerNewUserAccount(UserDto userDto);

}
