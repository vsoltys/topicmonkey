package com.busybox.topicmonkey.domain.repository;

import com.busybox.topicmonkey.domain.model.SearchEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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

    @Query(value = "SELECT se FROM search_entry u WHERE se.name=:name", nativeQuery = true)
    Optional<SearchEntry> findByName(@Param("name") String name);
}