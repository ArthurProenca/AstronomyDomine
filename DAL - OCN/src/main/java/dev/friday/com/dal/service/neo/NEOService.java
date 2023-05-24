package dev.friday.com.dal.service.neo;

import dev.friday.com.dal.domain.entity.neo.Neo;
import dev.friday.com.dal.repository.neo.NEORepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NEOService {
    private final NEORepository neoRepository;

    public void save(Neo neo) {
        if(neoRepository.findByObservationDateAndId(neo.getObservationDate(), neo.getId()).isPresent()) {
            log.info("NEO with id [{}] and observation date [{}] already exists", neo.getId(), neo.getObservationDate());
            return;
        }
       neoRepository.save(neo);
    }
}
