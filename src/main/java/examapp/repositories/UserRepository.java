package examapp.repositories;

import examapp.domain.entities.User;

public interface UserRepository extends GenericRepository<User,String> {
    User findByUsername(String username);
}
