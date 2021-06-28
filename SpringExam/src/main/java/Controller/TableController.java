package Controller;

import Model.Order;
import Model.Table;
import Service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tables")

public class TableController {
    private final TableService tableService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Table table, Order order) {
        tableService.create(table, order);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateTeam(@PathVariable String id, @RequestBody Table table, Order order) {
        tableService.update(id, table, order);
    }

    @GetMapping("")
    public List<Table> getAll() {
        return tableService.getAllTables();
    }

    @GetMapping("/{id}")
    public Optional<Table> getTableById(@PathVariable String id) {
        return tableService.getTableById(id);
    }
}
