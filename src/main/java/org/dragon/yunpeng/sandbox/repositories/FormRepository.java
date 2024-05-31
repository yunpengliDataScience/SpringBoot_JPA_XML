package org.dragon.yunpeng.sandbox.repositories;

import org.dragon.yunpeng.sandbox.entities.Form;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormRepository extends CrudRepository<Form, Long> {

}
