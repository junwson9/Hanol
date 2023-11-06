package com.ssafy.hanol.examination.domain;

import com.ssafy.hanol.common.model.BaseTimeEntity;
import com.ssafy.hanol.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "examination_survey")
public class ExaminationSurvey extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "examination_survey_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "answer_1", nullable = false, columnDefinition = "VARCHAR(30)")
    private String answer1;

    @Column(name = "answer_2", nullable = false, columnDefinition = "VARCHAR(30)")
    private String answer2;

    @Column(name = "answer_3", nullable = false, columnDefinition = "VARCHAR(30)")
    private String answer3;

    @Column(name = "answer_4", nullable = false, columnDefinition = "VARCHAR(30)")
    private String answer4;

    @Column(name = "answer_5", nullable = false, columnDefinition = "VARCHAR(30)")
    private String answer5;

    @Column(name = "answer_6", nullable = false, columnDefinition = "VARCHAR(30)")
    private String answer6;

    @Column(name = "answer_7", nullable = false, columnDefinition = "VARCHAR(30)")
    private String answer7;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "age", nullable = false)
    private int age;

    @Builder
    public ExaminationSurvey(Member member, String answer1, String answer2, String answer3, String answer4, String answer5, String answer6, String answer7, String gender, int age) {
        this.member = member;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.answer5 = answer5;
        this.answer6 = answer6;
        this.answer7 = answer7;
        this.gender = gender;
        this.age = age;
    }
}
