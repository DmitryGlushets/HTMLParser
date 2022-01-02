package ru.glushets.htmlparser.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.glushets.htmlparser.HtmlParserApplicationTests;
import ru.glushets.htmlparser.models.Statistics;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsServiceImplTest extends HtmlParserApplicationTests {

    @Autowired
    private StatisticsServiceImpl statisticsService;

    private final String URL = "https://test.ru";

    @Test
    @Transactional
    void getAllStatistics() {
        List<Statistics> listTest = statisticsService.getAllStatistics();
        assertNotNull(listTest);
    }

    @Test
    @Transactional
    void addStatistics() {
        Statistics testStatistics = statisticsService.addStatistics(URL);
        assertNotNull(testStatistics);
        assertEquals(URL, testStatistics.getUrl());
    }

    @Test
    @Transactional
    void deleteStatistics() {
        Statistics testStatistics = statisticsService.addStatistics(URL);
        assertEquals(1, statisticsService.getAllStatistics().size());
        statisticsService.deleteStatistics(testStatistics.getId());
        assertEquals(0, statisticsService.getAllStatistics().size());
    }

    @Test
    @Transactional
    void getDictionary() {
        Map<String, Integer> testMap = statisticsService.getDictionary("test");
        assertNotNull(testMap);
    }

    @Test
    @Transactional
    void getError() {
        Map<String, Integer> testMap = statisticsService.getDictionary("test");
        assertNotEquals("", statisticsService.getError());
        assertEquals("Не введен https://", statisticsService.getError());
    }
}