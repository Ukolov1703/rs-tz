package org.rs.rstz.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "history")
public class HistoryEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "value")
    private Double value;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "create_datatime")
    private Date createDatetime;

    @Column(name = "id_card")
    private Integer idCard;
}
