package com.exadel.booking.repository;

import com.exadel.booking.AbstractTest;
import com.exadel.booking.user.User;
import com.exadel.booking.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest extends AbstractTest {

    @Autowired
    private UserRepository userDao;

    @Test
    public void whenFindByEmail_thenReturnUser() {
        User user = createUser();
        User found = userDao.findUserByEmail(user.getEmail());
        assertThat(found.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void whenFindById_thenReturnUser() {
        User user = createUser();
        User found = userDao.findUserById(user.getId());
        assertThat(found.getId()).isEqualTo(user.getId());
    }
}
