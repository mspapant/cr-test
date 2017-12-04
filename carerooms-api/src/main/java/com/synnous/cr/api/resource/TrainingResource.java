package com.synnous.cr.api.resource;

import java.util.Date;

/**
 * The user resource
 *
 * @author : Manos Papantonakos.
 */
public class TrainingResource {

    /** The id. */
    private String id;

    /** The updated at. */
    private Date updatedAt;

    /** The created at. */
    private Date createdAt;

    /** The safeguarding training. */
    private DocumentResource safeguardingTraining;

    /** The cleaning training. */
    private DocumentResource cleaningTraining;

    /** The food training. */
    private DocumentResource foodTraining;

    /** The medal capacity. */
    private DocumentResource mendalCapacity;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    public DocumentResource getSafeguardingTraining() {
        return safeguardingTraining;
    }

    public void setSafeguardingTraining(final DocumentResource safeguardingTraining) {
        this.safeguardingTraining = safeguardingTraining;
    }

    public DocumentResource getCleaningTraining() {
        return cleaningTraining;
    }

    public void setCleaningTraining(final DocumentResource cleaningTraining) {
        this.cleaningTraining = cleaningTraining;
    }

    public DocumentResource getFoodTraining() {
        return foodTraining;
    }

    public void setFoodTraining(final DocumentResource foodTraining) {
        this.foodTraining = foodTraining;
    }

    public DocumentResource getMendalCapacity() {
        return mendalCapacity;
    }

    public void setMendalCapacity(final DocumentResource mendalCapacity) {
        this.mendalCapacity = mendalCapacity;
    }

    @Override
    public String toString() {
        return "TrainingResource{" +
                "id='" + id + '\'' +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                ", safeguardingTraining=" + safeguardingTraining +
                ", cleaningTraining=" + cleaningTraining +
                ", foodTraining=" + foodTraining +
                ", mendalCapacity=" + mendalCapacity +
                '}';
    }
}
