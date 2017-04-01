package com.demo.repositories;



import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.domain.HitCounter;


/**
 * @author ekansh
 * @since 2/4/16
 */
public interface HitCounterRepository extends JpaRepository<HitCounter,Long> {

	HitCounter findByCounterDate(Date counterDate);
}
