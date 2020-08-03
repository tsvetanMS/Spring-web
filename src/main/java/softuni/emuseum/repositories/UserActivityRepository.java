package softuni.emuseum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.emuseum.entities.UserActivity;

import java.time.LocalDateTime;

@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {

    void deleteAllByDateTimeBefore(LocalDateTime localDateTime);

}
