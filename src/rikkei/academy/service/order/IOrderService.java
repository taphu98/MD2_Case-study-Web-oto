package rikkei.academy.service.order;

import rikkei.academy.model.order.Order;
import rikkei.academy.service.IGenerateService;

public interface IOrderService extends IGenerateService<Order> {
    void deleteByName(String name);
    Order findByName(String name);
}
