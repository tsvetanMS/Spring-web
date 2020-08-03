package softuni.emuseum.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.emuseum.entities.Diode;
import softuni.emuseum.entities.Resistor;
import softuni.emuseum.entities.Transistor;
import softuni.emuseum.models.service.DiodeServiceModel;
import softuni.emuseum.models.service.ResistorServiceModel;
import softuni.emuseum.models.service.TransistorServiceModel;
import softuni.emuseum.repositories.DiodeRepository;
import softuni.emuseum.services.api.DiodeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiodeServiceImpl implements DiodeService {

    private final DiodeRepository diodeRepository;
    private final ModelMapper modelMapper;


    public DiodeServiceImpl(DiodeRepository diodeRepository, ModelMapper modelMapper) {
        this.diodeRepository = diodeRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void loadDiodeInDatabase(Diode diode) {
        if(this.diodeRepository.count() == 0) {
            this.diodeRepository.save(diode);
        }
    }

    @Override
    public List<DiodeServiceModel> findAllDiodes() {
        List<DiodeServiceModel> result = diodeRepository.findAll().stream()
                .map(diode -> this.modelMapper.map(diode, DiodeServiceModel.class))
                .collect(Collectors.toList());

        return result;

    }

    @Override
    public void deleteDiodeById(Long id) {
        this.diodeRepository.deleteById(id);
    }

    @Override
    public DiodeServiceModel findDiodeById(Long id) {
        Diode diode =  this.diodeRepository.findById(id).orElse(null);
        DiodeServiceModel diodeSM = this.modelMapper.map(diode, DiodeServiceModel.class);

        return diodeSM;
    }

    @Override
    public void saveDiodeInDatabase(DiodeServiceModel diodeSM) {

        Diode diode = this.modelMapper.map(diodeSM, Diode.class);

        this.diodeRepository.saveAndFlush(diode);

    }
}


