package softuni.emuseum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.emuseum.entities.Diode;

@Repository
public interface DiodeRepository extends JpaRepository<Diode, Long> {
}
