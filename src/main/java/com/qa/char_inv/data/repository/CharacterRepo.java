package com.qa.char_inv.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.qa.char_inv.data.entity.Character;

@Repository
public interface CharacterRepo extends JpaRepository<Character, Integer> {

}
