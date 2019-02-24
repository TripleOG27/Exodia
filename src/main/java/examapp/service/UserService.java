package examapp.service;

import examapp.domain.models.service.UserServiceModel;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);
    UserServiceModel userLogin(UserServiceModel userServiceModel);
    UserServiceModel findByUserName(String username);
}
