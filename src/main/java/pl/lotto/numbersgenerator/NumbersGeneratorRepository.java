package pl.lotto.numbersgenerator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
interface NumbersGeneratorRepository extends JpaRepository<WinningNumbers, Long> {

    WinningNumbers save(WinningNumbers winningNumbers);

    List<WinningNumbers> findAll();


}
