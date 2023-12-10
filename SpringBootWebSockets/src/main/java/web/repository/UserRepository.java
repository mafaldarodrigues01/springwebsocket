package web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM USERR u WHERE u.username = :username", nativeQuery = true)
    User getUserByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM USERR u WHERE u.username = :username and u.password = :password", nativeQuery = true)
    User getUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
