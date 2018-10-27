package com.example.admin.mysmarttimetable;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.admin.mysmarttimetable.Utils.LetterImageView;

public class DayDetail extends AppCompatActivity {

    private ListView listView;
    private android.support.v7.widget.Toolbar toolbar;
    public static String[] Monday;
    public static String[] Tuesday;
    public static String[] Wednesday;
    public static String[] Thursday;
    public static String[] Friday;
    public static String[] Saturday;
    public static String[] Time1;
    public static String[] Time2;
    public static String[] Time3;
    public static String[] Time4;
    public static String[] Time5;
    public static String[] Time6;
    private String[] preferredDay;
    private String[] preferredTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_detail);
        setupUIViews();
        initToolbar();
        setupUIViews();
    }

    private void setupUIViews(){
        listView = (ListView)findViewById(R.id.lvDayDetail);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.ToolbarDayDetail);
    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("WeekActivity.sharedPreferences.getString(WeekActivity.SEL_DAY,null");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setListView(){
        Monday = getResources().getStringArray(R.array.Monday);
        Tuesday = getResources().getStringArray(R.array.Tuesday);
        Wednesday = getResources().getStringArray(R.array.Wednesday);
        Thursday = getResources().getStringArray(R.array.Thursday);
        Friday = getResources().getStringArray(R.array.Friday);
        Saturday = getResources().getStringArray(R.array.Saturday);


        Time1 = getResources().getStringArray(R.array.time1);
        Time2 = getResources().getStringArray(R.array.time2);
        Time3 = getResources().getStringArray(R.array.time3);
        Time4 = getResources().getStringArray(R.array.time4);
        Time5= getResources().getStringArray(R.array.time5);
        Time6 = getResources().getStringArray(R.array.time6);

        String selected_day = WeekActivity.sharedPreferences.getString(WeekActivity.SEL_DAY,null);

        if(selected_day.equalsIgnoreCase("Monday")){
            preferredDay = Monday;
            preferredTime = Time1;
        }else if(selected_day.equalsIgnoreCase("Tuesday")){
            preferredDay = Tuesday;
            preferredTime = Time2;
        }else if(selected_day.equalsIgnoreCase("Wednesday")){
            preferredDay = Wednesday;
            preferredTime = Time3;
        }else if(selected_day.equalsIgnoreCase("Thursday")){
            preferredDay = Thursday;
            preferredTime = Time4;
        }else if(selected_day.equalsIgnoreCase("Friday")){
            preferredDay = Friday;
            preferredTime = Time5;
        }else {
            preferredDay = Saturday;
            preferredTime = Time6;
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,preferredDay,preferredTime);
        listView.setAdapter(simpleAdapter);

    }
    public class SimpleAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView subject,time;
        private LetterImageView letterImageView;
        private  String[] subjectArray;
        private String[] timeArray;
        private LetterImageView imageView;

        public SimpleAdapter(Context context,String[] subjectArray, String[]timeArray){
            mContext = context;
           this.subjectArray = subjectArray;
            this.timeArray = timeArray;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return subjectArray.length;
        }

        @Override
        public Object getItem(int position) {
            return subjectArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = layoutInflater.inflate(R.layout.day_detail_single_item,null);
            }
            subject = (TextView)convertView.findViewById(R.id.tvSubjectDayDetails);
            time = (TextView)convertView.findViewById(R.id.tvTimeDayDetail);
            letterImageView = (LetterImageView)convertView.findViewById(R.id.ivDayDetails);

            subject.setText(subjectArray[position]);
            time.setText(timeArray[position ]);

            letterImageView.setOval(true);
            letterImageView.setLetter(subjectArray[position].charAt(0));

            return convertView;

        }
    }
}
