package ba.unsa.etf.nwt.user_service.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.OffsetDateTime;
import java.util.Optional;


@Configuration
@EntityScan("ba.unsa.etf.nwt.user_service")
@EnableJpaRepositories("ba.unsa.etf.nwt.user_service")
@EnableTransactionManagement
public class DomainConfig {

    @Bean(name = "auditingDateTimeProvider")
    public DateTimeProvider dateTimeProvider() {
        return () -> Optional.of(OffsetDateTime.now());
    }

}
