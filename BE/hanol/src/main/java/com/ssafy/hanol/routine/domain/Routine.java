package com.ssafy.hanol.routine.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_id")
    private Long id;

    @Column(name = "routine_name")
    private String routineName;

    @Column(name = "is_basic", columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean isBasic;

    @Column(name = "is_value_1", columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean isValue1;

    @Column(name = "is_value_2", columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean isValue2;

    @Column(name = "is_value_3", columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean isValue3;

    @Column(name = "is_value_4", columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean isValue4;

    @Column(name = "is_value_5", columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean isValue5;

    @Column(name = "is_value_6", columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean isValue6;

    @Builder
    public Routine(String routineName, boolean isBasic, boolean isValue1, boolean isValue2, boolean isValue3, boolean isValue4, boolean isValue5, boolean isValue6) {
        this.routineName = routineName;
        this.isBasic = isBasic;
        this.isValue1 = isValue1;
        this.isValue2 = isValue2;
        this.isValue3 = isValue3;
        this.isValue4 = isValue4;
        this.isValue5 = isValue5;
        this.isValue6 = isValue6;
    }
}
