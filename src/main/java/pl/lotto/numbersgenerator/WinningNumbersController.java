package pl.lotto.numbersgenerator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.numbersgenerator.dto.NumbersGeneratorResultDto;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

@RestController
class WinningNumbersController {
    Clock clock = Clock.fixed(LocalDateTime.of(2022, 11, 5, 12, 0).toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
    Draw draw = new Draw(clock);
    final NumbersGeneratorRepository numbersGeneratorRepository;

    public WinningNumbersController(NumbersGeneratorRepository numbersGeneratorRepository) {
        this.numbersGeneratorRepository = numbersGeneratorRepository;
    }

    @GetMapping("/winningNumbers")
    public List<NumbersGeneratorResultDto> getWinningNumbers() {
        NumbersGeneratorFacade numbersGeneratorFacade = new NumbersGeneratorFacade(draw, numbersGeneratorRepository);
        return numbersGeneratorFacade.getWinningNumbers();
    }

    @PostMapping("/winningNumbers")
    public NumbersGeneratorResultDto addWinningNumbers() {
        NumbersGeneratorFacade numbersGeneratorFacade = new NumbersGeneratorFacade(draw, numbersGeneratorRepository);
        return numbersGeneratorFacade.generateNumbers();
    }
}
