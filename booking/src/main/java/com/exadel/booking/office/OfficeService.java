package com.exadel.booking.office;

import com.exadel.booking.utils.modelmapper.AMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OfficeService {
    private final OfficeRepository officeRepository;
    private final AMapper<Office, OfficeDto> officeMapper;


    public OfficeDto getOfficeById(UUID id) {
        return officeMapper.toDto(Optional.ofNullable(officeRepository.findOfficeById(id)).orElseThrow(() ->
                new EntityNotFoundException("no office with id" + id)));
    }

    public List<OfficeDto> getAllOffices() {
        return officeMapper.toListDto(officeRepository.findAll());
    }

    public OfficeDto getOfficeByNumber(Integer number) {
        return officeMapper.toDto(Optional.ofNullable(officeRepository.findOfficeByNumber(number)).orElseThrow(() ->
                new EntityNotFoundException("no office with number" + number)));
    }

    public OfficeDto getOfficeByName(String name) {
        return officeMapper.toDto(Optional.ofNullable(officeRepository.findOfficeByName(name)).orElseThrow(() ->
                new EntityNotFoundException("no office with name" + name)));
    }

}
