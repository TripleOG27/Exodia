package examapp.service;

import examapp.domain.entities.Document;
import examapp.domain.models.service.DocumentServiceModel;
import examapp.domain.models.view.DocumentsViewModel;
import examapp.repositories.DocumentRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class DocumentServiceImpl implements DocumentService {
    private final ModelMapper mapper;
    private final DocumentRepository documentRepository;
    @Inject
    public DocumentServiceImpl(ModelMapper mapper, DocumentRepository documentRepository) {
        this.mapper = mapper;
        this.documentRepository = documentRepository;
    }

    @Override
    public DocumentServiceModel scheduleDocument(DocumentServiceModel documentServiceModel) {
        Document document = this.mapper.map(documentServiceModel,Document.class);

        this.documentRepository.save(document);
        return this.mapper.map(document,DocumentServiceModel.class);
    }

    @Override
    public void printDocument(String id) {
        System.out.println();
        this.documentRepository.deleteDocument(id);
    }

    @Override
    public DocumentServiceModel findById(String id) {
        return this.mapper.map(this.documentRepository.findById(id),DocumentServiceModel.class);
    }

    @Override
    public List<DocumentsViewModel> findAll() {
        return this.documentRepository.findAll().stream().map(document -> this.mapper.map(document,DocumentsViewModel.class))
                .collect(Collectors.toList());

    }
}
