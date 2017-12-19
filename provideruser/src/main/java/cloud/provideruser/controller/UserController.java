package cloud.provideruser.controller;

import cloud.provideruser.model.User;
import cloud.provideruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/init/{name}/{email}")
    public User createUser(@PathVariable String name, @PathVariable String email) {
        return userService.createUser(name, email);
    }

    @GetMapping("/list")
    public Iterable<User> createUser() {
        return userService.listUser();
    }

    @GetMapping("/list/name/{name}")
    public List<User> findList(@PathVariable String name) {
        return userService.findByName(name);
    }

    @GetMapping("/list/email/{email}")
    public List<User> getByEmail(@PathVariable String email) {
        return userService.getByEmail(email);
    }

    @GetMapping("/list/page/email/{email}/{page}/{size}")
    public Page<User> pageUserByEmail(@PathVariable String email, @PathVariable int page, @PathVariable int size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        return userService.pageUserByEmail(email, pageable);
    }

    @GetMapping("/update/user/{id}/{email}")
    public User updateUserById(@PathVariable Integer id, @PathVariable String email) {
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        userService.updateOneUser(user);
        return user;
    }

}
