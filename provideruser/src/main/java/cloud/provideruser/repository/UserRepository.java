package cloud.provideruser.repository;

import cloud.provideruser.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    List<User> findByNameLike(String name);

    List<User> getByEmail(String email);

    Page<User> findAllByEmail(String email, Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE User u set u.email = :email where u.id = :id")
    Integer updateOneUser(@Param("id")Integer id, @Param("email")String email);

}
