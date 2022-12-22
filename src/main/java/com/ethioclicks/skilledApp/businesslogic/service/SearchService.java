package com.ethioclicks.skilledApp.businesslogic.service;


import com.ethioclicks.skilledApp.businesslogic.entity.Services;

import java.util.List;

public interface SearchService {
    List<Services> serviceSearch(String keyword);
    List<Services> serviceSearch(String keyword,Integer rate);
}
