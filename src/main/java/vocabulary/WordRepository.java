package vocabulary;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface WordRepository extends MongoRepository<Word, String> {
    public Word findByWord(String word);
    public List<Word> findByTranslations(String translation);
}