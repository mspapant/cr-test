package com.synnous.cr.core.domain.entity.root;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * The value id timestamp entity.
 *
 * @author : Manos Papantonakos.
 */
@MappedSuperclass
public class LookUpEntity extends IdEntity {

    public LookUpEntity() {
    }

    public LookUpEntity(final LookUpEntity other) {
        this.value = other.value;
    }

    public LookUpEntity(final String id, final String value) {
        this.value = value;
        this.id = id;
    }

    /** The created at. */
    @Column(name = "VALUE")
    protected String value;

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ValueIdTimestampEntity{" +
                "value='" + value + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        final LookUpEntity that = (LookUpEntity) o;

        return value != null ? value.equals(that.value) : that.value == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
