package softuni.emuseum.services.impl;

import org.springframework.stereotype.Service;
import softuni.emuseum.entities.UserActivity;
import softuni.emuseum.repositories.UserActivityRepository;
import softuni.emuseum.services.api.UserActivityStatService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional // без тази анотация метода cleanOldRecords() не иска да работи
public class UserActivityStatServiceImpl implements UserActivityStatService {

    private final UserActivityRepository userActivityRepository;


    public UserActivityStatServiceImpl(UserActivityRepository userActivityRepository) {
        this.userActivityRepository = userActivityRepository;

    }

//----------------------------------------------------------------------------------------------------------------------
    @Override
    public void saveActivity(String username, String route) {

        // това се прави без new ! Това е статичен клас?
        LocalDateTime localDateTime = LocalDateTime.now();

        UserActivity userActivity = new UserActivity();
        userActivity.setUsername(username);
        userActivity.setRoute(route);
        userActivity.setDateTime(localDateTime);

        this.userActivityRepository.saveAndFlush(userActivity);
    }
//----------------------------------------------------------------------------------------------------------------------
    @Override
    public void cleanOldRecords() {
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(7L);

        this.userActivityRepository.deleteAllByDateTimeBefore(localDateTime);
    }

//----------------------------------------------------------------------------------------------------------------------
}
