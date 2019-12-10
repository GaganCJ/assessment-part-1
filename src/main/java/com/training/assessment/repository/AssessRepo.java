package com.training.assessment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.training.assessment.bean.AssessmentTable;

@Repository
public interface AssessRepo extends CrudRepository<AssessmentTable, Integer>{

}
