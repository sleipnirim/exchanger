package by.zhukovski.exchanger.rest;

import by.zhukovski.exchanger.service.ExchangeService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
@RequestMapping("/api")
public class Controller {

    private final ExchangeService exchangeService;

    @Autowired
    public Controller (ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @RequestMapping(path = "/exchange", method = RequestMethod.GET)
    public ResponseEntity<JsonObject> exchange (@RequestParam(value = "currency") String currency,
                                            @RequestParam(value = "value") double value) {
        log.info("API called");
        double returnValue = exchangeService.exchange(currency.toUpperCase(), value);
        JsonObject json = new JsonObject(returnValue);
        log.info("Exchange succesfull");
        return new ResponseEntity<>(json, HttpStatus.OK);

    }

}
