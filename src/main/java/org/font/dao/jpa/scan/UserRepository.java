package org.font.dao.jpa.scan;

import org.font.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByName(String name);

    List<User> findByNameAndAge(String name, Integer age);

    @Query("from User u where u.name=:name")
    List<User> findUser(@Param("name") String name);

}
