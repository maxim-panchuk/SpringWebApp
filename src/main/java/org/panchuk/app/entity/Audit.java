package org.panchuk.app.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name= "t_audit")
public class Audit {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "audit_id", length = 6, nullable = false)
    private Long id;

    private Long user_id;
    private int action_type;
    private Timestamp action_date;
}
