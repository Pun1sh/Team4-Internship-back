package com.exadel.booking.user.authority;

import com.exadel.booking.utils.modelmapper.AMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorityService {

    private final AuthorityRepository authorityRepository;
    private final AMapper<Authority, AuthorityDto> authorityMapper;

    public AuthorityDto getAuthorityById(UUID id) {
        return authorityMapper.toDto(authorityRepository.findAuthorityById(id));
    }

    public AuthorityDto getAuthorityByName(String name) {
        return authorityMapper.toDto(authorityRepository.findAuthorityByName(name));
    }

    public List<AuthorityDto> getAllAuthorities() {
        return authorityMapper.toListDto(authorityRepository.findAll());
    }
}
