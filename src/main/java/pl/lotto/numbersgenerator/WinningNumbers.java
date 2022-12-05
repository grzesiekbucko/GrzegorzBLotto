package pl.lotto.numbersgenerator;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
public class WinningNumbers {
    @Id
    private String id;
    private LocalDateTime date;

    private List<Integer> numbers;

    WinningNumbers(LocalDateTime date, List<Integer> numbers) {
        this.date = date;
        this.numbers = numbers;
    }

    public WinningNumbers() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
