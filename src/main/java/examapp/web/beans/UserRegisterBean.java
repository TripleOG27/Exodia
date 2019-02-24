package examapp.web.beans;

import examapp.domain.models.binding.UserRegisterBindingModel;
import examapp.domain.models.service.UserServiceModel;
import examapp.service.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
@Named("register")
@RequestScoped
public class UserRegisterBean {
    private UserRegisterBindingModel userRegisterBindingModel;
    private UserService userService;
    private ModelMapper mapper;

    public UserRegisterBean() {

    }
    @Inject
    public UserRegisterBean(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
        this.userRegisterBindingModel = new UserRegisterBindingModel();
    }

    public UserRegisterBindingModel getUserRegisterBindingModel() {
        return userRegisterBindingModel;
    }

    public void setUserRegisterBindingModel(UserRegisterBindingModel userRegisterBindingModel) {
        this.userRegisterBindingModel = userRegisterBindingModel;
    }
    public void register() throws IOException {
        if(!this.userRegisterBindingModel.getPassword().equals(this.userRegisterBindingModel.getConfirmPassword())){
            throw new IllegalArgumentException("Passwords don't match!");
        }
        this.userService.registerUser(this.mapper.map(this.userRegisterBindingModel, UserServiceModel.class));
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/views/login.xhtml");
    }
}
