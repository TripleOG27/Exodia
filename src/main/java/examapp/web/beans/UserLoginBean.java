package examapp.web.beans;

import examapp.domain.models.binding.UserLoginBindingModel;
import examapp.domain.models.service.UserServiceModel;
import examapp.service.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@Named("login")
@RequestScoped
public class UserLoginBean {
    private UserService userService;
    private UserLoginBindingModel userLoginBindingModel;
    private ModelMapper mapper;

    public UserLoginBean() {
    }
    @Inject
    public UserLoginBean(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
        this.userLoginBindingModel = new UserLoginBindingModel();
    }

    public UserLoginBindingModel getUserLoginBindingModel() {
        return userLoginBindingModel;
    }

    public void setUserLoginBindingModel(UserLoginBindingModel userLoginBindingModel) {
        this.userLoginBindingModel = userLoginBindingModel;
    }
    public void login() throws IOException {

        UserServiceModel userServiceModel =this.userService.userLogin(this.mapper.map(userLoginBindingModel,UserServiceModel.class));
        if(userServiceModel==null){
            FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/views/login.xhtml");
            return;
        }
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("logged-in",true);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/views/home.xhtml");
    }
}
