package dev.friday.com.dal.repository.neo;

import dev.friday.com.dal.domain.entity.neo.Neo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface NEORepository extends JpaRepository<Neo, Long> {

    Optional<Neo> findByObservationDateAndId(Date observationDate, String id);
}
