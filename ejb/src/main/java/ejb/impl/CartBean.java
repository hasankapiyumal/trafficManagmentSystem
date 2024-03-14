package ejb.impl;

import ejb.remote.Cart;
import jakarta.ejb.Stateless;

@Stateless

public class CartBean implements Cart {
    @Override
    public void getDetails() {
        System.out.println("Hello Hasanka");
    }
}
