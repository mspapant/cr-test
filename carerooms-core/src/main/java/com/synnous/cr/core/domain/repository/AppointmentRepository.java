package com.synnous.cr.core.domain.repository;

import com.synnous.cr.core.domain.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {

}
