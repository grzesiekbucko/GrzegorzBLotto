package pl.lotto;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class IntegrationConfiguration {

    @Primary
    @Bean
    Clock clock() {
        Instant fixedInstant = LocalDateTime.of(2022, 11, 30, 11, 0).toInstant(ZoneOffset.UTC);
        return Clock.fixed(fixedInstant, ZoneId.systemDefault());
    }
}
