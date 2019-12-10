package com.training.assessment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.training.assessment.bean.User;

@Repository
public interface UserRepo extends CrudRepository<User, Integer>{

}
