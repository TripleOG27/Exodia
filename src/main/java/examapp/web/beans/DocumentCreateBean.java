package examapp.web.beans;

import examapp.domain.models.binding.DocumentBindingModel;
import examapp.domain.models.service.DocumentServiceModel;
import examapp.domain.models.view.DocumentViewModel;
import examapp.service.DocumentService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named("schedule")
@RequestScoped
public class DocumentCreateBean {
    private DocumentBindingModel documentBindingModel;

    private DocumentService documentService;
    private ModelMapper mapper;

    public DocumentCreateBean() {
    }

    @Inject
    public DocumentCreateBean(DocumentBindingModel documentBindingModel, DocumentService documentService, ModelMapper mapper) {
        this.documentBindingModel = new DocumentBindingModel();

        this.documentService = documentService;
        this.mapper = mapper;
    }
    public void create() throws IOException {
        DocumentServiceModel documentServiceModel= this.documentService.scheduleDocument(this.mapper.map(this.documentBindingModel, DocumentServiceModel.class));
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("document-id",documentServiceModel.getId());
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/views/details.xhtml?id="+documentServiceModel.getId());
    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public DocumentBindingModel getDocumentBindingModel() {
        return documentBindingModel;
    }

    public void setDocumentBindingModel(DocumentBindingModel documentBindingModel) {
        this.documentBindingModel = documentBindingModel;
    }
}
