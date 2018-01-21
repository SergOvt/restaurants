package ru.voting.api.restaurants.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.voting.api.restaurants.model.User;
import ru.voting.api.restaurants.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository{

    @Autowired
    private CrudUserRepository crudUserRepository;
    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;
    @Autowired
    private CrudVoteRepository crudVoteRepository;

    @Override
    public User get(int id) {
        return crudUserRepository.findById(id).orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return crudUserRepository.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return crudUserRepository.findAll();
    }

    @Override
    public User save(User user) {
        return crudUserRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return crudUserRepository.delete(id) != 0;
    }

    @Override
    public void setVote(Vote vote) {
        crudVoteRepository.save(vote);
    }

    @Override
    public Vote getVote(User user) {
        return crudVoteRepository.findByUserAndDate(user, LocalDate.now());
    }
}
