package softuni.emuseum.services.api;

public interface PageStatService {

    Long findCounterByPageRoute(String pageRoute);
    void updatePageCounter(String pageRoute);
}
