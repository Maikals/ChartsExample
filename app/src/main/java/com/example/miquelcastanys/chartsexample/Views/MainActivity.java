package com.example.miquelcastanys.chartsexample.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.miquelcastanys.chartsexample.Adapters.ChartAdapter;
import com.example.miquelcastanys.chartsexample.Entities.BaseEntity;
import com.example.miquelcastanys.chartsexample.Entities.ChartDataEntity;
import com.example.miquelcastanys.chartsexample.Presenters.BaseViewPresenter;
import com.example.miquelcastanys.chartsexample.Presenters.Implementations.ChartDataPresenter;
import com.example.miquelcastanys.chartsexample.R;
import com.github.mikephil.charting.charts.BarChart;

public class MainActivity extends AppCompatActivity implements BaseViewPresenter {

    private ChartAdapter chartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BarChart chart = (BarChart) findViewById(R.id.chart);
        chartAdapter = new ChartAdapter(chart);
        ChartDataPresenter chartDataPresenter = new ChartDataPresenter(this);
        chartDataPresenter.loadData();
    }

    @Override
    public void onDataReceive(BaseEntity baseEntity) {
        if (baseEntity instanceof ChartDataEntity) {
            ChartDataEntity chartDataEntity = (ChartDataEntity) baseEntity;
            chartAdapter.setData(chartDataEntity.getData());
        }
    }

    @Override
    public void onErrorReceived(BaseEntity baseResponse) {
        //TODO something
    }
}
