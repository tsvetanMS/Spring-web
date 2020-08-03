package softuni.emuseum.services.api;

import softuni.emuseum.models.responce.PageStatResponseModel;
import softuni.emuseum.models.service.PageStatServiceModel;

import java.util.List;
import java.util.Optional;

public interface PageStatService {

    Long findCounterByPageRoute(String pageRoute);

    void updatePageCounter(String pageRoute);

    List<PageStatServiceModel> findAllPages();
}
