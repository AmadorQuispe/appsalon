package com.amsoft.appsalon.repositories;

import com.amsoft.appsalon.models.User;

public interface UserRepository extends CrudRepository<User, String> {
    User findByEmail(String email);
}
