package com.synnous.cr.core.domain.service;

import com.synnous.cr.core.domain.entity.DBSCertification;
import com.synnous.cr.core.domain.entity.Document;
import com.synnous.cr.core.domain.entity.Property;
import com.synnous.cr.core.domain.enumeration.DocumentType;

/**
 * The property service.
 *
 * @author : Manos Papantonakos.
 */
public interface PropertyService {

    /**
     * Returns the property for user.
     *
     * @param userId
     *         the user id
     * @return the property
     */
    Property getProperty(final String userId);

    /**
     * Updates the property.
     *
     * @param userId
     *         the user id
     * @param propertyId
     *         the property id
     * @param property
     *         the property
     * @return the property
     */
    Property updateProperty(final String userId, final String propertyId, final Property property);

    /**
     * Updates the property.
     *
     * @param userId
     *         the user id
     * @param propertyId
     *         the property id
     * @param documentType
     *         the document type
     * @return the property
     */
    Property updatePropertyDocument(final String userId, final String propertyId, final DocumentType documentType, final Document document);

    /**
     * Updates the property person.
     *
     * @param userId
     *         the user id
     * @param propertyId
     *         the property id
     * @param personId
     *         the person id
     * @return the property
     */
    DBSCertification updatePropertyPersonDBSCertification(final String userId, final String propertyId, final String personId, final DBSCertification request);
}
