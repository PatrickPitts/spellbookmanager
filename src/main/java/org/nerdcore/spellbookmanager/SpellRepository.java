package org.nerdcore.spellbookmanager;

import org.nerdcore.spellbookmanager.models.Spell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.transaction.Transactional;
import java.util.List;

public interface SpellRepository extends JpaRepository<Spell, Long> {

    List<Spell> findByName(String name);

    @Transactional
    @Override
    <S extends Spell> S saveAndFlush(S s);
}
