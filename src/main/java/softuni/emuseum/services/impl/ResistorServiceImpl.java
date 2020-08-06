package softuni.emuseum.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.emuseum.entities.Resistor;
import softuni.emuseum.models.service.ResistorServiceModel;
import softuni.emuseum.repositories.ResistorRepository;
import softuni.emuseum.services.api.ResistorService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResistorServiceImpl implements ResistorService {

    private final ResistorRepository resistorRepository;
    private final ModelMapper modelMapper;


    public ResistorServiceImpl(ResistorRepository resistorRepository, ModelMapper modelMapper) {
        this.resistorRepository = resistorRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void loadResistorInDatabase(ResistorServiceModel resistorSM) {
        if(this.resistorRepository.count() == 0) {
            this.resistorRepository.save(this.modelMapper.map(resistorSM, Resistor.class));
        }
    }

    @Override
    public List<ResistorServiceModel> findAllResistors() {
        List<ResistorServiceModel> result = resistorRepository.findAll().stream()
                .map(resistor -> this.modelMapper.map(resistor, ResistorServiceModel.class))
                .collect(Collectors.toList());

        return result;

    }

    @Override
    public void deleteResistorById(Long id) {
        this.resistorRepository.deleteById(id);
    }

    @Override
    public ResistorServiceModel findResistorById(Long id) {
        Resistor resistor =  this.resistorRepository.findById(id).orElse(null);
        ResistorServiceModel resistorSM = this.modelMapper.map(resistor, ResistorServiceModel.class);

        return resistorSM;
    }

    @Override
    public void saveResistorInDatabase(ResistorServiceModel resistorSM) {

        Resistor resistor = this.modelMapper.map(resistorSM, Resistor.class);

        this.resistorRepository.saveAndFlush(resistor);

    }




}
