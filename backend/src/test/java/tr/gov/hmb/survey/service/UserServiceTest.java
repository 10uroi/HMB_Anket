package tr.gov.hmb.survey.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tr.gov.hmb.survey.entity.User;
import tr.gov.hmb.survey.exception.UserException;
import tr.gov.hmb.survey.util.LocaleMessage;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(JUnit4.class)
class UserServiceTest {

    public static Long userID = 1L;
    public static String userToken = "";

    @Autowired
    private LocaleMessage localeMessage;

    @Autowired
    private UserService userService;

    @BeforeEach
    @Test
    void testUserInsert() throws UserException {
        User user = new User();
        user.setUsername("Deneme");
        String token = new Random().nextInt(10000000)+"";
        userToken = token;
        user.setToken(userToken);
        user = userService.persist(user);
        userID = user.getId();

        assertNotNull(user);
    }

    @Test
    void testUserInsertErrorNotFound() {
        try {
            userService.persist(null);
        } catch (UserException e) {
            assertEquals(e.getMessage(),localeMessage.getMessage("user.not.found"));
        }
    }

    @Test
    void testUserInsertError() {
        User user = new User();
        user.setToken("48855122");
        try {
            userService.persist(user);
        } catch (UserException e) {
            assertEquals(e.getMessage(),localeMessage.getMessage("user.username.or.token.empty"));
        }
    }

    @Test
    void testGetUser() {
        User user = userService.getUser(userID);
        assertNotNull(user);
    }

    @Test
    void testGetToken() {
        User user = userService.getUser(userToken);
        assertNotNull(user);
    }

    @Test
    void testGetTokens() {
        List<String> tokens = userService.getTokens();
        assertNotNull(tokens);
    }

    @Test
    void testGetUserFindByToken() {
        User user = userService.getUser(userToken);
        assertNotNull(user);
    }

    @Test
    void testDeleteUser() {
        assertFalse(userService.delete(userID));
    }
}
