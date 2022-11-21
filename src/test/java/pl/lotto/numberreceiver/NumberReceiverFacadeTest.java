package pl.lotto.numberreceiver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.numberreceiver.dto.TicketsForGivenDateDto;

import java.time.*;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberReceiverFacadeTest {

    Clock clock = Clock.fixed(Instant.parse("2022-11-11T10:15:30.00Z"), ZoneId.of("Europe/Warsaw"));
    NumbersInputRepositoryTestImpl repository = new NumbersInputRepositoryTestImpl();

    @Test
    @DisplayName("should return false when user gave six various numbers")
    public void should_return_false_when_user_gave_six_various_numbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration().createModuleForTests(clock, repository);
        List<Integer> numbersFromUser = List.of(1, 2, 3, 4, 5, 6);
        // when
        NumberReceiverResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        // then
        assertThat(result.error()).isFalse();
    }

    @Test
    @DisplayName("should return true when user gave less than six numbers")
    public void should_return_true_when_user_gave_less_than_six_numbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration().createModuleForTests(clock, repository);
        List<Integer> numbersFromUser = List.of(1, 2, 3);
        // when
        NumberReceiverResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        // then
        assertThat(result.error()).isTrue();
        System.out.println(result.message());
    }

    @Test
    @DisplayName("should return true when user gave more than six numbers")
    public void should_return_true_when_user_gave_more_than_six_numbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration().createModuleForTests(clock, repository);
        List<Integer> numbersFromUser = List.of(1, 2, 3, 4, 5, 6, 7);
        // when
        NumberReceiverResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        // then
        assertThat(result.error()).isTrue();
        System.out.println(result.message());
    }

    @Test
    @DisplayName("should return true when user gave same number")
    public void should_return_true_when_user_gave_same_number() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration().createModuleForTests(clock, repository);
        List<Integer> numbersFromUser = List.of(1, 1, 2, 3, 4, 5);
        // when
        NumberReceiverResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        // then
        assertThat(result.error()).isTrue();
        System.out.println(result.message());
    }

    @Test
    @DisplayName("should return true when user gave number out of range")
    public void should_return_true_when_user_gave_number_out_of_range() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration().createModuleForTests(clock, repository);
        List<Integer> numbersFromUser = List.of(1, 2, 3, 4, 5, 222);
        // when
        NumberReceiverResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        // then
        assertThat(result.error()).isTrue();
        System.out.println(result.message());
    }

    @Test
    @DisplayName("should return true and message with errors when user has made mistakes in input number")
    public void should_return_true_and_message_with_errors_when_user_has_made_mistakes_in_input_number() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration().createModuleForTests(clock, repository);
        List<Integer> numbersFromUser = List.of(1, 1, 3, 5, 222);
        // when
        NumberReceiverResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        // then
        assertThat(result.error()).isTrue();
        System.out.println(result.message());
    }

    @ParameterizedTest
    @MethodSource("createLotteryTicketDay")
    @DisplayName("should return false and message with next draw date when user gave numbers")
    public void should_return_saturday_as_draw_date_when_today_is(LocalDateTime lotteryTicketDate) {
        // given
        Clock clock = Clock.fixed(lotteryTicketDate.toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration().createModuleForTests(clock, repository);
        List<Integer> numbersFromUser = List.of(1, 2, 3, 4, 5, 6);
        // when
        NumberReceiverResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        // then
        assertThat(result.error()).isFalse();
        System.out.println(result.drawTime());
    }

    private static Stream<Arguments> createLotteryTicketDay() {
        return Stream.of(
                Arguments.of(LocalDateTime.of(2022, 10, 17, 11, 0)),
                Arguments.of(LocalDateTime.of(2022, 10, 29, 11, 0)),
                Arguments.of(LocalDateTime.of(2022, 10, 29, 13, 0)),
                Arguments.of(LocalDateTime.now())
        );
    }

    @Test
    @DisplayName("should return false and message with lottery id when user gave number")
    public void should_return_false_and_message_with_lottery_id_when_user_gave_number() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration().createModuleForTests(clock, repository);
        List<Integer> numbersFromUser = List.of(1, 2, 3, 4, 5, 6);
        // when
        NumberReceiverResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        // then
        assertThat(result.error()).isFalse();
        assertThat(result.userLotteryId()).isNotEmpty();
    }

    @Test
    public void should_return_saturday_draw_date_when_today_is_wednesday() {
        // given
        Instant fixedInstant = LocalDateTime.of(2022, 11, 16, 11, 0).toInstant(ZoneOffset.UTC);
        Clock clock = Clock.fixed(fixedInstant, ZoneId.systemDefault());
        NumbersInputRepositoryTestImpl repository = new NumbersInputRepositoryTestImpl();
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration().createModuleForTests(clock, repository);
        // when
        NumberReceiverResultDto numberReceiverResultDto = numberReceiverFacade.inputNumbers(List.of(1, 2, 3, 4, 5, 6));
        // then
        Instant expected = LocalDateTime.of(2022, 11, 19, 12, 0).toInstant(ZoneOffset.UTC);
        assertThat(numberReceiverResultDto.drawTime().toInstant(ZoneOffset.UTC)).isEqualTo(expected);
    }

    @Test
    public void should_return_all_numbers_from_all_users_when_draw_date_was_given() {
        // given
        Clock clock = Clock.fixed(LocalDateTime.of(2022, 11, 16, 11, 0).toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
        NumbersInputRepositoryTestImpl repository = new NumbersInputRepositoryTestImpl();
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration().createModuleForTests(clock, repository);
        List<Integer> numbersFromUser = List.of(1, 2, 3, 4, 5, 6);
        numberReceiverFacade.inputNumbers(numbersFromUser);
        LocalDateTime date = LocalDateTime.of(2022, 11, 19, 12, 0);
        // when
        List<List<Integer>> result = numberReceiverFacade.retrieveAllNumbersForGivenDate(date);
        // then
        List<List<Integer>> expectedNumbersFromUsers = List.of(List.of(1, 2, 3, 4, 5, 6));
        assertThat(result).isEqualTo(expectedNumbersFromUsers);
    }

    @Test
    public void should_return_all_ticket_when_draw_date_was_given(){
        // given
        Clock clock = Clock.fixed(LocalDateTime.of(2022, 11, 16, 11, 0).toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
        NumbersInputRepositoryTestImpl repository = new NumbersInputRepositoryTestImpl();
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration().createModuleForTests(clock, repository);
        List<Integer> numbersFromUser = List.of(1, 2, 3, 4, 5, 6);
        numberReceiverFacade.inputNumbers(numbersFromUser);
        LocalDateTime date = LocalDateTime.of(2022, 11, 19, 12, 0);
        // when
        List<TicketsForGivenDateDto> result = numberReceiverFacade.retrieveAllTicketForGivenDate(date);
        // then
        assertThat(result).isNotEmpty();
    }

    @Test
    public void should_return_all_ticket(){
        // given
        Clock clock = Clock.fixed(LocalDateTime.of(2022, 11, 16, 11, 0).toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
        NumbersInputRepositoryTestImpl repository = new NumbersInputRepositoryTestImpl();
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration().createModuleForTests(clock, repository);
        List<Integer> numbersFromUser = List.of(1, 2, 3, 4, 5, 6);
        numberReceiverFacade.inputNumbers(numbersFromUser);
        // when
        List<TicketDto> result = numberReceiverFacade.retrieveAllTicket();
        // then
        assertThat(result).isNotEmpty();
    }



}
