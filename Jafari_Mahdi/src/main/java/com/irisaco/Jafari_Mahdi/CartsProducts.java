package com.irisaco.Jafari_Mahdi;


import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Embeddable
public class CartsProducts {
     @Id
     private int id = 0;
    private int productId;
    private int quantity;


    public CartsProducts() {
    }

    public CartsProducts(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "products{" +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }

    //region ----------Getters

    /**
     * public int getId() {
     * return id;
     * }
     **/
    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

//endregion

    //region ----------Getters

    /**
     * public void setId(int id) {
     * this.id = id;
     * }
     **/
    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

//endregion
}
