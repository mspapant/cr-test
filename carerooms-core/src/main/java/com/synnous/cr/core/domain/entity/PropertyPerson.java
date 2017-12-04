package com.synnous.cr.core.domain.entity;

import com.synnous.cr.core.domain.entity.root.IdTimestampEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * The property person
 *
 * @author : Manos Papantonakos.
 */
@Entity(name = "PROPERTY_PERSON")
public class PropertyPerson extends IdTimestampEntity {

    static final long serialVersionUID = 1L;

    /** The property. */
    @ManyToOne
    @JoinColumn(name = "PROPERTY_ID")
    private Property property;

    /** The first name */
    @Column(name = "FIRST_NAME")
    private String firstName;

    /** The last name */
    @Column(name = "LAST_NAME")
    private String lastName;

    /** The dbs certification. */
    @ManyToOne
    @JoinColumn(name = "DBS_CERTIFICATION_ID")
    private DBSCertification dbsCertification;

    public Property getProperty() {
        return property;
    }

    public void setProperty(final Property property) {
        this.property = property;
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

    public DBSCertification getDbsCertification() {
        return dbsCertification;
    }

    public void setDbsCertification(final DBSCertification dbsCertification) {
        this.dbsCertification = dbsCertification;
    }

    @Override
    public String toString() {
        return "PropertyPerson{" +
                "property=" + property +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dbsCertification=" + dbsCertification +
                "} " + super.toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final PropertyPerson that = (PropertyPerson) o;

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
