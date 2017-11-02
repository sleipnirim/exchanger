package by.zhukovski.exchanger.service.impl;

import by.zhukovski.exchanger.dao.sql.SQLRepository;
import by.zhukovski.exchanger.entity.Currency;
import by.zhukovski.exchanger.service.ExchangeService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log
@Service
@Transactional
public class ExchangeFromStoredValueService implements ExchangeService {

    private final SQLRepository repository;

    @Autowired
    public ExchangeFromStoredValueService (SQLRepository repository){
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public double exchange(String currency, double value) {

        log.info("Called exchange service with values " + currency + " " + value);
        Currency storedCurrency = repository.findOne(currency);
        if (storedCurrency != null) return storedCurrency.getCurrencyRate() * value;
        else {
            log.warning("Repository returns null!");
            return 0;
        }
    }
}
