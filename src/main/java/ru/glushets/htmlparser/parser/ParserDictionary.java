package ru.glushets.htmlparser.parser;

import java.util.Map;

public interface ParserDictionary {

    Map<String, Integer> getDictionary(String source);

    String getError();
}
