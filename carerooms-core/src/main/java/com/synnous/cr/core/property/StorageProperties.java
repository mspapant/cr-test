package com.synnous.cr.core.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * Properties
 *
 * @author mspapant
 */
@Validated
@ConfigurationProperties("app.storage")
public class StorageProperties {

    private String avatarBucket;

    private String propertyDocumentBucket;

    private String trainingDocumentBucket;

    private String awsProfile;

    public String getAwsProfile() {
        return awsProfile;
    }

    public void setAwsProfile(final String awsProfile) {
        this.awsProfile = awsProfile;
    }

    public String getAvatarBucket() {
        return avatarBucket;
    }

    public void setAvatarBucket(final String avatarBucket) {
        this.avatarBucket = avatarBucket;
    }

    public String getPropertyDocumentBucket() {
        return propertyDocumentBucket;
    }

    public void setPropertyDocumentBucket(final String propertyDocumentBucket) {
        this.propertyDocumentBucket = propertyDocumentBucket;
    }

    public String getTrainingDocumentBucket() {
        return trainingDocumentBucket;
    }

    public void setTrainingDocumentBucket(final String trainingDocumentBucket) {
        this.trainingDocumentBucket = trainingDocumentBucket;
    }
}
