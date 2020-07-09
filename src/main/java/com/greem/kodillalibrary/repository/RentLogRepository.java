package com.greem.kodillalibrary.repository;

import com.greem.kodillalibrary.domain.rentlog.RentLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface RentLogRepository extends CrudRepository<RentLog, Long> {
}
