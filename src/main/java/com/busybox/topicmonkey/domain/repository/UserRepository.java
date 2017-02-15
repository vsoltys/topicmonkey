package com.busybox.topicmonkey.domain.repository;

import com.busybox.topicmonkey.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Specifies methods used to obtain and modify User related information
 * in the database.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * Finds user by using the login name as a search criteria.
     *
     * @param loginName
     * @return A user which login name is an exact match with the given login name.
     * If no user is found, this method returns a null.
     */

    //TODO: named query or method level? native?
    @Query(value = "SELECT u FROM user u WHERE u.login_name=:loginName", nativeQuery = true)
    Optional<User> findByLoginName(@Param("loginName") String loginName);
}
