package ru.vsu.cs.DTO.mappers;

import org.modelmapper.Converter;
import org.springframework.stereotype.Component;
import ru.vsu.cs.DTO.InfoOrderDTO;
import ru.vsu.cs.DTO.NewOrderDTO;
import ru.vsu.cs.Entities.Order;
import ru.vsu.cs.Entities.Progress;
import java.time.LocalDate;


import javax.annotation.PostConstruct;

@Component
public class NewOrderMapper extends AbstractMapper {

    public NewOrderMapper() {
        super(Order.class, NewOrderDTO.class);
    }

    @PostConstruct
    public void setupMapper() {

        Converter<Progress, LocalDate> converterDATE = (ctg) -> {
            return ctg.getSource().getDateStart();
        };

        mapper.typeMap(Order.class, NewOrderDTO.class).addMappings(mapper -> {
                mapper.using(converterDATE).map(Order::getProgress, NewOrderDTO::setDate);
            }
        );
    }
}