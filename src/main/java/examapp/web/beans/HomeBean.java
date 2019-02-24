package examapp.web.beans;

import examapp.domain.models.service.DocumentServiceModel;
import examapp.domain.models.view.DocumentViewModel;
import examapp.domain.models.view.DocumentsViewModel;
import examapp.service.DocumentService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.LinkedList;
import java.util.List;

@Named("home")
@RequestScoped
public class HomeBean {
    private List<DocumentsViewModel> models;
    private DocumentService documentService;
    private ModelMapper mapper;

    public HomeBean() {
    }

    @Inject
    public HomeBean(DocumentsViewModel documentViewModel, DocumentServiceModel documentServiceModel, DocumentService documentService, ModelMapper mapper) {
        this.documentService = documentService;
        this.mapper = mapper;
        this.models = new LinkedList<>(this.documentService.findAll());
    }

//    public List<DocumentsViewModel> showFive(){
//        List<DocumentsViewModel> documents = new LinkedList<>();
//        for (int i = 0; i < this.models.size(); i+=5) {
//             documents.addAll(this.models.get(i),this.models.get(i+1));
//
//        }
//    }

    public List<DocumentsViewModel> getModels() {
        //return this.showFive();
        return this.models;
    }

    public void setModels(List<DocumentsViewModel> models) {
        this.models = models;
    }
}
