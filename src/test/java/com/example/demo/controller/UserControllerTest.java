package com.example.demo.controller;

import com.example.demo.dto.User;
import com.example.demo.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @MockBean
    private UserService userService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    public void testCount() throws Exception {
        String url = "/user/count";
        User firstUser = new User(1, 1, "Test First User", "Test First User");
        User secondUser = new User(1, 2, "Test Second User", "Test Second User");
        User thirdUser = new User(1, 3, "Test Third User", "Test Third User");
        User fourthUser = new User(2, 4, "Test Fourth User", "Test Fourth User");
        User fifthUser = new User(2, 5, "Test Fifth User", "Test Fifth User");
        User sixthUser = new User(2, 6, "Test Six User", "Test Sixth User");

        List<User> users = Arrays.asList(firstUser, secondUser, thirdUser, fourthUser, fifthUser, sixthUser);

        Integer expectedCount = 2;

        // Fail - No content provided
        given(userService.countUniqueUsers(any(List.class)))
                .willReturn(null);
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(status().isBadRequest());

        // Pass
        given(userService.countUniqueUsers(any(List.class)))
                .willReturn(expectedCount);
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ControllerTestHelper.convertObjectToString(users)))
                .andExpect(status().isOk());
    }

    @Test
    public void testupdate() throws Exception {
        String url = "/user/count";
        User firstUser = new User(1, 1, "Test First User", "Test First User");
        User secondUser = new User(1, 2, "Test Second User", "Test Second User");
        User thirdUser = new User(1, 3, "Test Third User", "Test Third User");
        User fourthUser = new User(2, 4, "Test Fourth User", "Test Fourth User");
        User fifthUser = new User(2, 5, "Test Fifth User", "Test Fifth User");
        User sixthUser = new User(2, 6, "Test Six User", "Test Sixth User");

        List<User> users = Arrays.asList(firstUser, secondUser, thirdUser, fourthUser, fifthUser, sixthUser);

        User expectedUser = new User(2, 4, "1800Flowers", "1800Flowers");

        // Fail - No content provided
        given(userService.getUpdatedUser(any(List.class)))
                .willReturn(null);
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(status().isBadRequest());

        // Pass
        given(userService.getUpdatedUser(any(List.class)))
                .willReturn(expectedUser);
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ControllerTestHelper.convertObjectToString(users)))
                .andExpect(status().isOk());
    }
}
