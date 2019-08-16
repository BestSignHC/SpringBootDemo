package com.hecheng.controller;

import com.hecheng.domain.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value="/users")
public class UserController {

    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    @ApiOperation(value = "获取用户列表", notes = "")
    @RequestMapping(value="/", method = RequestMethod.GET)
    public List<User> getUserList() {
        List<User> userList = new ArrayList<>(users.values());
        return userList;
    }

    @ApiOperation(value = "新增用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体User", required = true, dataType = "User")
    @RequestMapping(value="/", method = RequestMethod.POST)
    public String postUser(@ModelAttribute User user) {
        users.put(user.getUserId(), user);

        return "ok";
    }

    @ApiOperation(value = "查询用户", notes = "根据用户Id查询指定用户")
    @ApiImplicitParam(name = "userId", value = "用户Id", required = true, dataType = "Long")
    @RequestMapping(value="/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long userId) {
        return users.get(userId);
    }

    @ApiOperation(value = "修改用户", notes = "根据用户Id修改指定用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "user", value = "用户详细实体User", required = true, dataType = "User")
    })
    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public String putUser(@PathVariable Long userId, @ModelAttribute User user) {
        User u = users.get(userId);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(userId, u);
        return "success";
    }

    @ApiOperation(value = "删除用户", notes = "根据用户Id删除指定用户")
    @ApiImplicitParam(name = "userId", value = "用户Id", required = true, dataType = "Long")
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long userId) {
        if (users.containsKey(userId)) {
            users.remove(userId);
            return "ok";
        }
        else {
            return "not found";
        }
    }

}
