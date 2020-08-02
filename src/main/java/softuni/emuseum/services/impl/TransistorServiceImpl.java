package softuni.emuseum.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.emuseum.entities.Transistor;
import softuni.emuseum.models.service.TransistorServiceModel;
import softuni.emuseum.repositories.TransistorRepository;
import softuni.emuseum.services.api.TransistorService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransistorServiceImpl implements TransistorService {

    private final TransistorRepository transistorRepository;
    private final ModelMapper modelMapper;


    public TransistorServiceImpl(TransistorRepository transistorRepository, ModelMapper modelMapper) {
        this.transistorRepository = transistorRepository;
        this.modelMapper = modelMapper;
    }

//----------------------------------------------------------------------------------------------------------------------
    @Override
    public void loadTransistorInDatabase(Transistor transistor) {
        if(this.transistorRepository.count() < 10) {
            this.transistorRepository.save(transistor);
        }

    }
//----------------------------------------------------------------------------------------------------------------------
    @Override
    public List<TransistorServiceModel> findAllTransistors() {
        List<TransistorServiceModel> result = transistorRepository.findAll().stream()
                .map(transistor -> this.modelMapper.map(transistor, TransistorServiceModel.class))
                .collect(Collectors.toList());

        return result;
    }
//----------------------------------------------------------------------------------------------------------------------
    @Override
    public void deleteTransistorById(Long id) {
        this.transistorRepository.deleteById(id);
    }
//----------------------------------------------------------------------------------------------------------------------
    @Override
    public TransistorServiceModel findTransistorById(Long id) {
      Transistor transistor =  this.transistorRepository.findById(id).orElse(null);
      TransistorServiceModel transistorSM = this.modelMapper.map(transistor, TransistorServiceModel.class);

      return transistorSM;
    }
//----------------------------------------------------------------------------------------------------------------------
    @Override
    public void saveTransistorInDatabase(TransistorServiceModel transistorSM) {

        Transistor transistor = this.modelMapper.map(transistorSM, Transistor.class);

        this.transistorRepository.saveAndFlush(transistor);

    }
//----------------------------------------------------------------------------------------------------------------------

}
