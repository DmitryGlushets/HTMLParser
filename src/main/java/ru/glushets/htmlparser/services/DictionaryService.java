package ru.glushets.htmlparser.services;

import org.springframework.stereotype.Service;
import ru.glushets.htmlparser.models.Dictionary;

import java.util.List;
import java.util.Map;

@Service
public interface DictionaryService {

    List<Dictionary> getAllElementDictionary(String startElement);

    void deleteAllElementDictionary();

    void addMapElementToDictionary(Map<String, Integer> dictionary, String url);

    int getStartElement();

    int getEndElement();
}
