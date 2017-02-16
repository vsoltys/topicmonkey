package com.busybox.topicmonkey.domain.repository;

import com.busybox.topicmonkey.domain.model.SearchEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Specifies methods used to obtain and modify SearchEntry related information
 * in the database.
 */
@Repository
public interface SearchEntryRepository extends JpaRepository<SearchEntry, String> {

    /**
     * Finds searchEntry by using the name as a search criteria.
     *
     * @param name
     * @return A searchEntry which name is an exact match with the given name.
     * If no searchEntry is found, this method returns a null.
     */
    Optional<SearchEntry> findByName(@Param("name") String name);

    /**
     * Finds all searchEntry items for a user by using the user's id as a search criteria.
     *
     * @param userId
     * @return A list of searchEntry items by user id.
     * If no searchEntry is found, this method returns an empty list.
     */
    List<SearchEntry> findAllByUserId(@Param("userId") String userId);
}