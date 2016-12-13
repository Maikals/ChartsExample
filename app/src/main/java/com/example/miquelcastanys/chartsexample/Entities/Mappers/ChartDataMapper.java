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
package com.example.miquelcastanys.chartsexample.Entities.Mappers;

import com.example.domain.entities.ChartDataDomainEntity;
import com.example.domain.entities.ChartDomainValues;
import com.example.miquelcastanys.chartsexample.Entities.ChartDataEntity;
import com.example.miquelcastanys.chartsexample.Entities.ChartValues;

public class ChartDataMapper {
    public static ChartDataEntity transform(ChartDataDomainEntity chartDataDomainEntity) {
        ChartDataEntity chartDataEntity = new ChartDataEntity();
        chartDataEntity.setErrorCode(chartDataDomainEntity.getErrorCode());
        chartDataEntity.setData(getChartValues(chartDataDomainEntity.getChartDomainValues()));
        return chartDataEntity;
    }

    private static ChartValues[] getChartValues(ChartDomainValues[] chartDomainValues) {
        int n = chartDomainValues.length;
        ChartValues[] chartValues = new ChartValues[n];
        for (int i = 0; i < chartDomainValues.length; ++i){
            ChartValues chartValue = new ChartValues();
            chartValue.setX(chartDomainValues[i].getX());
            chartValue.setY(chartDomainValues[i].getY());
            chartValues[i] = chartValue;
        }
        return chartValues;
    }
}
