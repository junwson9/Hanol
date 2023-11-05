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
@Table(name = "examination_result")
public class ExaminationResult extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "examination_result_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToOne
    @JoinColumn(name = "examination_survey_id", nullable = false)
    private ExaminationSurvey examinationSurvey;

    @Column(name = "type_1", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean type1;

    @Column(name = "type_2", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean type2;

    @Column(name = "type_3", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean type3;

    @Column(name = "type_4", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean type4;

    @Column(name = "type_5", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean type5;

    @Column(name = "type_6", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean type6;

    @Builder
    public ExaminationResult(Member member, ExaminationSurvey examinationSurvey, Boolean type1, Boolean type2, Boolean type3, Boolean type4, Boolean type5, Boolean type6) {
        this.member = member;
        this.examinationSurvey = examinationSurvey;
        this.type1 = type1;
        this.type2 = type2;
        this.type3 = type3;
        this.type4 = type4;
        this.type5 = type5;
        this.type6 = type6;
    }

}
