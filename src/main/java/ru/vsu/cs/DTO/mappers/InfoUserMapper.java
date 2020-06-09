package ru.vsu.cs.DTO.mappers;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import ru.vsu.cs.DTO.InfoUserDTO;
import ru.vsu.cs.Entities.User;

import javax.annotation.PostConstruct;


@Component
public class InfoUserMapper extends AbstractMapper<User, InfoUserDTO> {

    public InfoUserMapper() {
        super(User.class, InfoUserDTO.class);
    }
}