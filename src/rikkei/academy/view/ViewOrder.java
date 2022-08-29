package rikkei.academy.view;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import rikkei.academy.controller.OrderController;
import rikkei.academy.model.order.Order;

public class ViewOrder {
    OrderController orderController = new OrderController();
    public void ViewOrderList(){
        System.out.println("***Menu Order***");
        System.out.println("1. Show order list");
        System.out.println("2. Create order");
        System.out.println("3. ");
    }
}
