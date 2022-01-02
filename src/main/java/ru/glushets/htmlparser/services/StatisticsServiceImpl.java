package ru.glushets.htmlparser.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.glushets.htmlparser.models.Statistics;
import ru.glushets.htmlparser.parser.ParserDictionary;
import ru.glushets.htmlparser.repositories.StatisticsRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsRepository statisticsRepository;

    private final ParserDictionary parserDictionaryFromHTML;

    @Override
    public List<Statistics> getAllStatistics() {
        return (List<Statistics>) statisticsRepository.findAll();
    }

    @Override
    public Statistics addStatistics(String url) {
        Statistics statistics = Statistics.builder()
                .date(LocalDate.now())
                .time(LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute())
                .url(url)
                .build();
        statisticsRepository.save(statistics);
        return statistics;
    }

    @Override
    public void deleteStatistics(Long statisticsId) {
        statisticsRepository.deleteById(statisticsId);
    }

    @Override
    public Map<String, Integer> getDictionary(String source) {
        return parserDictionaryFromHTML.getDictionary(source);
    }

    @Override
    public String getError() {
        return parserDictionaryFromHTML.getError();
    }
}
