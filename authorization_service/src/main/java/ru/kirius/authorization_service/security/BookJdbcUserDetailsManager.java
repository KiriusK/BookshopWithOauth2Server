package ru.kirius.authorization_service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;
import ru.kirius.authorization_service.entities.UserBookshop;
import ru.kirius.authorization_service.mappers.UserMapper;
import ru.kirius.authorization_service.repositories.UserBookshopRepository;


public class BookJdbcUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {


    private UserBookshopRepository repo;

    private UserMapper mapper;


    public BookJdbcUserDetailsManager() {
    }

    public BookJdbcUserDetailsManager(UserBookshopRepository repo, UserMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public UserBookshopRepository getRepo() {
        return repo;
    }

    @Autowired
    public void setRepo(UserBookshopRepository repo) {
        this.repo = repo;
    }

    public UserMapper getMapper() {
        return mapper;
    }

    public void setMapper(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        if (userExists(user.getUsername())) {
            UserBookshop userEnt = mapper.userBookshopFromUserDetails(user);
            userEnt.setPassword(newPassword);
            UserDetails updUser = mapper.userDetailsFromUserBookShop(userEnt);
            updateUser(updUser);
            return updUser;
        }
        return null;
    }

    @Override
    public void createUser(UserDetails user) {
        if (!userExists(user.getUsername())) {
            repo.save(mapper.userBookshopFromUserDetails(user));
        }
    }

    @Override
    public void updateUser(UserDetails user) {
        UserBookshop userEnt = repo.findByUsername(user.getUsername()).orElse(null);
        if(userEnt != null) {
            UserBookshop newUser = mapper.userBookshopFromUserDetails(user);
            newUser.setId(userEnt.getId());
            repo.save(newUser);
        }
    }

    @Override
    public void deleteUser(String username) {
        if (userExists(username)) {
            repo.deleteByUsername(username);
        }
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        UserBookshop userEnt = repo.findByUsername(username).orElse(null);
        return userEnt != null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserBookshop userEnt = repo.findByUsername(username).orElse(null);
        if (userEnt != null) {
            return mapper.userDetailsFromUserBookShop(userEnt);
        }
        throw new UsernameNotFoundException("User not found");
    }

}
