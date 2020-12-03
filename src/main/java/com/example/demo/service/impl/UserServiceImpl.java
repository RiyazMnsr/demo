package com.example.demo.service.impl;

import com.example.demo.constant.DemoConstant;
import com.example.demo.constant.ErrorMessage;
import com.example.demo.dto.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Value("${update_value}")
    private String updateValue;

    @Override
    public Integer countUniqueUsers(List<User> users) {
        List<User> distinctElements = users.stream().filter(distinctByKey(User::getUserId))
                .collect(Collectors.toList());
        return distinctElements.size();
    }

    @Override
    public User getUpdatedUser(List<User> users) {

        if (users.size() < DemoConstant.REQUEST_UPDATE_USER) {
            throw new UserNotFoundException(ErrorMessage.NO_USER_FOUND);
        }

        User user = users.get(DemoConstant.REQUEST_UPDATE_USER - 1);
        user.setBody(updateValue);
        user.setTitle(updateValue);
        return user;
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
