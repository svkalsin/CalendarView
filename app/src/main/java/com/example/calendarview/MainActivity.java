package com.example.calendarview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private Button mChooseStartDate;
    private Button mChooseEndDate;
    private CalendarView mStartDateCalendar;
    private CalendarView mEndtDateCalendar;
    private Button mBtnOK;
    private long mStartDate;
    private String mStartDateTxt;
    private long mEndDate;
    private String mEndDateTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    public void clickChooseStartDate(View view) {
        mStartDateCalendar.setVisibility(View.VISIBLE);
        mEndtDateCalendar.setVisibility(View.GONE);
    }

    public void clickChooseEndDate(View view) {
        mEndtDateCalendar.setVisibility(View.VISIBLE);
        mStartDateCalendar.setVisibility(View.GONE);
    }

    public void clickBtnOK(View view) {
        if (mStartDate > mEndDate) {
            Toast.makeText(MainActivity.this, "Ошибка", Toast.LENGTH_LONG).show();
            mChooseStartDate.setText(getString(R.string.StartDate));
            mChooseEndDate.setText(getString(R.string.EndDate));
        } else {
            Toast.makeText(MainActivity.this, "старт: " + mStartDateTxt + " окончаниe: " + mEndDateTxt, Toast.LENGTH_LONG).show();
        }
    }

    private void initViews() {
        mChooseStartDate = findViewById(R.id.chooseStartDate);
        mChooseEndDate = findViewById(R.id.chooseEndDate);
        mStartDateCalendar = findViewById(R.id.startDateCalendar);
        mEndtDateCalendar = findViewById(R.id.endtDateCalendar);
        mBtnOK = findViewById(R.id.btnOK);

        // Скроем календари при запуске приложения
        mStartDateCalendar.setVisibility(View.GONE);
        mEndtDateCalendar.setVisibility(View.GONE);

        mStartDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int mounth, int day) {
                mStartDateTxt = year + "-" + mounth + "-" + day;
                mChooseStartDate.setText(getString(R.string.StartDate) + " " + mStartDateTxt);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(year, mounth, day);
                mStartDate = gregorianCalendar.getTimeInMillis();
                calendarView.setVisibility(View.GONE);
            }
        });

        mEndtDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int mounth, int day) {
                mEndDateTxt = year + "-" + mounth + "-" + day;
                mChooseEndDate.setText(getString(R.string.EndDate) + " " + mEndDateTxt);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(year, mounth, day);
                mEndDate = gregorianCalendar.getTimeInMillis();
                calendarView.setVisibility(View.GONE);
            }
        });
    }
}