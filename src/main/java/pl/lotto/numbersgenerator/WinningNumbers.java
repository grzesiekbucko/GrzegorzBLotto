package pl.lotto.numbersgenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
class WinningNumbers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime date;
    @ElementCollection
    private List<Integer> numbers;

    WinningNumbers(LocalDateTime date, List<Integer> numbers) {
        this.date = date;
        this.numbers = numbers;
    }

    public WinningNumbers() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }
}
