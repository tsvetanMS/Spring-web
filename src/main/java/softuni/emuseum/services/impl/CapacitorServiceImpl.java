package softuni.emuseum.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.emuseum.entities.Capacitor;
import softuni.emuseum.entities.Transistor;
import softuni.emuseum.models.service.CapacitorServiceModel;
import softuni.emuseum.models.service.TransistorServiceModel;
import softuni.emuseum.repositories.CapacitorRepository;
import softuni.emuseum.services.api.CapacitorService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CapacitorServiceImpl implements CapacitorService {

    private final CapacitorRepository capacitorRepository;
    private final ModelMapper modelMapper;



    public CapacitorServiceImpl(CapacitorRepository capacitorRepository, ModelMapper modelMapper) {
        this.capacitorRepository = capacitorRepository;
        this.modelMapper = modelMapper;
    }
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public void loadCapacitorInDatabase(CapacitorServiceModel capacitorSM) {
        if(this.capacitorRepository.count() < 7) {
            this.capacitorRepository.save(this.modelMapper.map(capacitorSM, Capacitor.class));
        }
    }

    @Override
    public List<CapacitorServiceModel> findAllCapacitors() {
        List<CapacitorServiceModel> result = capacitorRepository.findAll().stream()
                .map(capacitor -> this.modelMapper.map(capacitor, CapacitorServiceModel.class))
                .collect(Collectors.toList());

        return result;

    }

    @Override
    public void deleteCapacitorById(Long id) {
        this.capacitorRepository.deleteById(id);
    }

    @Override
    public CapacitorServiceModel findCapacitorById(Long id) {
        Capacitor capacitor =  this.capacitorRepository.findById(id).orElse(null);
        CapacitorServiceModel capacitorSM = this.modelMapper.map(capacitor, CapacitorServiceModel.class);

        return capacitorSM;
    }

    @Override
    public void saveCapacitorInDatabase(CapacitorServiceModel capacitorSM) {

        Capacitor capacitor = this.modelMapper.map(capacitorSM, Capacitor.class);

        this.capacitorRepository.saveAndFlush(capacitor);

    }
}

