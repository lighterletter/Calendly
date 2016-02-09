package lighterletter.calendly;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;


import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.view.View;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


/*
* This is a test project. Here I've tested some material design, animation and databases.
* I plan to migrate this to a more complete project.
* */


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout ctl;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;

    BroadcastReceiver _broadcastReceiver;

    private SimpleDateFormat _sdfWatchTime;
    private Calendar calendar;
    //singleton instance of calendar functions
    private CustomCalendar calendly;
    private SimpleDateFormat _watch;



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendar = GregorianCalendar.getInstance();

        _sdfWatchTime = new SimpleDateFormat();
        calendly = CustomCalendar.getInstance(calendar, _sdfWatchTime.format(new Date()));

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ctl = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        _watch = new SimpleDateFormat();
        toolbar.setTitle("" + _watch.format(new Date()));

        if (calendly.getHour_of_day() >= 18 || calendly.getHour_of_day() <= 6)
            ctl.setBackground(getResources().getDrawable(R.drawable.md));

        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.scrollableview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        final List<String> listData = new ArrayList<String>();


        listData.add("1) Full date: " + calendly.getDate());
        listData.add("2) Day of week: " + calendly.getDayOfWeek(calendar));
        listData.add("3) Month: " + calendly.getMonth(calendar));
        listData.add("4) Day: " + calendly.getDayOfMonth(calendar));
        listData.add("5) Time: " + calendly.getTime());
        listData.add("6) Hours elapsed this year: " + calendly.getTimeElapsedYear());
        listData.add("7) Hours left to year: " + calendly.getHours_to_year());

        if (recyclerAdapter == null) {
            recyclerAdapter = new RecyclerAdapter(listData);
            recyclerView.setAdapter(recyclerAdapter);
        }
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NewActivity.class));
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        //makes call to system and updates on the minute. Must optimize for hourly call update.
        _broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context ctx, Intent intent) {
                if (intent.getAction().compareTo(Intent.ACTION_TIME_TICK) == 0) {
                    _sdfWatchTime = new SimpleDateFormat();
                    toolbar.setTitle("" + _sdfWatchTime.format(new Date()));
                }
            }
        };
        registerReceiver(_broadcastReceiver, new IntentFilter(Intent.ACTION_TIME_TICK));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (_broadcastReceiver != null)
            unregisterReceiver(_broadcastReceiver);
    }
}
