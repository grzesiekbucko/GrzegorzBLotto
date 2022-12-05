package pl.lotto.resultchecker;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ResultCheckerFacadeTestImpl implements ResultCheckerRepository {
    Map<String, TicketResult> database = new ConcurrentHashMap<>();

    @Override
    public void insertAll(List<TicketResult> list) {
        list.stream()
                .map(ticket -> database.put(ticket.getTickedId(), ticket));
    }

    @Override
    public List<TicketResult> findByLotteryDate(LocalDateTime lotteryDate) {
        return database.values()
                .stream()
                .filter(ticket -> ticket.getLotteryDate().isEqual(lotteryDate))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TicketResult> findByTickedId(String tickedId) {
        return Optional.ofNullable(database.get(tickedId));
    }


    @Override
    public <S extends TicketResult> S save(S entity) {
        return null;
    }

    @Override
    public <S extends TicketResult> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<TicketResult> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<TicketResult> findAll() {
        return null;
    }

    @Override
    public Iterable<TicketResult> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(TicketResult entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends TicketResult> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<TicketResult> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<TicketResult> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends TicketResult> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends TicketResult> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends TicketResult> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends TicketResult> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends TicketResult> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends TicketResult> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends TicketResult> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends TicketResult> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends TicketResult, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

}
