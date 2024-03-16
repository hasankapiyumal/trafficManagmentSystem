package ejb.impl;

import ejb.remote.Cart;
import jakarta.ejb.Stateless;

@Stateless

public class CartBean implements Cart {
    @Override
    public String getDetails() {

        return "Hello Hasanka";
    }
}
