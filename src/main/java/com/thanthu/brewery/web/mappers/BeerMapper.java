package com.thanthu.brewery.web.mappers;

import org.mapstruct.Mapper;

import com.thanthu.brewery.domain.Beer;
import com.thanthu.brewery.web.model.BeerDto;

@Mapper(uses = { DateMapper.class })
public interface BeerMapper {

	BeerDto beerToBeerDto(Beer beer);

	Beer beerDtoToBeer(BeerDto dto);
}
