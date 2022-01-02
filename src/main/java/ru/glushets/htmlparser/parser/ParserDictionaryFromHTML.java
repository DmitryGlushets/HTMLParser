package ru.glushets.htmlparser.parser;

import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Getter
public class ParserDictionaryFromHTML implements ParserDictionary {

    private static final Logger log = Logger.getLogger(ParserDictionaryFromHTML.class);

    private String error;

    public String getTextFromHTMLPage(String url) {
        Document document;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            log.warn("url адрес не существует " + e.getMessage());
            error = "url адрес не существует";
            return null;
        }
        return document.body().text();
    }

    public String[] getArraySting(String url) {
        String result = getTextFromHTMLPage(url);

        if (result == null) {
            return new String[0];
        }

        result = result.replaceAll("[^a-zA-Zа-яёА-ЯЁ ]", "").replaceAll("( +)", " ").trim();
        return result.split(" ");
    }

    public Map<String, Integer> getDictionary(String url) {
        log.info("Обработка " + url);
        Map<String, Integer> dictionary = new HashMap<>();
        error = "";

        if (url.startsWith("http://") || url.startsWith("https://")) {
            String[] arraySting = getArraySting(url);
            for (String tempSting : arraySting) {
                if (dictionary.containsKey(tempSting)) {
                    dictionary.put(tempSting, dictionary.get(tempSting) + 1);
                } else {
                    dictionary.put(tempSting, 1);
                }
            }
        } else {
            log.warn("Не введен HTTP — протокол ->" + url);
            error = "Не введен https://";
        }

        if (error.equals("")) {
            log.info("Адрес  " + url + " обработан");
        }

        return dictionary;
    }
}
