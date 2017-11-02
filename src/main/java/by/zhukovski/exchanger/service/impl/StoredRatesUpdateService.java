package by.zhukovski.exchanger.service.impl;

import by.zhukovski.exchanger.dao.remote.RemoteRates;
import by.zhukovski.exchanger.dao.sql.SQLRepository;
import by.zhukovski.exchanger.entity.Currency;
import by.zhukovski.exchanger.service.UpdateService;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log
public class StoredRatesUpdateService implements UpdateService {

    private final SQLRepository repository;
    private final RemoteRates remoteRates;

    @Autowired
    public StoredRatesUpdateService (SQLRepository repository, RemoteRates remoteRates){
        this.repository = repository;
        this.remoteRates = remoteRates;
    }

    @Override
    @Scheduled(fixedDelay = 1*60*1000)
    public boolean update() {

        List<Currency> currencies = remoteRates.update();
        if (!currencies.isEmpty()){
            repository.save(currencies);
            log.info("Currencies saved, remote data OK");
            return true;
        }
        log.severe("remote service returned empty list! Currencies not updated!");
        return false;
    }
}
