package Controller;

import Model.Order;
import Model.Table;
import Repository.OrderRepository;
import Service.OrderService;
import Service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Table table, Order order) {
        orderService.create(table, order);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateOrder(@PathVariable String id, @RequestBody Table table, Order order) {
        orderService.update(id, table, order);
    }

    @GetMapping("")
    public List<Order> getAll() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Optional<Order> getTableById(@PathVariable String id) {
        return orderService.getOrderById(id);
    }
}
