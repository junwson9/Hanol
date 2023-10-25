package com.ssafy.hanol.diagnosis.service;

import com.ssafy.hanol.diagnosis.repository.DiagnosisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class DiagnosisService {

    private final DiagnosisRepository diagnosisRepository;



}
