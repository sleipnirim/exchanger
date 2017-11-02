package by.zhukovski.exchanger.rest;

import com.fasterxml.jackson.annotation.JsonSetter;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class JsonObject {

    private  double result = 0;

    public JsonObject () {}

    public JsonObject (double result) {
        this.result = result;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }


}
