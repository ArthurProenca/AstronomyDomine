package dev.friday.com.dal.service.cme;

import dev.friday.com.dal.domain.entity.cme.CME;
import dev.friday.com.dal.domain.entity.cme.dto.CMEDTO;
import dev.friday.com.dal.repository.cme.CMERepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CMEService {

    private final CMERepository cmeRepository;

    public void save(CME cme) {
        if(cmeRepository.findByActivityId(cme.getActivityId()).isPresent()) {
            log.info("CME with activityId [{}] already exists", cme.getActivityId());
            return;
        }
        cmeRepository.save(cme);
    }

    public List<CMEDTO> findAll() {
        List<CME> cmes = cmeRepository.findAll();

        if(cmes.isEmpty()) {
            log.info("No CMEs found");
            return null;
        }
        List<CMEDTO> cmeDtos = new ArrayList<>();
        cmes.forEach(cme -> cmeDtos.add(CMEDTO.fromEntity(cme)));

        return cmeDtos;
    }
}
