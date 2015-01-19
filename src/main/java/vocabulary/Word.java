package vocabulary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// Basically here we use Mongo as just a key-value storage although it's actually a document-based database.
@Document(collection = "words")
public class Word {
    @Id
    private String word;

    private List<String> translations = new ArrayList<String>();

    public Word() {}

    public Word(String word, String translation) {
        this(word, Arrays.asList(translation));
    }

    public Word(String word, List<String> translations) {
        this.word = word.toLowerCase();

        for (String translation : translations) {
            this.translations.add(translation.toLowerCase());
        }
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