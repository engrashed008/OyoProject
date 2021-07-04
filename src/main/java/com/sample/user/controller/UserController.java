package com.sample.user.controller;

import com.sample.user.core.CommonResponse;
import com.sample.user.dto.UserReq;
import com.sample.user.dto.UserRequestParam;
import com.sample.user.dto.UserUpdateReq;
import com.sample.user.resonse.UserUpdateResponse;
import com.sample.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class UserController {
    @Autowired
    UserService service;

    /*@PostMapping("/sign-up")
    public CommonResponse create(@RequestBody @Valid UserRequestParam param) {
        service.create(param);
        return CommonResponse.success("OK");
    }*/

    @PostMapping("/signup")
    public CommonResponse<UserReq> create(@RequestBody @Valid UserRequestParam param) {
        UserReq entity = service.create(param);
        return CommonResponse.success(entity, "User Details by user_id");
    }


    @GetMapping("users/{user_id}")
    public CommonResponse<UserReq> user(@PathVariable @NotNull String user_id) {
        UserReq entity = service.singleUser(user_id);
        return CommonResponse.success(entity, "User Details by user_id");
    }

    @PatchMapping("users/{user_id}")
    public CommonResponse<UserUpdateResponse> add(@PathVariable @NotNull String user_id, @RequestBody UserUpdateReq param) throws Exception {
        UserUpdateResponse dto = service.updateUser(user_id, param);
        return CommonResponse.success(dto, "User successfully updated");
    }

    @PostMapping("/close/{user_id}")
    public CommonResponse close(@PathVariable @NotNull String user_id) {
        service.delete(user_id);
        return CommonResponse.success("Account and user successfully removed");
    }

    /*@PostMapping("/remove/event-user")
    public CommonResponse deleteUserEvent(@RequestBody @Valid DeleteParam param) {
        service.deleteUserFromEvent(param.getEmail(), param.getEventId());
        return CommonResponse.success("OK");
    }*/
}
