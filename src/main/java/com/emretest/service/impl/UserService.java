package com.emretest.service.impl;

import com.emretest.dto.UserAddressDTO;
import com.emretest.dto.UserDTO;
import com.emretest.dto.UserDTOIU;
import com.emretest.entities.Address;
import com.emretest.entities.User;
import com.emretest.repositories.AddressRepository;
import com.emretest.repositories.UserRepository;
import com.emretest.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<User> getAllList() {
        return userRepository.findAll();
    }

    @Override
    public UserDTO getUserById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return null;
        }

        User user = userOptional.get();
        Address address = user.getAddress();

        UserDTO result = new UserDTO();
        UserAddressDTO userAddressDTO = new UserAddressDTO();

        BeanUtils.copyProperties(user, result);
        BeanUtils.copyProperties(address, userAddressDTO);

        result.setUserAddress(userAddressDTO);
        return result;
    }

    public Page<User> getSearchUser(Pageable pageable) {
       return   userRepository.searchUser(pageable);
    }

    @Override
    public User getUserByEmail(String email) {
      //  User q=new User();
        return null;
    }

    @Override
    public UserDTO createUser(UserDTOIU dtoUser) {
        User user = new User();
        user.address = new Address();

        BeanUtils.copyProperties(dtoUser, user);
        BeanUtils.copyProperties(dtoUser.getAddress(), user.address);

        Address address = addressRepository.save(user.address);
        user.setAddress(address);
        user = userRepository.save(user);
        UserDTO u = new UserDTO();
        BeanUtils.copyProperties(user, u);
        return u;
    }

    @Override
    public User updateUser(User user) {
        User dbUser = userRepository.findById(user.getId()).get();
        if (dbUser != null) {
            dbUser.setEmail(user.getEmail());
            dbUser.setUsername(user.getUsername());

            return userRepository.save(dbUser);
        }
        return null;
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
