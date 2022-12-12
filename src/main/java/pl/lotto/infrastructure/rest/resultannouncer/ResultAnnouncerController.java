package pl.lotto.infrastructure.rest.resultannouncer;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.numbersgenerator.NumbersGeneratorFacade;
import pl.lotto.resultannouncer.ResultAnnouncerFacade;
import pl.lotto.resultannouncer.dto.ResultAnnouncerDto;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.ResultCheckerRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class ResultAnnouncerController {

    private final ResultAnnouncerFacade resultAnnouncerFacade;
    private final ResultCheckerFacade resultCheckerFacade;
    private final NumbersGeneratorFacade numbersGeneratorFacade;

    @GetMapping("/winners/{uniqueId}")
    public ResponseEntity<ResultAnnouncerDto> getWinningNumbers(@PathVariable String uniqueId) {
        ResultAnnouncerDto resultAnnouncerDto = resultAnnouncerFacade.checkTicket(uniqueId);
        return ResponseEntity.ok(resultAnnouncerDto);
    }

}
