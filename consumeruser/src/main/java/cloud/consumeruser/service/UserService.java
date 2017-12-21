package cloud.consumeruser.service;

import cloud.consumeruser.model.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "provider-user-application")
public interface UserService {

    @RequestMapping(method = RequestMethod.GET,value = "/user/list/email/{email}")
    List<User> getUserListByEmail(@PathVariable("email") String email);
}
