package rikkei.academy.service.cart;

import rikkei.academy.config.Config;
import rikkei.academy.model.cart.Cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartServiceIMPL implements ICartService {
    static String PATH_CART = "C:\\Users\\Asus\\Module2\\case-study\\Website-ban-o-to\\src\\rikkei\\academy\\database\\cart.txt";
    static Config<List<Cart>> config = new Config<>();
    static List<Cart> cartList = config.read(PATH_CART);

    static {
        if (cartList == null) {
            cartList = new ArrayList<>();
        }
    }

    public List<Cart> findAll() {
        return cartList;
    }

    @Override
    public void save(Cart cart) {
        Cart oldCart = findById(cart.getId());
        if(oldCart == null){
            cartList.add(cart);
        } else {
            oldCart.setCartMap(cart.getCartMap());
        }
        updateData();
    }

    public void updateData() {
        config.write(PATH_CART, cartList);
    }


    @Override
    public int getLastId() {
        return cartList.isEmpty() ? 1 : cartList.get(cartList.size() - 1).getId() + 1;
    }

    @Override
    public Cart findById(int id) {
        for (Cart cart : cartList){
            if (cart.getId()==id)
                return cart;
        }
        return null;
    }

//    @Override
//    public int changeStatus(int id) {
//        Cart cart =
//    }

}
