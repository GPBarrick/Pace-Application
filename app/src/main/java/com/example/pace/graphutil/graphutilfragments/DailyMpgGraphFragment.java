package com.example.pace.graphutil.graphutilfragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.pace.R;
import com.example.pace.config.ListHolder;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DailyMpgGraphFragment extends Fragment {
    public DailyMpgGraphFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_mpg_graph, container, false);

        initViews(view);

        initGraph();

        setPointListener();

        return view;
    }

    public LineChart lineChart;
    public TextView mpgValue;
    private void initViews(View view) {
        this.lineChart = view.findViewById(R.id.daily_mpg_lineGraph);

        this.mpgValue = view.findViewById(R.id.daily_mpg_graph_value);
        this.mpgValue.setText(""+ListHolder.getInstance().outputDailyDataList.get(
                ListHolder.getInstance().outputDailyDataListIndex
        ).getAverageMpg()+" m/g");
    }

    private void initGraph() {
        Description description = new Description();
        description.setText("");
        description.setPosition(15.0f, 15.0f);

        this.lineChart.setDescription(description);
        this.lineChart.getAxisRight().setDrawLabels(false);
        this.lineChart.getLegend().setEnabled(false);

        addDataXValues();

        XAxis xAxis = this.lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xValues));
        xAxis.setAxisLineWidth(3.0f);
        xAxis.setAxisLineColor(Color.BLACK);
        xAxis.setLabelCount(15);
        xAxis.setGranularity(1.0f);

        YAxis yAxis = this.lineChart.getAxisLeft();
        yAxis.setAxisMinimum(0.0f);
        yAxis.setAxisMaximum(getMaximum() + 15.0f);
        yAxis.setAxisLineWidth(3.0f);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setLabelCount(15);

        addEntries();

        addLineData();

        //LineData lineData = new LineData();

        this.lineChart.setData(new LineData(this.dataSets));

        this.lineChart.invalidate();
    }

    public List<String> xValues;
    private void addDataXValues() {
        xValues = new ArrayList<>();
        for (int i = 0; i < ListHolder.getInstance().outputDailyDataList.size(); ++i) {
            xValues.addAll(
                    Arrays.asList(ListHolder.getInstance().outputDailyDataList.get(i).getMonth()
                            +"/"+ListHolder.getInstance().outputDailyDataList.get(i).getDay()
            ));
        }
    }

    public List<Entry> mpgEntries;
    public ArrayList<Integer> colorList;
    public ArrayList<Integer> holeColors;
    public ArrayList<Float> sizeList;
    private void addEntries() {
        this.mpgEntries = new ArrayList<>();
        this.colorList = new ArrayList<>();
        this.holeColors = new ArrayList<>();
        this.sizeList = new ArrayList<>();
        for (int i = 0; i < ListHolder.getInstance().outputDailyDataList.size(); ++i) {
            if (i == ListHolder.getInstance().outputDailyDataListIndex) {
                Entry entry = new Entry(
                        i, ListHolder.getInstance().outputDailyDataList.get(i).getAverageMpg()
                );
                this.mpgEntries.add(entry);
                this.colorList.add(Color.GREEN);
                this.holeColors.add(Color.BLACK);
                this.sizeList.add(8.0f);
            } else {
                Entry entry = new Entry(
                        i, ListHolder.getInstance().outputDailyDataList.get(i).getAverageMpg()
                );
                this.mpgEntries.add(entry);
                this.colorList.add(Color.BLACK);
                this.holeColors.add(Color.BLACK);
                this.sizeList.add(4.0f);
            }
        }
    }

    //LineDataSet lineDataSet;
    ArrayList<ILineDataSet> dataSets;
    public void addLineData() {
        //this.lineDataSet = new LineDataSet(this.mpgEntries, "Average MPG");
        //this.lineDataSet.setColor(Color.BLUE);

        this.dataSets = new ArrayList<>();
        for(int i = 0; i < this.mpgEntries.size(); ++i) {
            LineDataSet dsi = new LineDataSet(Collections.singletonList(this.mpgEntries.get(i)), null);
            dsi.setDrawValues(false);
            dsi.setCircleColor(this.colorList.get(i));
            dsi.setCircleHoleColor(this.holeColors.get(i));
            dsi.setCircleRadius(this.sizeList.get(i));
            dsi.setCircleHoleRadius(this.sizeList.get(i));
            dataSets.add(dsi);
        }

        LineDataSet dataSet = new LineDataSet(this.mpgEntries, null);
        dataSet.setColor(Color.BLUE);
        dataSet.setCircleHoleColor(Color.TRANSPARENT);
        dataSet.setCircleColor(Color.TRANSPARENT);
        dataSet.setDrawValues(false);
        dataSets.add(dataSet);
    }

    private float getMaximum() {
        float retVal = 0.0f;
        for (int i = 0; i < ListHolder.getInstance().outputDailyDataList.size(); ++i) {
            if (retVal < ListHolder.getInstance().outputDailyDataList.get(i).getAverageMpg()) {
                retVal = ListHolder.getInstance().outputDailyDataList.get(i).getAverageMpg();
            }
        }
        return retVal;
    }

    private void setPointListener() {
        this.lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener()
        {
            @Override
            public void onValueSelected(Entry e, Highlight h)
            {
                ListHolder.getInstance().outputDailyDataListIndex = (int)e.getX();
                FragmentTransaction fragmentTransaction = ListHolder.getInstance().fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.activity_main_mainFrame, new DailyListItemFragment());
                fragmentTransaction.commit();
            }

            @Override
            public void onNothingSelected()
            {

            }
        });
    }
}