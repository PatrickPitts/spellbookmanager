package org.nerdcore.spellbookmanager.repository;

import org.nerdcore.spellbookmanager.models.Spell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SpellRepository extends JpaRepository<Spell, Integer> {

    List<Spell> findAll();

    List<Spell> findByName(String name);

    List<Spell> findBySchoolOrderBySpellLevel(String school);

    List<Spell> findBySpellLevel(int level);
}
