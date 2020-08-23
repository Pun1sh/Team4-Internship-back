package com.exadel.booking.entities.booking;

import com.exadel.booking.entities.office.floor.room.place.PlaceRepository;
import com.exadel.booking.entities.user.UserRepository;
import com.exadel.booking.utils.modelmapper.AMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.UUID;

@Component
public class BookingMapper extends AMapper<Booking, BookingDto> {

    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;

    public BookingMapper(ModelMapper mapper, UserRepository userRepository, PlaceRepository placeRepository) {
        super(Booking.class, BookingDto.class);
        this.placeRepository = placeRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Booking.class, BookingDto.class).addMappings(m -> {
            m.skip(BookingDto::setPlaceId);
        }).setPostConverter(toDtoConverter());
        mapper.createTypeMap(BookingDto.class, Booking.class).addMappings(m -> {
            m.skip(Booking::setPlace);
        }).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFieldsToDtoConverter(Booking source, BookingDto destination) {
        destination.setPlaceId(getPlaceDtoId(source));
    }


    private UUID getPlaceDtoId(Booking source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null
                : source.getPlace().getId();
    }

    @Override
    public void mapSpecificFieldsToEntityConverter(BookingDto source, Booking destination) {
        destination.setPlace(placeRepository.getOne(source.getPlaceId()));
    }
}
