package com.ssafy.hanol.diagnosis.domain;

import com.ssafy.hanol.common.model.BaseTimeEntity;
import com.ssafy.hanol.diagnosis.service.dto.response.RabbitmqResponse;
import com.ssafy.hanol.member.domain.Member;
import lombok.*;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Table(name = "diagnosis")
public class Diagnosis extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diagnosis_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "value_1")
    private Integer value1;

    @Column(name = "value_2")
    private Integer value2;

    @Column(name = "value_3")
    private Integer value3;

    @Column(name = "value_4")
    private Integer value4;

    @Column(name = "value_5")
    private Integer value5;

    @Column(name = "value_6")
    private Integer value6;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "device_type", nullable = false)
    private int deviceType;

    @Column(name = "scan_part", nullable = false)
    private int scanPart;

    @Builder
    public Diagnosis(Member member, String imageUrl, int deviceType, int scanPart) {
        this.member = member;
        this.imageUrl = imageUrl;
        this.deviceType = deviceType;
        this.scanPart = scanPart;
    }

    @Builder
    public Diagnosis(Member member, Integer value1, Integer value2, Integer value3, Integer value4, Integer value5, Integer value6, String imageUrl, int deviceType, int scanPart) {
        this.member = member;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
        this.value5 = value5;
        this.value6 = value6;
        this.imageUrl = imageUrl;
        this.deviceType = deviceType;
        this.scanPart = scanPart;
    }

    // value값만 리스트로 추출하는 메서드
    public List<Integer> getValuesAsList() {
        return Arrays.asList(value1, value2, value3, value4, value5, value6);
    }

    public void updateValues(RabbitmqResponse response) {
        this.value1 = response.getValue1();
        this.value2 = response.getValue2();
        this.value3 = response.getValue3();
        this.value4 = response.getValue4();
        this.value5 = response.getValue5();
        this.value6 = response.getValue6();
    }

}
