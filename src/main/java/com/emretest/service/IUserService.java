package com.emretest.service;

import com.emretest.dto.UserDTO;
import com.emretest.dto.UserDTOIU;
import com.emretest.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IUserService {

    List<User> getAllList();

    UserDTO getUserById(Integer id);
    User getUserByEmail(String email);
    UserDTO createUser(UserDTOIU dtoUser);
    User updateUser(User user);
    Page<User> getSearchUser(Pageable pageable);
    void deleteUser(Integer id);
}
