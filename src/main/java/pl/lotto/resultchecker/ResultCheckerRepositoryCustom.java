package pl.lotto.resultchecker;

import java.util.List;

interface ResultCheckerRepositoryCustom {

    void insertAll(List<TicketResult> tickets);

}
