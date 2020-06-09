package ru.vsu.cs.DTO.mappers;

import org.modelmapper.Converter;
import org.springframework.stereotype.Component;
import ru.vsu.cs.DTO.InfoOrderDTO;
import ru.vsu.cs.DTO.ResponseExpenseReportDTO;
import ru.vsu.cs.Entities.ExpenseReport;
import ru.vsu.cs.Entities.Pesticide;

import javax.annotation.PostConstruct;

@Component
public class ResponseExpenseReportMapper extends AbstractMapper<ExpenseReport, ResponseExpenseReportDTO> {

    public ResponseExpenseReportMapper() {
        super(ExpenseReport.class, ResponseExpenseReportDTO.class);
    }

    @PostConstruct
    public void setupMapper() {
        Converter<Pesticide, String> converter = (ctg) -> {
            return ctg.getSource().getName();
        };
        mapper.typeMap(ExpenseReport.class, ResponseExpenseReportDTO.class).addMappings(mapper -> {
                    mapper.using(converter).map(ExpenseReport::getPesticide, ResponseExpenseReportDTO::setPesticideName);
                }
        );
    }

}
