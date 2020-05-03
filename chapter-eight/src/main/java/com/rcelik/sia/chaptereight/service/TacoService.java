package com.rcelik.sia.chaptereight.service;

import com.rcelik.sia.chaptereight.domain.Taco;

public interface TacoService {

    Iterable<Taco> getTacos(int pageIndex);
}
