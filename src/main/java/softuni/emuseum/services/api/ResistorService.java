package softuni.emuseum.services.api;

import softuni.emuseum.entities.Resistor;
import softuni.emuseum.entities.Transistor;
import softuni.emuseum.models.service.ResistorServiceModel;
import softuni.emuseum.models.service.TransistorServiceModel;

import java.util.List;

public interface ResistorService {

    void loadResistorInDatabase(Resistor resistor);

    List<ResistorServiceModel> findAllResistors();

    void deleteResistorById(Long id);

    ResistorServiceModel findResistorById(Long id);

    void saveResistorInDatabase(ResistorServiceModel resistorSM);
}
