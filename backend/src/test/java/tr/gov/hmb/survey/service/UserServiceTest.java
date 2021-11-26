package tr.gov.hmb.survey.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tr.gov.hmb.survey.entity.User;
import tr.gov.hmb.survey.exception.UserException;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(JUnit4.class)
class UserServiceTest {

    public static Long userID = 1L;

    @Autowired
    private UserService userService;

    @BeforeEach
    @Test
    void testUserInsert() throws UserException {
        User user = new User();
        user.setUsername("Deneme");
        user.setToken("48855122");
        user = userService.persist(user);
        userID = user.getId();

        assertNotNull(user);
    }

    @Test
    void testUserInsertErrorNotFound() {
        try {
            userService.persist(null);
        } catch (UserException e) {
            assertEquals(e.getMessage(),"Kullanıcı Bulunamadı!");
        }
    }

    @Test
    void testUserInsertError() {
        User user = new User();
        user.setToken("48855122");
        try {
            userService.persist(user);
        } catch (UserException e) {
            assertEquals(e.getMessage(),"Kullanıcı adı veya token boş olamaz!");
        }
    }

    @Test
    void testGetUser() {
        User user = userService.getUser(userID);
        assertNotNull(user);
    }

    @Test
    void testDeleteUser() {
        assertFalse(userService.delete(userID));
    }
}
