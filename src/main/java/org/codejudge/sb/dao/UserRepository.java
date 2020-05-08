package org.codejudge.sb.dao;

import org.codejudge.sb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select * from users where auth_token = :authToken", nativeQuery = true)
    public User getUserByHeaders(@Param("authToken") String authToken);
}
