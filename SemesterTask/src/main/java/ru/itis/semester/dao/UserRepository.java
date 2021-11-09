package ru.itis.semester.dao;

import ru.itis.semester.dao.base.CrudRepository;
import ru.itis.semester.models.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findByUsername(String username);
    void updateAvatarForUser(Long userId, Long avatarId);
    Optional<Long> findAdminById(Long userId);
}
