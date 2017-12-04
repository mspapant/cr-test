package com.synnous.cr.api.assembler;

import com.synnous.cr.api.assembler.root.DomainAssembler;
import com.synnous.cr.api.assembler.root.ResourceAssembler;
import com.synnous.cr.api.resource.TrainingResource;
import com.synnous.cr.core.domain.entity.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The Training assembler.
 *
 * @author : Manos Papantonakos.
 */
@Component
public class TrainingAssembler implements ResourceAssembler<Training, TrainingResource>, DomainAssembler<Training, TrainingResource> {

    @Autowired
    private DocumentAssembler documentAssembler;

    @Override
    public TrainingResource toResource(final Training domain) {
        if (domain == null) {
            return null;
        }
        final TrainingResource target = new TrainingResource();
        target.setId(domain.getId());
        target.setSafeguardingTraining(documentAssembler.toResource(domain.getSafeguardingTraining()));
        target.setCleaningTraining(documentAssembler.toResource(domain.getCleaningTraining()));
        target.setFoodTraining(documentAssembler.toResource(domain.getFoodTraining()));
        target.setMendalCapacity(documentAssembler.toResource(domain.getMendalCapacity()));
        target.setCreatedAt(domain.getCreatedAt());
        target.setUpdatedAt(domain.getUpdatedAt());
        return target;
    }

    @Override
    public Training toDomain(final TrainingResource resource) {
        final Training target = new Training();
        target.setId(resource.getId());
        target.setSafeguardingTraining(documentAssembler.toDomain(resource.getSafeguardingTraining()));
        target.setCleaningTraining(documentAssembler.toDomain(resource.getCleaningTraining()));
        target.setFoodTraining(documentAssembler.toDomain(resource.getFoodTraining()));
        target.setMendalCapacity(documentAssembler.toDomain(resource.getMendalCapacity()));
        return target;
    }
}
