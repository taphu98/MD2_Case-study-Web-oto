package rikkei.academy.controller;

import com.sun.org.apache.xpath.internal.operations.Or;
import rikkei.academy.model.order.Order;
import rikkei.academy.service.order.IOrderService;
import rikkei.academy.service.order.OrderServiceIMPL;

import java.util.List;

public class OrderController {
    IOrderService orderService = new OrderServiceIMPL();
    public List<Order> showOrderList(){
        return orderService.findAll();
    }
    public void createOrder(Order order){
        orderService.save(order);
    }
    public void deleteOrder(String name){
        orderService.deleteByName(name);
    }
}
