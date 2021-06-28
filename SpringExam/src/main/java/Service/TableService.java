package Service;

import Model.Order;
import Model.Table;
import Repository.OrderRepository;
import Repository.TableRepository;
import org.springframework.util.StringUtils;
import Exception.ValidationException;

import java.util.List;
import java.util.Optional;

public class TableService {
    private  TableRepository tableRepository;
    private  OrderRepository orderRepository;


    public void create(Table table, Order order) {
        validate(table, order);
        tableRepository.save(table);
    }


    public void update(String id, Table table, Order order) {
        Table oldTable = tableRepository.findById(id).orElse(null);
        validate(table, order);
        tableRepository.save(table.toBuilder()
                .id(oldTable.getId()).allPlaces(oldTable
                        .getAllPlaces()).usedPlaces(oldTable.getUsedPlaces())
                .outside(oldTable
                        .getOutside())
                .build());
    }

    public List<Table> getAllTables() {
        return tableRepository.findAll();
    }

    public Optional<Table> getTableById(String id) {
        return tableRepository.findById(id);
    }

    public void validate(Table Table, Order team) {
        if (!StringUtils.hasText(Table.getId()) || Table.getId() == null) {
            throw new ValidationException("Adj már meg egy nevet lécci!");
        }
        if (Table.getAllPlaces() == null) {
            throw new ValidationException(" Vmelyik helynek csak kéne léteznie, nem ?");
        }

    }
}
