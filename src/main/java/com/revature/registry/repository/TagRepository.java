package com.revature.registry.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import com.revature.registry.model.Tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    @Query("from Tag t where t.name = ?1")
    Optional<Tag> findByName(String name);

    @Modifying
    @Transactional
    @Query("update Tag t set t.isEnabled = true where t.id = ?1")
    void enableTag(int id);

    @Modifying
    @Transactional
    @Query("update Tag t set t.isEnabled = false where t.id = ?1")
    void disableTag(int id);
}
