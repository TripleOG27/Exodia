package examapp.repositories;

import java.util.List;

public interface GenericRepository<Entity,Id> {
    Entity save(Entity entity);
    List<Entity> findAll();
    Entity findById(String id);
}
