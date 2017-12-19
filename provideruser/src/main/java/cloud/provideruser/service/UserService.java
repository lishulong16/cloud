package cloud.provideruser.service;

import cloud.provideruser.model.User;
import cloud.provideruser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User createUser(String name, String email) {
        return userRepository.save(new User(name, email));
    }

    public List<User> getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    public List<User> findByName(String name) {
        return userRepository.findByNameLike(name);
    }

    public Iterable<User> listUser() {
        return userRepository.findAll();
    }

    /***
     * 分页查询
     * @param email
     * @param pageable
     * @return
     */

    public Page<User> pageUserByEmail(String email, Pageable pageable) {
        return userRepository.findAllByEmail(email, pageable);
    }

    /***
     * 更新
     * @param user
     * @return 0 | 1
     */
    public Integer updateOneUser(User user) {
        assert user.getId() != null;
        return userRepository.updateOneUser(user.getId(), user.getEmail());
    }
}
