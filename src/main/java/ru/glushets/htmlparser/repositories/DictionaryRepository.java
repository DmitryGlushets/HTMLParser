package ru.glushets.htmlparser.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.glushets.htmlparser.models.Dictionary;

@Repository
public interface DictionaryRepository extends CrudRepository<Dictionary, Long> {
}
