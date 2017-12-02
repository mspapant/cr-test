package com.synnous.cr.core.domain.entity.root;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.UUIDGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * The id entity.
 *
 * @author : Manos Papantonakos.
 */
@MappedSuperclass
public class IdEntity implements Serializable {

    private static final long serialVersionUID = -1L;

    /** The id. */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2", parameters = {@Parameter(name = UUIDGenerator.UUID_GEN_STRATEGY_CLASS, value = "com.b4tb.misc.uuid.UUIDVersion1Generator")})
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "IdEntity{" +
                "id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final IdEntity idEntity = (IdEntity) o;

        return !(id != null ? !id.equals(idEntity.id) : idEntity.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
