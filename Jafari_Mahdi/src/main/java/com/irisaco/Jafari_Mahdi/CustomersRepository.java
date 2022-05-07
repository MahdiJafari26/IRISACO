package com.irisaco.Jafari_Mahdi;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends CrudRepository<Customers, Integer> {
    Customers findById(int id);
}