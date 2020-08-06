package softuni.emuseum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.emuseum.entities.User;
import softuni.emuseum.entities.UserActivity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {

    void deleteAllByDateTimeBefore(LocalDateTime localDateTime);

    Optional<List<UserActivity>> findByUsername(String name);


}
