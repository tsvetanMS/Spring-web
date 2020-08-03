package softuni.emuseum.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.emuseum.entities.UserActivity;
import softuni.emuseum.errorhandling.errors.UserActivityStatNotFoundException;
import softuni.emuseum.models.service.UserActivityServiceModel;
import softuni.emuseum.repositories.UserActivityRepository;
import softuni.emuseum.services.api.UserActivityStatService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional // без тази анотация метода cleanOldRecords() не иска да работи
public class UserActivityStatServiceImpl implements UserActivityStatService {

    private final UserActivityRepository userActivityRepository;
    private final ModelMapper modelMapper;



    public UserActivityStatServiceImpl(UserActivityRepository userActivityRepository, ModelMapper modelMapper) {
        this.userActivityRepository = userActivityRepository;

        this.modelMapper = modelMapper;
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
    @Override
    public List<UserActivityServiceModel> findAllUsersActivity() {
        List<UserActivity> userActivities = this.userActivityRepository.findAll();

        if(userActivities == null){
            throw new UserActivityStatNotFoundException("User activity database is empty!");
        } else {
           return userActivities.stream().map(userActivity -> this.modelMapper.map(userActivity, UserActivityServiceModel.class))
                    .collect(Collectors.toList());
        }

    }

//----------------------------------------------------------------------------------------------------------------------
}
