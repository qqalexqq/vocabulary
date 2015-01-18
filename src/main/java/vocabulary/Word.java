package vocabulary;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "words")
public class Word {
    @Id
    private String word;

    private List<String> translations;

    public Word() {}

    public Word(String word, String translation) {
        this(word, Arrays.asList(translation));
    }

    public Word(String word, List<String> translations) {
        this.word = word.toLowerCase();

        for (String translation : translations) {
            translation = translation.toLowerCase();
        }

        this.translations = translations;
    }

    public String getWord() {
        return this.word;
    }

    public List<String> getTranslations() {
        return this.translations;
    }

    public void addTranslation(String translation) {
        this.translations.add(translation);
    }

    public void deleteTranslations() {
        this.translations.clear();
    }

    @Override
    public String toString() {
        return String.format(
            "Word[word=%s, translations='%s']",
            this.word, this.translations
        );
    }
}