package com.pretz.dyntreeserver.service.dto;

import com.pretz.dyntreeserver.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    public User fromUserDTO(UserDTO userDTO) {
        return new User(userDTO.getName(), userDTO.getPassword(), userDTO.getEmail());
    }

    public UserDTO fromUser(User user) {
        return new UserDTO(user.getName(), user.getPassword(), user.getEmail());
    }

    public List<User> fromUserDTOList(List<UserDTO> userDTOList) {
        List<User> userList = new ArrayList<>();
        for (UserDTO userDTO : userDTOList) {
            userList.add(fromUserDTO(userDTO));
        }
        return userList;
    }

    public List<UserDTO> fromUserList(List<User> userList) {
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList) {
            userDTOList.add(fromUser(user));
        }
        return userDTOList;
    }
}

