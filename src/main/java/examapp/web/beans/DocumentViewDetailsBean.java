package examapp.web.beans;

import examapp.domain.models.service.DocumentServiceModel;
import examapp.domain.models.view.DocumentViewModel;
import examapp.service.DocumentService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Named("details")
@RequestScoped
public class DocumentViewDetailsBean {
    private DocumentViewModel documentViewModel;
    private DocumentServiceModel documentServiceModel;
    private DocumentService documentService;
    private ModelMapper mapper;

    public DocumentViewDetailsBean() {
    }

    @Inject
    public DocumentViewDetailsBean(DocumentViewModel documentViewModel, DocumentService documentService, ModelMapper mapper) {
        this.documentViewModel = new DocumentViewModel();
        this.documentServiceModel = new DocumentServiceModel();
        this.documentService = documentService;
        this.mapper = mapper;
        this.viewDetails();
    }

    public void viewDetails(){
        String id = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("id");
        //String id = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().getAttribute("document-id").toString();
        this.documentViewModel = this.mapper.map(this.documentService.findById(id),DocumentViewModel.class);
    }
    public void print(String id) throws IOException {

//        String id = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("id");
        //So this is where I managed to get to. All other business logic except from the bonus should be implemented properly.
        //The exception refers to the GuestFilter hence it doesn't even come in this method
        this.documentService.printDocument(id);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/views/home.xhtml");

    }
    public DocumentViewModel setDetails(String id){
                return this.documentViewModel = this.mapper.map(this.documentService.findById(id),DocumentViewModel.class);
    }
    public DocumentService getDocumentService() {
        return documentService;
    }

    public DocumentViewModel getDocumentViewModel() {
        return documentViewModel;
    }

    public void setDocumentViewModel(DocumentViewModel documentViewModel) {
        this.documentViewModel = documentViewModel;
    }

}
