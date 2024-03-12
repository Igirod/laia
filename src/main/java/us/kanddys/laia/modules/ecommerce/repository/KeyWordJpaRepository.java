package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.KeyWord;

@Repository
public interface KeyWordJpaRepository extends JpaRepository<KeyWord, Long> {

   @Query(value = "SELECT id FROM key_words WHERE word = ?1", nativeQuery = true)
   public Long findKeyWordIdByWord(String word);
}
