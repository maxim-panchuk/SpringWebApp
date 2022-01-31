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
@Table(name= "t_access_token")
public class AccessToken {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "token_id", length = 6, nullable = false)
    private Long id;

    private Long audit_id;
    private Long user_id;
    private Timestamp expire_date;
}
