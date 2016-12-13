/*            _MMMMM`
 *     __MMMMMMMMM`       J        openTrends Solucions i Sistemes, S.L.
 * JMMMMMMMMMMMMF       JM         http://www.opentrends.net
 * MMMMMMMMMMF       _JMM`         info@opentrends.net
 * MMMMMMMF`    .JMMMMF`
 * """")    _JMMMMMMF`             Llacuna, 166 Planta 10
 * _MMMMMMMMMMMMMMM`      .M)      Barcelona, 08018
 * MMMMMMMMMMMMMF`     .JMM`       Spain
 * MMMMMMMMMM"     _MMMMMF
 * M4MMM""`   ._MMMMMMMM`          *************************************
 * _______MMMMMMMMMMMF             ChartsExample
 * MMMMMMMMMMMMMMMM"               *************************************
 * MMMMMMMMMMMMF"                  Copyright (C) 2016 openTrends, Tots els drets reservats
 * MMMMMMMM""                      Copyright (C) 2016 openTrends, All Rights Reserved
 *
 *                                 This program is free software; you can redistribute it and/or modify
 *                                 it under the terms of the GNU General Public License as published by
 *                                 the Free Software Foundation; either version 2 of the License, or
 *                                 (at your option) any later version.
 *                             
 *                                 This program is distributed in the hope that it will be useful,
 *                                 but WITHOUT ANY WARRANTY; without even the implied warranty of
 *                                 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *                                 GNU General Public License for more details.
 *                             
 *                                 You should have received a copy of the GNU General Public License along
 *                                 with this program; if not, write to the Free Software Foundation, Inc.,
 *                                 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA. 
 */
package com.example.domain.ws;

import com.example.domain.entities.ChartDataDomainEntity;
import com.example.domain.entities.ChartDomainValues;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RestCall {

    private static final long TIMEOUT_CONNECT_VALUE = 3000;
    private static final long TIMEOUT_READ_VALUE = 3000;
    private static final String REDIRECT_HEADER_URL_PARAM_LOCATION = "Location";
    private static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public String getJSONStringFromURLPost(String url, String json) {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        System.out.println("Url: " + url);
        System.out.println("Body: " + json);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_CONNECT_VALUE, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_READ_VALUE, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .followRedirects(false).build();
        Response response;
        try {
            response = client.newCall(request).execute();
            if (response.code() == HttpURLConnection.HTTP_MOVED_TEMP) {
                String urlToRedirect = response.header(REDIRECT_HEADER_URL_PARAM_LOCATION);
                System.out.println("Retrying");
                return getJSONStringFromURLPost(urlToRedirect, json);
            }
            if (response.code() == HttpURLConnection.HTTP_OK) {
                String result = response.body().string();
                System.out.println(" Response: " + result);
                return result;
            } else {
                System.out.println("HTTP error: " + response.code() + " " + response.body().toString());
                response.body().close();
            }
        } catch (IOException e) {
            System.out.println("error_okhttp" + e.getMessage());
        }
        System.out.println("nullObject returned");
        return null;
    }

    public String getJSONStringFromURLGet(String url) {
        /*Request request = new Request.Builder()
                .url(url)
                .build();

        System.out.println("Url: " + url);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_CONNECT_VALUE, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_READ_VALUE, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .followRedirects(false).build();
        Response response;
        try {
            response = client.newCall(request).execute();
            if (response.code() == HttpURLConnection.HTTP_MOVED_TEMP) {
                String urlToRedirect = response.header(REDIRECT_HEADER_URL_PARAM_LOCATION);
                System.out.println("Retrying");
                return getJSONStringFromURLGet(urlToRedirect);
            }
            if (response.code() == HttpURLConnection.HTTP_OK) {
                String result = response.body().string();
                System.out.println("Response: " + result);
                return result;
            }
            else {
                System.out.println("HTTP error: "+ response.code()+ " " + response.body().toString());
                response.body().close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;*/
        //TODO Valores de gráfica falseados
        ChartDataDomainEntity chartDataDomainEntity = new ChartDataDomainEntity();
        chartDataDomainEntity.setErrorCode(0);
        ChartDomainValues[] chartDomainValues = new ChartDomainValues[20];
        for (int i = 0; i < chartDomainValues.length; ++i) {
            ChartDomainValues chartValue = new ChartDomainValues();
            chartValue.setX(i);
            chartValue.setY(new Random().nextInt(i+1));
            chartDomainValues[i] = chartValue;
        }
        chartDataDomainEntity.setChartDomainValues(chartDomainValues);
        Gson gson = new Gson();
        return gson.toJson(chartDataDomainEntity);
    }
}
