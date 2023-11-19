package com.amsoft.appsalon.services;

import java.util.Optional;

import com.amsoft.appsalon.Exceptions.UserException;
import com.amsoft.appsalon.models.User;
import com.amsoft.appsalon.models.UserDetail;

public interface UserService {
    Optional<User> save(User user) throws UserException;

    Optional<UserDetail> login(String username, String password);

}
