package com.hc.controller;

import com.hc.domin.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value="/users")
public class UserController {

    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    @RequestMapping(value="/", method = RequestMethod.GET)
    public List<User> getUserList() {
        List<User> userList = new ArrayList<>(users.values());
        return userList;
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public String postUser(@RequestBody @Validated User user) {
        users.put(user.getUserId(), user);

        return "ok";
    }

    @RequestMapping(value="/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long userId) {
        return users.get(userId);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public String putUser(@PathVariable Long userId, @ModelAttribute User user) {
        User u = users.get(userId);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(userId, u);
        return "success";
    }

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
