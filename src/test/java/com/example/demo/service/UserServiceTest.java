package com.example.demo.service;

import com.example.demo.constant.ErrorMessage;
import com.example.demo.dto.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.impl.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserServiceImpl userService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void getUpdatedUser_success() {

        User firstUser = new User(1, 1, "Test First User", "Test First User");
        User secondUser = new User(1, 2, "Test Second User", "Test Second User");
        User thirdUser = new User(1, 3, "Test Third User", "Test Third User");
        User fourthUser = new User(2, 4, "Test Fourth User", "Test Fourth User");
        User fifthUser = new User(2, 5, "Test Fifth User", "Test Fifth User");
        User sixthUser = new User(2, 6, "Test Six User", "Test Sixth User");

        List<User> users = Arrays.asList(firstUser, secondUser, thirdUser, fourthUser, fifthUser, sixthUser);
        User updatedUser = userService.getUpdatedUser(users);
        assertAll(
                () -> Assertions.assertThat(updatedUser).isNotNull(),
                () -> Assertions.assertThat(fourthUser.getId().equals(updatedUser.getId())),
                () -> Assertions.assertThat("1800Flowers".equals(updatedUser.getTitle())),
                () -> Assertions.assertThat("1800Flowers".equals(updatedUser.getBody()))
        );
    }

    @Test
    public void getUpdatedUser_only_three_user_failed() {

        User firstUser = new User(1, 1, "Test First User", "Test First User");
        User secondUser = new User(1, 2, "Test Second User", "Test Second User");
        User thirdUser = new User(1, 3, "Test Third User", "Test Third User");

        thrown.expect(UserNotFoundException.class);
        thrown.expectMessage(ErrorMessage.NO_USER_FOUND);

        List<User> users = Arrays.asList(firstUser, secondUser, thirdUser);
        userService.getUpdatedUser(users);

    }

    @Test
    public void getCount_success() {

        User firstUser = new User(1, 1, "Test First User", "Test First User");
        User secondUser = new User(1, 2, "Test Second User", "Test Second User");
        User thirdUser = new User(1, 3, "Test Third User", "Test Third User");
        User fourthUser = new User(2, 4, "Test Fourth User", "Test Fourth User");
        User fifthUser = new User(2, 5, "Test Fifth User", "Test Fifth User");
        User sixthUser = new User(2, 6, "Test Six User", "Test Sixth User");

        List<User> users = Arrays.asList(firstUser, secondUser, thirdUser, fourthUser, fifthUser, sixthUser);
        Integer uniqueNo = userService.countUniqueUsers(users);
        assertAll(
                () -> Assertions.assertThat(uniqueNo).isNotNull(),
                () -> Assertions.assertThat(uniqueNo.equals(2))
        );
    }
}
