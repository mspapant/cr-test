package com.synnous.cr.core.domain.repository;

import com.synnous.cr.core.domain.entity.PropertyPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * The property person repository.
 *
 * @author : Manos Papantonakos.
 */
public interface PropertyPersonRepository extends JpaRepository<PropertyPerson, String>, JpaSpecificationExecutor<PropertyPerson> {
}
