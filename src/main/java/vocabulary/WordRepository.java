package vocabulary;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface WordRepository extends MongoRepository<Word, String> {
    public List<Word> findByWordOrTranslationsAllIgnoreCase(String word, String translation);
}