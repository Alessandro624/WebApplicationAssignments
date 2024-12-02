package it.unical.demacs.informatica.ristoranti.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTest {
    private final IUserService service;

    UserServiceTest(UserService service) {
        this.service = service;
    }

    @Test
    void dependencyTest() {
        assertNotNull(service);
    }

    @Test
    void whenNeedTryToCreateANEwUSer_Then_theUSerWasCreated() {
    }
}
