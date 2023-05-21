package dev.friday.com.dal.repository.cme;

import dev.friday.com.dal.domain.entity.cme.CME;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CMERepository extends JpaRepository<CME, Long> {
}
