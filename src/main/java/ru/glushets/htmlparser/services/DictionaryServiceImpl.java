package ru.glushets.htmlparser.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.glushets.htmlparser.models.Dictionary;
import ru.glushets.htmlparser.repositories.DictionaryRepository;

import java.util.List;
import java.util.Map;

@Service
@Getter
@RequiredArgsConstructor
public class DictionaryServiceImpl implements DictionaryService {

    private static final int MAX_ELEMENT_IN_PAGE = 50;

    private final DictionaryRepository dictionaryRepository;

    private int startElement;

    private int endElement;

    @Override
    public List<Dictionary> getAllElementDictionary(String start) {
        List<Dictionary> dictionaryList = (List<Dictionary>) dictionaryRepository.findAll();

        if (dictionaryList.size() == 0) return dictionaryList;
        startElement = Integer.parseInt(start);
        if (startElement < 0) {
            startElement = 0;
        } else if (startElement > dictionaryList.size() - 1) {
            startElement = dictionaryList.size() - 1;
        }

        endElement = startElement + MAX_ELEMENT_IN_PAGE;
        if (endElement > dictionaryList.size()) {
            endElement = dictionaryList.size();
        }
        return dictionaryList.subList(startElement, endElement);
    }

    @Override
    public void deleteAllElementDictionary() {
        dictionaryRepository.deleteAll();
        startElement = 0;
        endElement = 0;
    }

    @Override
    public void addMapElementToDictionary(Map<String, Integer> dictionary, String url) {
        for (Map.Entry<String, Integer> entry : dictionary.entrySet()) {
            Dictionary tempElementDict = Dictionary.builder()
                    .url(url)
                    .word(entry.getKey())
                    .count(entry.getValue())
                    .build();
            dictionaryRepository.save(tempElementDict);
        }
    }
}
