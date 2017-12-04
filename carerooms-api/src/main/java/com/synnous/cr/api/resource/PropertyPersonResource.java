package com.synnous.cr.api.resource;

import java.util.Date;

/**
 * The user resource
 *
 * @author : Manos Papantonakos.
 */
public class PropertyPersonResource {

    /** The id. */
    private String id;

    /** The updated at. */
    private Date updatedAt;

    /** The created at. */
    private Date createdAt;

    /** The first name */
    private String firstName;

    /** The last name */
    private String lastName;

    /** The dbs certification. */
    private DBSCertificationResource dbsCertification;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public DBSCertificationResource getDbsCertification() {
        return dbsCertification;
    }

    public void setDbsCertification(final DBSCertificationResource dbsCertification) {
        this.dbsCertification = dbsCertification;
    }

    @Override
    public String toString() {
        return "PropertyPersonResource{" +
                "id='" + id + '\'' +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dbsCertification=" + dbsCertification +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final PropertyPersonResource that = (PropertyPersonResource) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
