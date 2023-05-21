package dev.friday.com.dal.service.cme;

import dev.friday.com.dal.domain.entity.cme.CME;
import dev.friday.com.dal.domain.entity.cme.dto.CMEDTO;
import dev.friday.com.dal.repository.cme.CMERepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CMEService {

    private final CMERepository cmeRepository;

    public void save(CME cme) {
        cmeRepository.save(cme);
    }

    public CMEDTO findAll() {
        return CMEDTO.fromEntity(cmeRepository.findAll().get(0));
    }
}
