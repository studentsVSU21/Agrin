package ru.vsu.cs.DTO.mappers;

import org.springframework.stereotype.Component;
import ru.vsu.cs.DTO.NewOrderDTO;
import ru.vsu.cs.Entities.Order;

@Component
public class NewOrderMapper extends AbstractMapper {
    public NewOrderMapper() {
        super(Order.class, NewOrderDTO.class);
    }
}
