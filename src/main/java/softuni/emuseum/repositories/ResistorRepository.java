package softuni.emuseum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.emuseum.entities.Resistor;

@Repository
public interface ResistorRepository extends JpaRepository<Resistor, Long> {
}
