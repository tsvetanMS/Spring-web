package softuni.emuseum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.emuseum.entities.Capacitor;

@Repository
public interface CapacitorRepository extends JpaRepository<Capacitor, Long> {
}
