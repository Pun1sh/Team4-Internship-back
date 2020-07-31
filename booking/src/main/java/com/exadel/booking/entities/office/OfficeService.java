package com.exadel.booking.entities.office;

import com.exadel.booking.utils.modelmapper.AMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OfficeService {
    private final OfficeRepository officeRepository;
    private final AMapper<Office, OfficeDto> officeMapper;


    public OfficeDto getOfficeById(UUID id) {
        return officeMapper.toDto(findOfficeById(id));
    }

    public OfficeDto getOfficeByAddressId(UUID id) {
        return officeMapper.toDto(findOfficeByAddressId(id));
    }

    public List<OfficeDto> getAllOffices() {
        return officeMapper.toListDto(officeRepository.findAll());
    }

    public OfficeDto getOfficeByNumber(Integer number) {
        return officeMapper.toDto(findOfficeByNumber(number));
    }

    public OfficeDto saveOfficeFromDto(OfficeDto officeDto) {
        return officeMapper.toDto(officeRepository.save(officeMapper.toEntity(officeDto)));

    }

    public void deleteOfficeById(UUID id) {
        findOfficeById(id);
        officeRepository.deleteById(id);
    }

    public Office findOfficeById(UUID id) {
        return officeRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("no office with id " + id));
    }

    public Office findOfficeByAddressId(UUID id) {
        return officeRepository.findOfficeByAddressId(id).
                orElseThrow(() -> new EntityNotFoundException("no office with address id " + id));
    }

    public Office findOfficeByNumber(Integer number) {
        return officeRepository.findOfficeByNumber(number).
                orElseThrow(() -> new EntityNotFoundException("no office with number " + number));
    }
}
