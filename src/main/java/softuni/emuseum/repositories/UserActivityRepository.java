package softuni.emuseum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.emuseum.entities.UserActivity;

@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {
}
