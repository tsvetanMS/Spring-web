package softuni.emuseum.services.impl;

import org.springframework.stereotype.Service;
import softuni.emuseum.entities.PageStat;
import softuni.emuseum.repositories.PageStatRepository;
import softuni.emuseum.services.api.PageStatService;

import java.time.LocalDateTime;

@Service
public class PageStatServiceImpl implements PageStatService {

    private final PageStatRepository pageStatRepository;


    public PageStatServiceImpl(PageStatRepository pageStatRepository) {
        this.pageStatRepository = pageStatRepository;
    }

//----------------------------------------------------------------------------------------------------------------------
    @Override
    public Long findCounterByPageRoute(String pageRoute) {

        PageStat pageStat = this.pageStatRepository.findByRoute(pageRoute).orElse(null);

        if(pageStat == null){
           return 0L;
        } else {
            return pageStat.getVisits();
        }

    }
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public void updatePageCounter(String pageRoute) {

        PageStat pageStat = this.pageStatRepository.findByRoute(pageRoute).orElse(null);

        if(pageStat == null){
            pageStat = new PageStat(pageRoute, 1L, LocalDateTime.now());
        } else {
            Long counter = pageStat.getVisits();
            counter ++;
            pageStat.setVisits(counter);
            pageStat.setLastVisit(LocalDateTime.now());
        }

        this.pageStatRepository.saveAndFlush(pageStat);
    }
//----------------------------------------------------------------------------------------------------------------------
}
