package com.handacc.amz.analytics.features.account.repo;


import com.handacc.amz.analytics.features.account.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserEntity, Integer> {

}
