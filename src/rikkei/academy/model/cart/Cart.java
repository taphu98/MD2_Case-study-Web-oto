package rikkei.academy.model.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Cart implements Serializable {
    private int id;
    private int idUser;
    private Map<Integer, Integer> cartMap = new HashMap<>();

    private boolean status;

    public Cart() {
    }

    public Cart(int id, int idUser) {
        this.id = id;
        this.idUser = idUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Integer, Integer> getCartMap() {
        return cartMap;
    }

    public void setCartMap(Map<Integer, Integer> cartMap) {
        this.cartMap = cartMap;
    }

    public void addToCart(int idCart) {
        if (cartMap.containsKey(idCart)) {
            cartMap.put(idCart, cartMap.get(idCart) + 1);
        } else {
            cartMap.put(idCart, 1);
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", cartMap=" + cartMap +
                ", status=" + status +
                '}';
    }
}
