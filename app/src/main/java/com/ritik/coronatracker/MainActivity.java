package com.ritik.coronatracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.ritik.coronatracker.apii.ApiUtilities;
import com.ritik.coronatracker.apii.CountryData;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView totalConfirm,totalActive,totalRecovered,totalDeath,totalTests;
    private TextView todayConfirm,todayRecovered,todayDeath,date;
    private PieChart pieChart;
    private static List<CountryData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list=new ArrayList<>();
        init();

        ApiUtilities.getApiInterface().getCountryData()
                .enqueue(new Callback<List<CountryData>>() {
                    @Override
                    public void onResponse(Call<List<CountryData>> call, Response<List<CountryData>> response) {
                       list.addAll(response.body());

                       for (int i=0;i<list.size();i++){
                           if (list.get(i).country.equals("India")){
                               int confirm = Integer.parseInt(list.get(i).cases);
                               int active = Integer.parseInt(list.get(i).active);
                               int recovered=Integer.parseInt(list.get(i).recovered);
                               int death = Integer.parseInt(list.get(i).deaths);

                               totalConfirm.setText(NumberFormat.getInstance().format(confirm));
                               totalActive.setText(NumberFormat.getInstance().format(active));
                               totalRecovered.setText(NumberFormat.getInstance().format(recovered));
                               totalDeath.setText(NumberFormat.getInstance().format(death));

                               todayDeath.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).todayDeaths)));
                               todayConfirm.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).todayCases)));
                               todayRecovered.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).todayRecovered)));
                               totalTests.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).tests)));

                               setText(list.get(i).updated);

                               pieChart.addPieSlice(new PieModel("confirm",confirm,getResources().getColor(R.color.yellow)));
                               pieChart.addPieSlice(new PieModel("Active",active,getResources().getColor(R.color.blue_pie)));
                               pieChart.addPieSlice(new PieModel("confirm",recovered,getResources().getColor(R.color.green_pie)));
                               pieChart.addPieSlice(new PieModel("confirm",death,getResources().getColor(R.color.red_pie)));

                               pieChart.startAnimation();

                           }
                       }
                    }

                    @Override
                    public void onFailure(Call<List<CountryData>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private void setText(String updated) {
        DateFormat format = new SimpleDateFormat("MMM dd, yyyy");
        long millisecond = Long.parseLong(updated);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millisecond);
        date.setText("Updated  at"+format.format(calendar.getTime()));
    }

    private void init(){
        totalConfirm=findViewById(R.id.totalConfirm);
        totalActive=findViewById(R.id.totalActive);
        totalRecovered=findViewById(R.id.totalRecoverd);
        totalDeath=findViewById(R.id.totalDeath);
        totalTests=findViewById(R.id.totalTest);
        pieChart=findViewById(R.id.pieChart);
        todayConfirm=findViewById(R.id.todayConfirm);
        todayRecovered=findViewById(R.id.todayRecovered);
        todayDeath=findViewById(R.id.todayDeath);
        date=findViewById(R.id.date);
    }
}