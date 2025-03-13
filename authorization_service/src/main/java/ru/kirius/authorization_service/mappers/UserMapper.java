package ru.kirius.authorization_service.mappers;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kirius.authorization_service.entities.UserBookshop;

public class UserMapper {

    public UserDetails userDetailsFromUserBookShop(UserBookshop user) {
        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRole())
                .build();
    }

    public UserBookshop userBookshopFromUserDetails(UserDetails user) {
        UserBookshop userBookshop = new UserBookshop();
        userBookshop.setUsername(user.getUsername());
        userBookshop.setPassword(user.getPassword());
        userBookshop.setRole(authoritiesToRole((GrantedAuthority) user.getAuthorities().toArray()[0]));
        return userBookshop;
    }

    private String authoritiesToRole(GrantedAuthority authority) {
        String result = authority.getAuthority();
        if(result.startsWith("ROLE_")) {
            return result.substring(5);
        }
        return result;
    }
}
