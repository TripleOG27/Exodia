package examapp.repositories;

import examapp.domain.entities.Document;

public interface DocumentRepository extends GenericRepository<Document,String> {
    void deleteDocument(String id);
}
