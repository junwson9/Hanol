package com.ssafy.hanol.routine.domain;

import com.ssafy.hanol.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "member_routine_log",
        uniqueConstraints = @UniqueConstraint(columnNames = {"member_id", "routine_id", "date"})
)
public class MemberRoutineLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_routine_log_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id", nullable = false)
    private Routine routine;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "is_done", columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean isDone;

    @Builder
    public MemberRoutineLog(Member member, Routine routine, LocalDate date, boolean isDone) {
        this.member = member;
        this.routine = routine;
        this.date = date;
        this.isDone = isDone;
    }

    // 실천 여부를 변경하는 메서드
    public void updateDoneStatus(boolean isDone) {
        this.isDone = isDone;
    }

}
