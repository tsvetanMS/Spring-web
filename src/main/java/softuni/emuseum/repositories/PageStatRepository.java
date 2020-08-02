package softuni.emuseum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.emuseum.entities.PageStat;

import java.util.Optional;

@Repository
public interface PageStatRepository extends JpaRepository<PageStat, Long> {

    Optional<PageStat> findByRoute(String route);

}
