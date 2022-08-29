package rikkei.academy.service.cart;

import rikkei.academy.model.cart.Cart;
import rikkei.academy.service.IGenerateService;

public interface ICartService extends IGenerateService<Cart> {
    int getLastId();
    Cart findById(int id);
}
