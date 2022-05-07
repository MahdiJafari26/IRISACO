package com.irisaco.Jafari_Mahdi;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartsRepository extends CrudRepository<Carts, Integer> {
    Carts findById(int id);
   Carts findTopByOrderByIdDesc();
}
