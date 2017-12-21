package cloud.consumeruser.controller;

import cloud.consumeruser.model.User;
import cloud.consumeruser.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@RestController
public class ConsumerUserController {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerUserController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Value("${user.UserListByEmailApi}")
    private String getUserListByEmailApi;

    @Autowired
    private UserService userService;

//    @GetMapping("/user/{email}")
//    public ResponseEntity<List> getByEmail(@PathVariable String email) {
//        logger.info("request user list by email,argument:{}", email);
//        return this.restTemplate.getForEntity(getUserListByEmailApi + email, List.class);
//    }

    @GetMapping("/user/{email}")
    public List<User> getByEmail(@PathVariable String email) {
        logger.info("request user list by email,argument:{}", email);
        return this.userService.getUserListByEmail(email);
    }

    @GetMapping("/user-instance")
    public List<ServiceInstance> showInfo() {
        logger.info("request ServiceInstance,argument:{}", "/user-instance");
        return this.discoveryClient.getInstances("consumer-user-application");
    }

    @GetMapping("/log-instance")
    public void logshowInfo() {
        ServiceInstance choose = this.loadBalancerClient.choose("consumer-user-application");
        logger.info("request log show Info,argument:{},{}:{}:{}", "/log-instance",choose.getServiceId(),choose.getHost(),choose.getPort());
    }

    @GetMapping("/errors")
    public ResponseEntity show404() {
        logger.info("request show404,argument:{}", "/error");
        return ResponseEntity.ok(Collections.singletonMap("status", "success"));
    }
}
