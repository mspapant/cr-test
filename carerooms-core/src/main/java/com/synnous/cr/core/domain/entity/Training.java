package com.synnous.cr.core.domain.entity;

import com.synnous.cr.core.domain.entity.root.IdTimestampEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The training
 *
 * @author : Manos Papantonakos.
 */
@Entity(name = "TRAINING")
public class Training extends IdTimestampEntity {

    static final long serialVersionUID = 1L;

    /** The user. */
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    /** The safeguarding training. */
    @ManyToOne
    @JoinColumn(name = "SAFEGUARDING_TRAINING_ID")
    private Document safeguardingTraining;

    /** The cleaning training. */
    @ManyToOne
    @JoinColumn(name = "CLEANING_TRAINING_ID")
    private Document cleaningTraining;

    /** The food training. */
    @ManyToOne
    @JoinColumn(name = "FOOD_TRAINING_ID")
    private Document foodTraining;

    /** The mendal capacity. */
    @ManyToOne
    @JoinColumn(name = "MENDAL_CAPACITY_ID")
    private Document mendalCapacity;

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Document getSafeguardingTraining() {
        return safeguardingTraining;
    }

    public void setSafeguardingTraining(final Document safeguardingTraining) {
        this.safeguardingTraining = safeguardingTraining;
    }

    public Document getCleaningTraining() {
        return cleaningTraining;
    }

    public void setCleaningTraining(final Document cleaningTraining) {
        this.cleaningTraining = cleaningTraining;
    }

    public Document getFoodTraining() {
        return foodTraining;
    }

    public void setFoodTraining(final Document foodTraining) {
        this.foodTraining = foodTraining;
    }

    public Document getMendalCapacity() {
        return mendalCapacity;
    }

    public void setMendalCapacity(final Document mendalCapacity) {
        this.mendalCapacity = mendalCapacity;
    }

    @Override
    public String toString() {
        return "Training{" +
                "user=" + user +
                ", safeguardingTraining=" + safeguardingTraining +
                ", cleaningTraining=" + cleaningTraining +
                ", foodTraining=" + foodTraining +
                ", mendalCapacity=" + mendalCapacity +
                "} " + super.toString();
    }
}
