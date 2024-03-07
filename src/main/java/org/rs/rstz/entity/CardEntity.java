package org.rs.rstz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "card")
public class CardEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "number")
    private Integer number;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "id_client")
    private Integer idClient;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_card")
    private List<HistoryEntity> historyEntityList;
}
