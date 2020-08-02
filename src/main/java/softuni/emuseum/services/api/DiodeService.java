package softuni.emuseum.services.api;

import softuni.emuseum.entities.Diode;
import softuni.emuseum.entities.Resistor;
import softuni.emuseum.models.service.DiodeServiceModel;
import softuni.emuseum.models.service.ResistorServiceModel;
import softuni.emuseum.models.service.TransistorServiceModel;

import java.util.List;

public interface DiodeService {

    void loadDiodeInDatabase(Diode diode);

    List<DiodeServiceModel> findAllDiodes();

    void deleteDiodeById(Long id);

    DiodeServiceModel findDiodeById(Long id);

    void saveDiodeInDatabase(DiodeServiceModel diodeSM);
}
