package com.synnous.cr.core.domain.repository;

import com.synnous.cr.core.domain.entity.Application;
import com.synnous.cr.core.domain.entity.Appointment;
import com.synnous.cr.core.domain.entity.Property;
import com.synnous.cr.core.domain.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, String> {

    /**
     * Finds the application by property.
     *
     * @param property
     *          the property
     * @return  the application
     */
    Application findByProperty(final Property property);

    /**
     * Finds the application by training.
     *
     * @param training
     *          the training
     * @return  the application
     */
    Application findByTraining(final Training training);
}
