package com.goormthon.backend.repository;

import com.goormthon.backend.entity.SpotInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotInfoRepository extends JpaRepository<SpotInfo, Long> {
}
