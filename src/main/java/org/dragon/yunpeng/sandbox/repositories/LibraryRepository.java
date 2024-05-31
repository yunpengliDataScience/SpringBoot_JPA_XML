package org.dragon.yunpeng.sandbox.repositories;

import org.dragon.yunpeng.sandbox.entities.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long>{

}
