package by.zhukovski.exchanger.dao.remote.impl;

import by.zhukovski.exchanger.dao.remote.RemoteRates;
import by.zhukovski.exchanger.entity.Currency;
import lombok.extern.java.Log;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipInputStream;

@Service
@Log
public class CsvRemoteRates implements RemoteRates {

    private final String csvSource = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref.zip";

    @Override
    public List<Currency> update() {

        List<Currency> currencies = new ArrayList<>();

        byte[] bytes = new RestTemplate().getForObject(csvSource, byte[].class);
        ZipInputStream zip = new ZipInputStream(new ByteArrayInputStream(bytes));

        try {
            zip.getNextEntry();

            byte[] buffer = new byte[1024];
            StringBuilder stringBuilder = new StringBuilder();

            while (zip.read(buffer) > 0){
                stringBuilder.append(new String(buffer));
            }

            String csvData = stringBuilder.toString();

            String[] columes = csvData.split("\n");
            String[] nameEntries = columes[0].split(", ");
            String[] rateEntries = columes[1].split(", ");

            for (int i = 1; i < nameEntries.length - 1; i++){
                if (nameEntries[i] != "" & rateEntries[i] != ""){
                    currencies.add(new Currency(nameEntries[i], Double.parseDouble(rateEntries[i])));
                }
            }

            return currencies;

        } catch (IOException e) {
            log.warning("Cannot read csv entry from zip source");
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
}
