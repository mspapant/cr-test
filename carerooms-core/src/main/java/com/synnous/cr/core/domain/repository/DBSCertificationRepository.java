package com.synnous.cr.core.domain.repository;

import com.synnous.cr.core.domain.entity.DBSCertification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * The document repository.
 *
 * @author : Manos Papantonakos.
 */
public interface DBSCertificationRepository extends JpaRepository<DBSCertification, String>, JpaSpecificationExecutor<DBSCertification> {

}
