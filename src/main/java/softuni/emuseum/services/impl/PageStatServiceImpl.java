package softuni.emuseum.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.emuseum.entities.PageStat;
import softuni.emuseum.errorhandling.errors.PageStatNotFoundException;
import softuni.emuseum.models.responce.PageStatResponseModel;
import softuni.emuseum.models.service.PageStatServiceModel;
import softuni.emuseum.repositories.PageStatRepository;
import softuni.emuseum.services.api.PageStatService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PageStatServiceImpl implements PageStatService {

    private final PageStatRepository pageStatRepository;
    private final ModelMapper modelMapper;



    public PageStatServiceImpl(PageStatRepository pageStatRepository, ModelMapper modelMapper) {
        this.pageStatRepository = pageStatRepository;
        this.modelMapper = modelMapper;
    }

//----------------------------------------------------------------------------------------------------------------------
    @Override
    public Long findCounterByPageRoute(String pageRoute) {

        PageStat pageStat = this.pageStatRepository.findByRoute(pageRoute).orElse(null);

        if(pageStat == null){
            throw new PageStatNotFoundException("Page stat database is empty!");
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
    @Override
    public List<PageStatServiceModel> findAllPages() {
        List<PageStatServiceModel> pages = this.pageStatRepository.findAll().stream()
                .map(page -> this.modelMapper.map(page, PageStatServiceModel.class))
                .collect(Collectors.toList());

        return pages;

    }
//----------------------------------------------------------------------------------------------------------------------

}
