package org.dragon.yunpeng.sandbox.repositories;

import org.dragon.yunpeng.sandbox.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
