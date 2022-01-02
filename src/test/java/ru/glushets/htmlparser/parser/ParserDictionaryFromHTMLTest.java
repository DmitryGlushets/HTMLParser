package ru.glushets.htmlparser.parser;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.glushets.htmlparser.HtmlParserApplicationTests;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ParserDictionaryFromHTMLTest extends HtmlParserApplicationTests {

    @Autowired
    private ParserDictionaryFromHTML parserDictionaryFromHTML;

    @Test
    void getTextFromHTMLPage() {
        String text = parserDictionaryFromHTML.getTextFromHTMLPage("https://google.ru");
        assertNotNull(text);
        parserDictionaryFromHTML.getTextFromHTMLPage("https://googleZZZZ.ru");
        assertEquals("url адрес не существует", parserDictionaryFromHTML.getError());
    }

    @Test
    void getArraySting() {
        String[] array = parserDictionaryFromHTML.getArraySting("https://google.ru");
        assertNotNull(array);
        assertNotEquals(0, array.length);
        array = parserDictionaryFromHTML.getArraySting("https://googleZZZZ.ru");
        assertEquals(0, array.length);
    }

    @Test
    void getDictionary() {
        Map<String, Integer> mapTest = parserDictionaryFromHTML.getDictionary("https://google.ru");
        assertNotNull(mapTest);
        assertNotEquals(0, mapTest.size());
        parserDictionaryFromHTML.getDictionary("google.ru");
        assertEquals("Не введен https://", parserDictionaryFromHTML.getError());
    }
}