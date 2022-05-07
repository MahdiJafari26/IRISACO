package com.irisaco.Jafari_Mahdi;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends CrudRepository<Products, Integer> {
    Products findById(int id);
}

