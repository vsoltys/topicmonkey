package com.busybox.topicmonkey.domain.repository;

import com.busybox.topicmonkey.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Specifies methods used to obtain and modify User related information
 * which is stored in the database.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds user by using the login name as a search criteria.
     *
     * @param loginName
     * @return A user which login name is an exact match with the given login name.
     * If no user is found, this method returns a null.
     */

    //TODO: named query or method level? native?
//    @Query("SELECT u FROM tm_user u WHERE u.login_name=:loginName")
//    User findByLoginName(@Param("loginName") String loginName);
}
