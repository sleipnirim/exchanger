package by.zhukovski.exchanger.dao.remote;

import by.zhukovski.exchanger.entity.Currency;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RemoteRates {

    List<Currency> update();
}
