/*
package com.exadel.booking.repository;

import com.exadel.booking.AbstractTest;
import com.exadel.booking.entities.user.User;
import com.exadel.booking.entities.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
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

    @Test
    public void whenFindByUserName_thenReturnUser() {
        User user = createUser();
        User found = userDao.findUserByUsername(user.getUsername());
        assertThat(found.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    public void whenFindByLastName_thenReturnUser() {
        User user = createUser();
        List<User> found = userDao.findUserByLastName(user.getLastName());
        assertThat(found.get(0).getLastName()).isEqualTo(user.getLastName());
    }

    @Test
    public void whenFindByFirstName_thenReturnUser() {
        User user = createUser();
        List<User> found = userDao.findUserByFirstName(user.getFirstName());
        assertThat(found.get(0).getFirstName()).isEqualTo(user.getFirstName());
    }
}
*/
