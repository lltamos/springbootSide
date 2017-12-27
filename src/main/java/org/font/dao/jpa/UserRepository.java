package org.font.dao.jpa;

import org.font.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByName(String name);

    List<User> findByNameAndAge(String name, Integer age);

    @Query("from User u where u.name=:name")
    List<User> findUser(@Param("name") String name);

}
