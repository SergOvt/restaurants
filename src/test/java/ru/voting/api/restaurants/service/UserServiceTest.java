package ru.voting.api.restaurants.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.voting.api.restaurants.model.Role;
import ru.voting.api.restaurants.model.User;
import ru.voting.api.restaurants.util.exception.NotFoundException;

import java.util.Collections;
import java.util.List;

import static ru.voting.api.restaurants.TestData.*;

@ContextConfiguration("classpath:spring/spring-app.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = {"classpath:db/initDB.sql", "classpath:db/populateDB.sql"}, config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void testGet() throws Exception {
        User user = service.get(USER_1.getId());
        assertMatch(user, USER_1);
    }

    @Test
    public void testGetAll() throws Exception {
        List<User> users = service.getAll();
        assertMatch(users, USER_1, USER_2, ADMIN);
    }

    @Test
    public void testCreate() throws Exception {
        User created = new User(USER_NEW);
        service.create(created);
        created.setId(4);
        assertMatch(service.get(4), created);
    }

    @Test
    public void testUpdate() throws Exception {
        User updated = new User(USER_1);
        updated.setName("Updated");
        updated.setRoles(Collections.singleton(Role.ROLE_ADMIN));
        service.update(updated, USER_1.getId());
        assertMatch(service.get(USER_1.getId()), updated);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(USER_1.getId());
        assertMatch(service.getAll(), USER_2, ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(0);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        service.delete(0);
    }

}