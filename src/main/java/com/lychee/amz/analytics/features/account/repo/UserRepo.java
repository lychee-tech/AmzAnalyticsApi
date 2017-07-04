package com.lychee.amz.analytics.features.account.repo;


import com.lychee.amz.analytics.features.account.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Integer> {
    UserEntity findByEmail(String email);
    UserEntity findByLogin(String login);
    UserEntity findById(Integer id);
    boolean existsByEmail(String email);
}
