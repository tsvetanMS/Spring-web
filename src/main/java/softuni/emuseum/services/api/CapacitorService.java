package softuni.emuseum.services.api;

import softuni.emuseum.entities.Capacitor;
import softuni.emuseum.entities.Resistor;
import softuni.emuseum.models.service.CapacitorServiceModel;
import softuni.emuseum.models.service.ResistorServiceModel;
import softuni.emuseum.models.service.TransistorServiceModel;

import java.util.List;

public interface CapacitorService {

    void loadCapacitorInDatabase(Capacitor capacitor);

    List<CapacitorServiceModel> findAllCapacitors();

    void deleteCapacitorById(Long id);

    CapacitorServiceModel findCapacitorById(Long id);

    void saveCapacitorInDatabase(CapacitorServiceModel capacitorSM);
}
