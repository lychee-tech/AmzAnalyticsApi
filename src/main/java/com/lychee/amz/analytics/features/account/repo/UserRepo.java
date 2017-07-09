package com.lychee.amz.analytics.features.account.repo;


import com.lychee.amz.analytics.features.account.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Integer> {
    UserEntity findByEmail(String email);
    UserEntity findById(Integer id);
}
