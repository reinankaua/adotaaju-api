package br.com.adotaaju.adotaajuapi.domain.service;

import br.com.adotaaju.adotaajuapi.domain.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TutorService {

    @Autowired
    private TutorRepository tutorRepository;



}
