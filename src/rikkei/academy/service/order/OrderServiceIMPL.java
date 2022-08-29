package rikkei.academy.service.order;

import rikkei.academy.config.Config;
import rikkei.academy.model.order.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceIMPL implements IOrderService{
    static String PATH_ORDER = "C:\\Users\\Asus\\Module2\\case-study\\Website-ban-o-to\\src\\rikkei\\academy\\database\\order.txt";
    static Config<List<Order>> config = new Config<>();
    static List<Order> orderList = config.read(PATH_ORDER);
    static {
        if (orderList==null){
            orderList = new ArrayList<>();
        }
    }
    @Override
    public List<Order> findAll() {
        updateData();
        return orderList;
    }

    @Override
    public void save(Order order) {
        orderList.add(order);
        updateData();
    }

    @Override
    public void updateData() {
        config.write(PATH_ORDER,orderList);
    }

    @Override
    public void deleteByName(String name) {


    }

    @Override
    public Order findByName(String name) {

        return null;
    }
}
