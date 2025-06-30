package com.template.springproject.util;

import com.template.springproject.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * MemoryDataBase Class. <br/>
 * Description: Simulate a sync database.
 * @author JulioPuma
 */
@Component
public class MemoryDataBase {
    private List<User> usersFromDatabase = new ArrayList<>(Util.generateUsers());
    private Integer currentId = usersFromDatabase.size() + 1;

    public List<User> findAll() {
        usersFromDatabase.sort(Comparator.comparingInt(User::getId));
        return usersFromDatabase;
    }

    public User findById(Integer id) {
        return usersFromDatabase.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No existe usuario."));
    }

    public User save(User user) {
        user.setId(currentId++);
        usersFromDatabase.add(user);
        return user;
    }

    public User update(User user) {
        usersFromDatabase.remove(findById(user.getId()));
        usersFromDatabase.add(user);
        return user;
    }

    public void deleteById(Integer id) {
        usersFromDatabase.remove(findById(id));
    }
}
