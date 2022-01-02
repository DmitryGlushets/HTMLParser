package ru.glushets.htmlparser.services;

import org.springframework.stereotype.Service;
import ru.glushets.htmlparser.models.Statistics;

import java.util.List;
import java.util.Map;

@Service
public interface StatisticsService {

    List<Statistics> getAllStatistics();

    Statistics addStatistics(String url);

    void deleteStatistics(Long statisticsId);

    Map<String, Integer> getDictionary(String source);

    String getError();
}
