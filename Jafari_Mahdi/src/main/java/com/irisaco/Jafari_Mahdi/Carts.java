package com.irisaco.Jafari_Mahdi;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private int id;
    private int customer_id;
    //---------- It should have been Decimal as PDF mentioned, but I checked the examples, string was more compatible
    private String date;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "carts_products", joinColumns = @JoinColumn(name = "cart_id"))
    private List<CartsProducts> products = new ArrayList<>();


    public Carts() {
    }
    public Carts(int customer_id, String date, List<CartsProducts> products) {
        this.products = products;
        this.customer_id = customer_id;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Carts{" +
                "id=" + id +
                ", customer_id=" + customer_id +
                ", date='" + date + '\'' +
                ", cartsProducts=" + products +
                '}';
    }

    //region ----------Getters

    public int getId() {
        return id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public String getDate() {
        return date;
    }

    public List<CartsProducts> getProducts() {
        return products;
    }


    //endregion

    //region ----------Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setProducts(List<CartsProducts> products) {
        this.products = products;
    }

//endregion

}//


