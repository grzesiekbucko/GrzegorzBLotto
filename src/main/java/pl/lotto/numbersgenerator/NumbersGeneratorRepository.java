package pl.lotto.numbersgenerator;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NumbersGeneratorRepository extends MongoRepository<WinningNumbers, Long> {

    WinningNumbers save(WinningNumbers winningNumbers);

    List<WinningNumbers> findAll();

    WinningNumbers findByDate(LocalDateTime date);

}
