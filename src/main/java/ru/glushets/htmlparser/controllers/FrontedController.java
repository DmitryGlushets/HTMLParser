package ru.glushets.htmlparser.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.glushets.htmlparser.models.Dictionary;
import ru.glushets.htmlparser.models.Statistics;
import ru.glushets.htmlparser.services.DictionaryService;
import ru.glushets.htmlparser.services.StatisticsService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class FrontedController {

    private final StatisticsService statisticsServiceImpl;
    private final DictionaryService dictionaryServiceImpl;

    @GetMapping("/parser")
    public String getParserPage(Model model) {
        List<Statistics> listStatistics = statisticsServiceImpl.getAllStatistics();
        model.addAttribute("listStatistics", listStatistics);
        return "/parserPage";
    }

    @PostMapping("/parsing")
    public String parsingPage(@RequestParam("url") String url, Model model, RedirectAttributes redirectAttributes) {
        Map<String, Integer> dictionary = statisticsServiceImpl.getDictionary(url);
        if (!statisticsServiceImpl.getError().equals("")) {
            redirectAttributes.addFlashAttribute("error", statisticsServiceImpl.getError());
            return "redirect:/parser";
        }
        statisticsServiceImpl.addStatistics(url);
        dictionaryServiceImpl.addMapElementToDictionary(dictionary, url);
        model.addAttribute("url", url);
        model.addAttribute("dictionary", dictionary);
        return "/dictionary";
    }

    @PostMapping("/statistics/{statistics-id}/delete")
    public String deleteRole(@PathVariable("statistics-id") Long statisticsId) {
        statisticsServiceImpl.deleteStatistics(statisticsId);
        return "redirect:/parser";
    }

    @GetMapping("dictionary")
    public String getDictionaryPage(@RequestParam(name = "start", required = false, defaultValue = "0") String start,
                                    Model model) {
        List<Dictionary> dictionaries = dictionaryServiceImpl.getAllElementDictionary(start);
        model.addAttribute("start", dictionaryServiceImpl.getStartElement());
        model.addAttribute("end", dictionaryServiceImpl.getEndElement());
        model.addAttribute("dictionaries", dictionaries);
        return "/dictionariesPage";
    }

    @PostMapping("/delete")
    public String deleteAllElementInDictionary() {
        dictionaryServiceImpl.deleteAllElementDictionary();
        return "redirect:/dictionary";
    }
}

