package ru.vsu.cs.DTO.mappers;

import org.modelmapper.Converter;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vsu.cs.Entities.Order;
import ru.vsu.cs.Entities.Progress;
import ru.vsu.cs.DTO.InfoOrderDTO;

import javax.annotation.PostConstruct;

@Component
public class InfoOrderMapper extends AbstractMapper<Order, InfoOrderDTO> {

    private final Logger LOG = LoggerFactory.getLogger(InfoOrderDTO.class);

    public InfoOrderMapper() {
        super(Order.class, InfoOrderDTO.class);
    }

    @PostConstruct
    public void setupMapper() {
        Converter<Progress, String> converter = (ctg) -> {
            return ctg.getSource().getStatus().getNameStatus();
        };
        mapper.typeMap(Order.class, InfoOrderDTO.class).addMappings( mapper -> {
                    mapper.using(converter).map(Order::getProgress, InfoOrderDTO::setStatus);
                }
        );
    }
}
