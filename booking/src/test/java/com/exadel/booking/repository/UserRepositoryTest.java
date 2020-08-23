package com.exadel.booking.repository;

import com.exadel.booking.AbstractTest;
import com.exadel.booking.entities.user.User;
import com.exadel.booking.entities.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class UserRepositoryTest extends AbstractTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByEmail_thenReturnUser() {
        User user = createUser();
        User found = userRepository.findUserByEmail(user.getEmail()).get();
        assertThat(found.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void whenFindById_thenReturnUser() {
        User user = createUser();
        User found = userRepository.findUserById(user.getId());
        assertThat(found.getId()).isEqualTo(user.getId());
    }

    @Test
    public void whenFindByUserName_thenReturnUser() {
        User user = createUser();
        User found = userRepository.findUserByUsername(user.getUsername());
        assertThat(found.getUsername()).isEqualTo(user.getUsername());
    }
 }

