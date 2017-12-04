package com.synnous.cr.api.assembler.root;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * The resource assembler.
 *
 * @author : Manos Papantonakos on 16/1/2017.
 */
public interface DomainAssembler<D, R> {

    /**
     * To resource assembler.
     *
     * @param resource
     *         the resource object
     * @return the domain
     */
    D toDomain(final R resource);

    /**
     * Transforms the list resource to domain list.
     *
     * @param list
     *         the list
     * @return the domain objects
     */
    default List<D> toDomain(final List<R> list) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        final List<D> resource = new LinkedList<>();
        for (final R r : list) {
            resource.add(toDomain(r));
        }
        return resource;
    }

    /**
     * Transforms the list resource to domain list.
     *
     * @param list
     *         the list
     * @return the domain objects
     */
    default Set<D> toDomainSet(final Set<R> list) {
        if (list == null || list.isEmpty()) {
            return new HashSet<>();
        }
        final Set<D> resource = new HashSet<>();
        for (final R r : list) {
            resource.add(toDomain(r));
        }
        return resource;
    }
}
