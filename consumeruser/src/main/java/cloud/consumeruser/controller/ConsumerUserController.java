package cloud.consumeruser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ConsumerUserController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${user.UserListByEmailApi}")
    private String getUserListByEmailApi;

    @GetMapping("/user/{email}")
    public ResponseEntity<List> getByEmail(@PathVariable String email) {
        return this.restTemplate.getForEntity(getUserListByEmailApi + email, List.class);
    }
}
