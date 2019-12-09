package com.training.assessment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessRepo extends CrudRepository<AssessmentTable, Integer>{

}
