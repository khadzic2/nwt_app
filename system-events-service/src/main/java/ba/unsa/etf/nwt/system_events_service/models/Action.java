package ba.unsa.etf.nwt.system_events_service.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Action {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private Instant timestamp;

    @Column(nullable = false)
    private String service;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String actionType;

    @Column(nullable = false)
    private String resourceName;

    @Column(nullable = false)
    private String responseType;
}
