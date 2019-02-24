package examapp.repositories;

import examapp.domain.entities.Document;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class DocumentRepositoryImpl implements DocumentRepository {
    private final EntityManager entityManager;
    private final ModelMapper mapper;

    @Inject
    public DocumentRepositoryImpl(EntityManager entityManager,ModelMapper mapper) {
        this.entityManager = entityManager;
        this.mapper = mapper;
    }
    @Override
    public Document save(Document document) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(document);
        this.entityManager.getTransaction().commit();
        return document;
    }

    @Override
    public List<Document> findAll() {
        this.entityManager.getTransaction().begin();
        List<Document> documents =  this.entityManager.createQuery("select d from Document d",Document.class).getResultList();
        this.entityManager.getTransaction().commit();
        return documents;
    }

    @Override
    public Document findById(String id) {
        this.entityManager.getTransaction().begin();
        Document document =  this.entityManager.createQuery("select d from Document d where d.id= :id",Document.class)
                .setParameter("id",id).getSingleResult();
        this.entityManager.getTransaction().commit();
        return document;
    }

    @Override
    public void deleteDocument(String id) {
        this.entityManager.getTransaction().begin();
        this.entityManager.createQuery("delete from Document d where d.id= :id",Document.class)
        .setParameter("id",id).executeUpdate();
        this.entityManager.getTransaction().commit();
    }
}
