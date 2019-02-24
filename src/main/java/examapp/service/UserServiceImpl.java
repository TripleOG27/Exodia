package examapp.service;

import examapp.domain.entities.User;
import examapp.domain.models.service.UserServiceModel;
import examapp.repositories.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        User user = this.mapper.map(userServiceModel,User.class);
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        this.userRepository.save(user);
    }

    @Override
    public UserServiceModel userLogin(UserServiceModel userServiceModel) {
        User user = this.userRepository.findByUsername(userServiceModel.getUsername());
        if(user==null||!DigestUtils.sha256Hex(userServiceModel.getPassword()).equals(user.getPassword())){
            return null;
        }
        return this.mapper.map(user,UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUserName(String username) {

        return this.mapper.map(this.userRepository.findByUsername(username),UserServiceModel.class);

    }
}
