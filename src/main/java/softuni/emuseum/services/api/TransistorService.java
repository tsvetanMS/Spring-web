package softuni.emuseum.services.api;

import softuni.emuseum.entities.Transistor;
import softuni.emuseum.models.service.TransistorServiceModel;

import java.util.List;

public interface TransistorService {

    void loadTransistorInDatabase(TransistorServiceModel transistorSM);

    List<TransistorServiceModel> findAllTransistors();

    void deleteTransistorById(Long id);

    TransistorServiceModel findTransistorById(Long id);

    void saveTransistorInDatabase(TransistorServiceModel transistorSM);
}
