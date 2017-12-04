package com.synnous.cr.core.domain.repository;

import com.synnous.cr.core.domain.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * The property repository.
 *
 * @author : Manos Papantonakos.
 */
public interface PropertyRepository extends JpaRepository<Property, String>, JpaSpecificationExecutor<Property> {

    /**
     * Finds by user id.
     *
     * @param userId
     *         the user id
     * @return the property
     */
    List<Property> findByUserId(final String userId);
}
