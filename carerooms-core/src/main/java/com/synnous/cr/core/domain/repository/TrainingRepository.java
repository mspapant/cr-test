package com.synnous.cr.core.domain.repository;

import com.synnous.cr.core.domain.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, String> {

}
