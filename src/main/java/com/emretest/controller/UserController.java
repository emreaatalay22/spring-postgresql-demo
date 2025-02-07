package com.emretest.controller;

import com.emretest.config.ApplicationConfig;
import com.emretest.config.GlobalProperties;
import com.emretest.config.Server;
import com.emretest.dto.UserDTO;
import com.emretest.dto.UserDTOIU;
import com.emretest.model.RequestPageModel;
import com.emretest.model.ResultModel;
import com.emretest.service.IUserService;
import com.emretest.entities.User;
import io.micrometer.core.annotation.Counted;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.web.bind.annotation.*;

import io.micrometer.core.annotation.Timed;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController extends RestBaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private GlobalProperties globalProperties;

    @Autowired
    private ApplicationConfig applicationConfig;

    @Timed(value = "requests.time", description = "Request Response Time",histogram = true)
    @Counted(value = "requests.count", description = "Total Request Count")    @GetMapping(path = "/users")
    public ResultModel<List<User>> users() {
        System.out.println("users");
        return ok(userService.getAllList());
    }

    @Timed(value = "requests.time", description = "Request Response Time",histogram = true)
    @Counted(value = "requests.count", description = "Total Request Count")    @GetMapping(path = "/users/{id}")
    public ResultModel<UserDTO> findUserById(@PathVariable(name = "id") Integer id) {
        return ok(userService.getUserById(id));
    }

    @Timed(value = "requests.time", description = "Request Response Time",histogram = true)
    @Counted(value = "requests.count", description = "Total Request Count")    @GetMapping(path = "/users/search")
    public ResultModel<Page<User>> findSearchUser(RequestPageModel requestPageModel) {

        Pageable pageRequest = PageRequest.of(requestPageModel.getPageSize(), requestPageModel.getPageNumber(),
                requestPageModel.getSortDirection() ?
                        Sort.by(Sort.Direction.ASC, "id") :
                        Sort.by(Sort.Direction.DESC, "id")
        );

        Page<User> result = userService.getSearchUser(pageRequest);

        return ok(result);
    }

    @Timed(value = "requests.time", description = "Request Response Time",histogram = true)
    @Counted(value = "requests.count", description = "Total Request Count")
    @PostMapping(path = "/users")
    public ResultModel<UserDTO> save(@RequestBody @Valid UserDTOIU dtoUser) {
        return ok(userService.createUser(dtoUser));
    }

    @Timed(value = "requests.time", description = "Request Response Time",histogram = true)
    @Counted(value = "requests.count", description = "Total Request Count")    @PutMapping(path = "/users")
    public ResultModel<User> update(@RequestBody User user) {
        return ok(userService.updateUser(user));
    }

    @Timed(value = "requests.time", description = "Request Response Time",histogram = true)
    @Counted(value = "requests.count", description = "Total Request Count")    @DeleteMapping(path = "/users/{id}")
    public void delete(@PathVariable(name = "id") Integer id) {
        userService.deleteUser(id);
    }
}
