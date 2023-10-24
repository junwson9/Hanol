package com.ssafy.hanol.routine.repository;

import com.ssafy.hanol.routine.domain.MemberRoutine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemberRoutineRepositoryImpl implements MemberRoutineRepository {

    private final JpaMemberRoutineRepository jpaMemberRoutineRepository;

    @Override
    public Optional<MemberRoutine> findById(Long id) {
        return jpaMemberRoutineRepository.findById(id);
    }

    @Override
    public List<MemberRoutine> findByMemberId(Long memberId) {
        return jpaMemberRoutineRepository.findByMemberId(memberId);
    }

    @Override
    public void save(MemberRoutine memberRoutine) {
        jpaMemberRoutineRepository.save(memberRoutine);
    }

    @Override
    public void deleteById(Long id) {
        jpaMemberRoutineRepository.deleteById(id);
    }

}
