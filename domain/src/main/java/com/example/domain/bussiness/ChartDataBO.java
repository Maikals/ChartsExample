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
package com.example.domain.bussiness;

import android.net.Uri;

import com.example.domain.entities.ChartDataDomainEntity;
import com.example.domain.repositories.ChartDataRepository;

public class ChartDataBO {

    public ChartDataDomainEntity getChartData() {
        ChartDataRepository chartDataRepository = new ChartDataRepository();
        return chartDataRepository.getChartData(buildURL());
    }

    private String buildURL() {
        Uri.Builder builder = new Uri.Builder();
        return builder.scheme("http")
                .authority("dummy_url.com")
                .appendPath("dummy")
                .appendPath("url").build().toString();
    }
}
