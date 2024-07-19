package com.stock_data.stock_data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "results")
@Builder
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private Date date;

    @Column(nullable = false)
    private Double open;

    @Column(nullable = false)
    private Double high;

    @Column(nullable = false)
    private Double low;

    @Column(nullable = false)
    private Double close;

    @Column(nullable = false)
    private Double adjClose;

    @Column(nullable = false)
    private Integer volume;

    @Column(nullable = false)
    private Integer unadjustedVolume;

    @Column(nullable = false)
    private Double change;

    @Column(nullable = false)
    private Double changePercent;

    @Column(nullable = false)
    private Double vwap;

    @Column(nullable = false)
    private String label;

    @Column(nullable = false)
    private Double changeOvertime;


    @ManyToOne(cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Stock stock;
}
