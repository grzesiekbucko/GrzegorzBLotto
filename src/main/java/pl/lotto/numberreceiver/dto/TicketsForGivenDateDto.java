package pl.lotto.numberreceiver.dto;

import java.util.List;
import java.util.UUID;

public record TicketsForGivenDateDto(String uuid, List<Integer> userNumbers) {

}
