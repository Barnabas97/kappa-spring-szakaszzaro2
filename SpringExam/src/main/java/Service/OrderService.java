package Service;

import Model.Order;
import Model.Table;
import Repository.OrderRepository;
import Repository.TableRepository;
import org.springframework.util.StringUtils;
import Exception.ValidationException;

import java.util.List;
import java.util.Optional;

public class OrderService {
    private  TableRepository tableRepository;
    private  OrderRepository orderRepository;


    public void create(Table table, Order order) {
        validate(table, order);
        tableRepository.save(table);
    }


    public void update(String id, Table table, Order order) {
        Order oldTable = orderRepository.findById(id).orElse(null);
        validate(table, order);
        orderRepository.save(order.toBuilder()
                .id(oldTable.getId()).name(oldTable
                        .getName()).unitPrice(oldTable.getUnitPrice())
                .table(oldTable
                        .getTable())
                .build());
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(String id) {
        return orderRepository.findById(id);
    }

    public void validate(Table table, Order order) {
        if (!StringUtils.hasText(order.getId()) || order.getId() == null) {
            throw new ValidationException("Adj már meg egy id-t lécci!");
        }
        if (order.getName() == null) {
            throw new ValidationException("Kéne név nem ?");
        }

    }

}
