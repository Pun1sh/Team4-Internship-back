package com.exadel.booking;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan("com.exadel.booking")
@EntityScan("com.exadel.booking")
class BookingApplicationTest {

}
