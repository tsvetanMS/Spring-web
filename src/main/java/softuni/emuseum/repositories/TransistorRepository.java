package softuni.emuseum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.emuseum.entities.Transistor;

@Repository
public interface TransistorRepository extends JpaRepository<Transistor, Long> {

}
