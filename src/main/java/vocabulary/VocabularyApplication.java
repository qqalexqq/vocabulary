package vocabulary;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@EnableAutoConfiguration
@Controller
@SpringBootApplication
public class VocabularyApplication {

    @Autowired
    private WordRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(VocabularyApplication.class, args);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String words(Model model) {
        Collection<Word> words = repository.findAll();

        model.addAttribute("words", words);

        return "words";
    }

    @RequestMapping(value = "/word/add", method = RequestMethod.GET)
    public String addWord(Model model) {
        Word word = new Word();

        model.addAttribute("word", word);

        return "add-word";
    }

    @RequestMapping(value = "/word/add", method = RequestMethod.POST)
    public String addWordPOST(@RequestParam("word") String wordName, @RequestParam String translation) {
        Word word = new Word(wordName, translation);

        repository.save(word);

        return "redirect:/";
    }

    @RequestMapping(value = "/word/{word}/delete", method = RequestMethod.GET)
    public String deleteWord(@PathVariable("word") Word word) {
        repository.delete(word);

        return "redirect:/";
    }

    @RequestMapping(value = "/word/{word}/translation/add", method = RequestMethod.GET)
    public String addWordTranslation(@PathVariable("word") Word word, Model model) {
        model.addAttribute("word", word);

        return "add-word-translation";
    }

    @RequestMapping(value = "/word/{word}/translation/add", method = RequestMethod.POST)
    public String addWordTranslationPOST(@PathVariable("word") Word word, @RequestParam String translation) {
        word.addTranslation(translation);

        repository.save(word);

        return "redirect:/";
    }

    @RequestMapping(value = "/word/{word}/translation/delete", method = RequestMethod.GET)
    public String deleteWordTranslations(@PathVariable("word") Word word) {
        word.deleteTranslations();

        repository.save(word);

        return "redirect:/";
    }
}
