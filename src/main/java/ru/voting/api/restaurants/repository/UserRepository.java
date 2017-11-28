package ru.voting.api.restaurants.repository;

import ru.voting.api.restaurants.model.User;

import java.util.List;

public interface UserRepository {

    User get(String email);

    List<User> getAll();

    User save(User user);

    boolean delete(String email);

}
