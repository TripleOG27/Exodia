package examapp.service;

import examapp.domain.entities.Document;
import examapp.domain.models.service.DocumentServiceModel;
import examapp.domain.models.view.DocumentsViewModel;

import java.util.List;

public interface DocumentService {
    //aka create a Document
    DocumentServiceModel scheduleDocument(DocumentServiceModel documentServiceModel);
    //view and delete a Document
    void printDocument(String id);
    List<DocumentsViewModel> findAll();
    DocumentServiceModel findById(String id);
}
