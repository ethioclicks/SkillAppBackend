package com.ethioclicks.skilledApp.businesslogic.service.imp;

import com.ethioclicks.skilledApp.businesslogic.entity.Services;
import com.ethioclicks.skilledApp.businesslogic.repo.ServicesRepo;
import com.ethioclicks.skilledApp.businesslogic.service.SearchService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SearchServiceImpl implements SearchService {

    private final ServicesRepo servicesRepo;

    public SearchServiceImpl(ServicesRepo servicesRepo) {
        this.servicesRepo = servicesRepo;
    }
    @Override
    public List<Services> serviceSearch(String keyword) {
        List<Services> matchingProducts = new ArrayList<>();

        String[] searchWords = getSearchWords(keyword);

        if (searchWords != null  ) {
            if(searchWords.length > 1){
                List partialMatchProducts = new ArrayList();
                for (String searchWord : searchWords) {
                    List<Services> partialResult = servicesRepo.getServiceByKeyword(searchWord);
                    if (partialResult != null && !partialResult.isEmpty()) {
                        partialMatchProducts.addAll(partialResult);
                    }
                }
                if (!partialMatchProducts.isEmpty()) {
                    matchingProducts.addAll(partialMatchProducts);
                }
            }
            if (searchWords.length == 1) {
                matchingProducts = servicesRepo.getServiceByKeyword(keyword);
            }

        }
        return matchingProducts;
    }
    private String[] getSearchWords(String keyword) {
        String[] searchWords = keyword.split(" ");
        if (searchWords != null && searchWords.length > 0) {
            // the best  shop
            for (int i = 0; i < searchWords.length; i++) {
                searchWords[i] = searchWords[i].trim();
            }
        }
        return searchWords;
    }
}
