package ru.glushets.htmlparser.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.glushets.htmlparser.models.Statistics;

@Repository
public interface StatisticsRepository extends CrudRepository<Statistics, Long> {
}
