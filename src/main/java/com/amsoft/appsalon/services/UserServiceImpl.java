package com.amsoft.appsalon.services;

import java.util.Optional;

import com.amsoft.appsalon.Exceptions.UserException;
import com.amsoft.appsalon.models.User;
import com.amsoft.appsalon.models.UserDetail;
import com.amsoft.appsalon.repositories.UserRepository;
import com.amsoft.appsalon.util.PasswordEncryption;
import com.amsoft.appsalon.util.TokenGenerator;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class UserServiceImpl implements UserService {
    @Inject
    private UserRepository userRepository;

    @Override
    public Optional<User> save(User user) throws UserException {
        user.setIsAdmin(false);
        user.setPassword(PasswordEncryption.encryptPassword(user.getPassword()));
        if (user.getUid() != null) {
            user.setIsConfirm(false);
            user.setTokenTemp(TokenGenerator.generateToken());
        }
        try {
            User userSaved = userRepository.save(user);
            return Optional.of(userSaved);
        } catch (Exception e) {
            if (e.getCause().getClass().getName() == "org.hibernate.exception.ConstraintViolationException") {
                throw new UserException("Email ya existe");
            }

            throw new RuntimeException("Error guardando");
        }
    }

    @Override
    public Optional<UserDetail> login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            boolean isValid = PasswordEncryption.validatePassword(password, user.getPassword());
            if (isValid) {
                UserDetail userDetail = new UserDetail();
                userDetail.setUid(user.getUid());
                userDetail.setFullName(user.getFirstName() + " " + user.getLastName());
                userDetail.setEmail(email);
                userDetail.setIsAdmin(user.getIsAdmin());
                return Optional.of(userDetail);
            }
        }
        return Optional.empty();
    }

}
