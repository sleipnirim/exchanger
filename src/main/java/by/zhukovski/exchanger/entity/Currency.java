package by.zhukovski.exchanger.entity;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@ToString
@EqualsAndHashCode
@Table(name = "currencies", schema = "main")
public class Currency {

    @Id
    @Column(name = "name")
    private String currencyName;

    @Column(name = "rate")
    private double currencyRate;

    public Currency () {}

    public Currency(String currencyName, double currencyRate) {
        this.currencyName = currencyName;
        this.currencyRate = currencyRate;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public double getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(double currencyRate) {
        this.currencyRate = currencyRate;
    }
}
