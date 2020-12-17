package org.evrim.spring.exam.data.dao;

import org.evrim.spring.exam.data.ds.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductDao extends CrudRepository<Product,Integer> {
}
