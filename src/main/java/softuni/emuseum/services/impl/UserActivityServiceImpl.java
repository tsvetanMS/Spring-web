package softuni.emuseum.services.impl;

import org.springframework.stereotype.Service;
import softuni.emuseum.entities.UserActivity;
import softuni.emuseum.repositories.UserActivityRepository;
import softuni.emuseum.services.api.UserActivityService;

import java.time.LocalDateTime;

@Service
public class UserActivityServiceImpl implements UserActivityService {

    private final UserActivityRepository userActivityRepository;

    public UserActivityServiceImpl(UserActivityRepository userActivityRepository) {
        this.userActivityRepository = userActivityRepository;

    }


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


}
