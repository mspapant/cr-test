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
public interface ResourceAssembler<D, R> {

    /**
     * To resource assembler.
     *
     * @param domain
     *         the domain object
     * @return the resource
     */
    R toResource(final D domain);

    /**
     * Transforms the list domain to resource list.
     *
     * @param list
     *         the list
     * @return the resources
     */
    default Set<R> toResource(final Set<D> list) {
        if (list == null || list.isEmpty()) {
            return new HashSet<>();
        }
        final Set<R> resource = new HashSet<>();
        for (final D d : list) {
            resource.add(toResource(d));
        }
        return resource;
    }

    /**
     * Transforms the list domain to resource list.
     *
     * @param list
     *         the list
     * @return the resources
     */
    default List<R> toResourceList(final List<D> list) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        final List<R> resource = new LinkedList<>();
        for (final D d : list) {
            resource.add(toResource(d));
        }
        return resource;
    }

    /**
     * Transforms the list domain to resource list.
     *
     * @param list
     *         the list
     * @return the resources
     */
    default List<R> toResourceSetList(final Set<D> list) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        final List<R> resource = new LinkedList<>();
        for (final D d : list) {
            resource.add(toResource(d));
        }
        return resource;
    }
}
