package rikkei.academy.controller;

import rikkei.academy.dto.response.ResponseMessenger;
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

    public List<Cart> showCartList(){
        return cartServiceIMPL.findAll();
    }
    public void createCart(int idCar) {
        Cart cart = getMyCart();
        if(cart==null){
            cart = new Cart(cartServiceIMPL.getLastId(), currentUser.getId());
        }
        //trả về true khi có chứa idCar
        if(!new CarServiceIMPL().findAll().stream().map(Car::getId).collect(Collectors.toList()).contains(idCar)){
            System.out.println("Create err!");
            return;
        }
        cart.addToCart(idCar);
        cartServiceIMPL.save(cart);
    }
    public Cart getMyCart(){
        //dùng luồng tìm tất cả đối tượng user có id = id của người dùng hiện tại. Nếu không có sẽ là null
        return cartServiceIMPL.findAll().stream().filter(o->o.getIdUser()==currentUser.getId()).findAny().orElse(null);
    }
//    public ResponseMessenger changeStatus(int id){
//        if (cartServiceIMPL.findById(id)==null){
//            return new ResponseMessenger("not_found");
//        }
//        cartServiceIMPL.changeStatus(id);
//        boolean check = cartServiceIMPL.findById(id).isStatus();
//        System.out.println(check);
//        if (check){
//            return new ResponseMessenger("paid");
//        }else {
//            return new ResponseMessenger("not_paid");
//        }
//    }
    public void changeStatusCart(int id){
        cartServiceIMPL.changeStatus(id);
    }
    public void deleteCart(int id){
        cartServiceIMPL.deleteById(id);
    }
}

