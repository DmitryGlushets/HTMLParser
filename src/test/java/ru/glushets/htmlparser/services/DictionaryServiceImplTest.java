package ru.glushets.htmlparser.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.glushets.htmlparser.HtmlParserApplicationTests;
import ru.glushets.htmlparser.models.Dictionary;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryServiceImplTest extends HtmlParserApplicationTests {

    @Autowired
    private DictionaryServiceImpl dictionaryService;

    private Map<String, Integer> getTestMap() {
        Map<String, Integer> testMap = new TreeMap<>();
        testMap.put("test1", 1);
        testMap.put("test2", 2);
        testMap.put("test3", 3);
        return testMap;
    }

    @Test
    @Transactional
    void testGetAllElementDictionary() {
        List<Dictionary> listTest = dictionaryService.getAllElementDictionary("0");
        assertNotNull(listTest);
        listTest = dictionaryService.getAllElementDictionary("10");
        assertNotNull(listTest);
        listTest = dictionaryService.getAllElementDictionary("-10");
        assertNotNull(listTest);
    }

    @Test
    @Transactional
    public void testAddMapElementToDictionary() {
        Map<String, Integer> testMap = getTestMap();
        dictionaryService.addMapElementToDictionary(testMap, "https://test.ru");
        List<Dictionary> listTest = dictionaryService.getAllElementDictionary("0");
        assertEquals(3, listTest.size());
        assertEquals(0, dictionaryService.getStartElement());
        assertEquals(3, dictionaryService.getEndElement());
        int count = 1;
        for (Dictionary dictionary : listTest) {
            assertEquals("test" + count, dictionary.getWord());
            assertEquals(count, dictionary.getCount());
            assertEquals("https://test.ru", dictionary.getUrl());
            count++;
        }
        dictionaryService.getAllElementDictionary("10");
        assertEquals(2, dictionaryService.getStartElement());
        dictionaryService.getAllElementDictionary("-10");
        assertEquals(0, dictionaryService.getStartElement());
    }

    @Test
    @Transactional
    public void testDeleteAllElementDictionary() {
        Map<String, Integer> testMap = getTestMap();
        List<Dictionary> listTest = dictionaryService.getAllElementDictionary("0");
        assertEquals(0, listTest.size());
        dictionaryService.addMapElementToDictionary(testMap, "https://test.ru");
        listTest = dictionaryService.getAllElementDictionary("0");
        assertEquals(3, listTest.size());
        dictionaryService.deleteAllElementDictionary();
        listTest = dictionaryService.getAllElementDictionary("0");
        assertEquals(0, listTest.size());
    }
}