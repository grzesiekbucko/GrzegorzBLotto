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
//import pl.lotto.resultchecker.TicketResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class ResultAnnouncerController {

    //    @Autowired
//    private final ResultCheckerRepository resultCheckerRepository;

    private final ResultAnnouncerFacade resultAnnouncerFacade;
    private final ResultCheckerFacade resultCheckerFacade;
    private final NumbersGeneratorFacade numbersGeneratorFacade;

    @GetMapping("/winners/{uniqueId}")
    public ResponseEntity<ResultAnnouncerDto> getWinningNumbers(@PathVariable String uniqueId) {
        ResultAnnouncerDto resultAnnouncerDto = resultAnnouncerFacade.checkTicket(uniqueId);
        return ResponseEntity.ok(resultAnnouncerDto);
    }





//

//    @GetMapping("/generateWinningNumbers")
//    public void generateWinningNumbers() {
//        numbersGeneratorFacade.generateNumbers();
//    }
//
//    @GetMapping("/winners")
//    public void getWinningNumbers() {
//        resultCheckerFacade.checkAllWinnings();
//    }
//    @GetMapping("/find_winners_by_date")
//    public void getWinningByDate() {
////        LocalDateTime dateTime = LocalDateTime.of(2022, 12, 10,12,0);
////        resultCheckerFacade.checkWinningsForGivenDate(dateTime);
//        LocalDateTime drewDate = LocalDateTime.of(2022, 12, 10,12,0);
//        resultCheckerFacade.checkWinningsForGivenDate(drewDate);
//    }

//    @GetMapping("/testRepository")
//    public void testRepository() {
//        List<TicketResult> list = new ArrayList<>();
//
//        String uuid = UUID.randomUUID().toString();
//        LocalDateTime drawDate = LocalDateTime.of(2022, 11, 19, 12, 0);
//        List<Integer> winningNumbers = List.of(1, 20, 31, 42, 53, 64);
//        List<Integer> userNumbers = List.of(1, 2, 31, 4, 53, 64);
//        List<Integer> hitNumbers = List.of(1, 31, 53, 64);
//
//        String uuid2 = UUID.randomUUID().toString();
//        LocalDateTime drawDate2 = LocalDateTime.of(2022, 11, 19, 12, 0);
//        List<Integer> winningNumbers2 = List.of(1, 20, 31, 42, 53, 64);
//        List<Integer> userNumbers2 = List.of(1, 2, 33, 4, 53, 68);
//        List<Integer> hitNumbers2 = List.of(1, 53);
//
//        TicketResult ticket1 = new TicketResult(uuid, drawDate, winningNumbers, userNumbers, hitNumbers);
//        TicketResult ticket2 = new TicketResult(uuid2, drawDate2, winningNumbers2, userNumbers2, hitNumbers2);
//
//        list.add(ticket1);
//        list.add(ticket2);
//
//        resultCheckerRepository.insert(list);
//
//    }


}
