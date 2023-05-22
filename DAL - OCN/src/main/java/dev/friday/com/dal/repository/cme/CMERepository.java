package dev.friday.com.dal.repository.cme;

import dev.friday.com.dal.domain.entity.cme.CME;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CMERepository extends JpaRepository<CME, Long> {

    Optional<CME> findByActivityId(String activityId);
}
