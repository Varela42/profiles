package com.mx.marketplace.care.profiles.service;

import com.mx.marketplace.care.profiles.dto.HomeDTO;

public interface HomeService {

    HomeDTO getHomeData(Long userId);
}
