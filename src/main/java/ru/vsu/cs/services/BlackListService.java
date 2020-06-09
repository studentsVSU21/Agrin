package ru.vsu.cs.services;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.cs.CustomExceptions.DataNotValid;
import ru.vsu.cs.Entities.BlackList;
import ru.vsu.cs.reposirories.BlackListRepository;

import java.util.Collection;

@Service
public class BlackListService {

    private final Logger LOG = LoggerFactory.getLogger(BlackListService.class);

    private BlackListRepository blackListRepository;

    @Autowired
    public BlackListService(
            BlackListRepository blackListRepository
    ) {
        this.blackListRepository = blackListRepository;
    }

    public void blockUser(String email, String phone) {
        BlackList blackList = new BlackList();
        blackList.setEmail(email);
        blackList.setNumberPhone(phone);
        blackListRepository.save(blackList);
    }

    public void checkValidPhone(String phone) throws DataNotValid {
        LOG.debug("check valid phone");
        Collection<BlackList> blackLists = blackListRepository.findByNumberPhone(phone);
        if (blackLists.size() != 0)
            throw new DataNotValid();
    }

    public void checkValidEmail(String email) throws DataNotValid {
        LOG.debug("check valid email");
        Collection<BlackList> blackLists = blackListRepository.findByEmail(email);
        if (blackLists.size() != 0)
            throw new DataNotValid();
    }
}