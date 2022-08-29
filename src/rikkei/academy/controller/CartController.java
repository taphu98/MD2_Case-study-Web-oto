package rikkei.academy.controller;

import rikkei.academy.model.car.Car;
import rikkei.academy.model.cart.Cart;
import rikkei.academy.model.user.User;
import rikkei.academy.service.car.CarServiceIMPL;
import rikkei.academy.service.cart.CartServiceIMPL;
import rikkei.academy.service.cart.ICartService;
import rikkei.academy.service.user.IUserService;
import rikkei.academy.service.user.UserServiceIMPL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CartController {
    ICartService cartServiceIMPL = new CartServiceIMPL();
    IUserService userService = new UserServiceIMPL();
    User currentUser = userService.getCurrentUser();

    public void createCart(int idCar) {
        Cart cart = getMyCart();
        if(cart==null){
            cart = new Cart(cartServiceIMPL.getLastId(), currentUser.getId());
        }
        if(!new CarServiceIMPL().findAll().stream().map(Car::getId).collect(Collectors.toList()).contains(idCar)){
            System.out.println("Create err!");
            return;
        }
        cart.addToCart(idCar);
        cartServiceIMPL.save(cart);
    }
    public Cart getMyCart(){
        return cartServiceIMPL.findAll().stream().filter(o->o.getIdUser()==currentUser.getId()).findAny().orElse(null);
    }
}
