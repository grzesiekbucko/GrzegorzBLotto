package pl.lotto.numberreceiver.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record TicketDto(UUID uuid, LocalDateTime drawDate, List<Integer> numbers) {
}
