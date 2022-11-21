package pl.lotto.numbersgenerator;

import java.util.List;

import pl.lotto.numbersgenerator.dto.NumbersGeneratorResultDto;

public class NumbersGeneratorFacade {

    private final Draw draw;
    private final NumbersGeneratorRepository numbersGeneratorRepository;

    NumbersGeneratorFacade(Draw draw, NumbersGeneratorRepository numbersGeneratorRepository) {
        this.draw = draw;
        this.numbersGeneratorRepository = numbersGeneratorRepository;

    }

    NumbersGeneratorResultDto generateNumbers() {
        WinningNumbers winningNumbers = draw.startDrew();
        numbersGeneratorRepository.save(winningNumbers);
        return new NumbersGeneratorResultDto(winningNumbers.getDate(), winningNumbers.getNumbers());
    }

    public List<NumbersGeneratorResultDto> getWinningNumbers() {
        List<WinningNumbers> winningNumbers = numbersGeneratorRepository.findAll();
        return winningNumbers.stream()
                .map(WinningNumbers -> new NumbersGeneratorResultDto(WinningNumbers.getDate(), WinningNumbers.getNumbers()))
                .toList();
    }
}

