package com.example.jobscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class EditMyTaskActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtTitle, edtDeskripsi, edtDue;
    Button btnAdd, btnCancel;
    private DatabaseReference mdatabase;
    FirebaseUser firebaseUser;
    private String keyMyTask,timeString,dateString, date, time;
    DatePicker pickerDate;
    TimePicker pickerTime;

    String title, desc, due;

    public EditMyTaskActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_task);
        edtTitle = findViewById(R.id.edtTitle);
        edtDeskripsi = findViewById(R.id.edtDeskripsi);
        pickerDate = findViewById(R.id.datePicker);
        pickerTime = findViewById(R.id.timePicker);
        btnAdd = findViewById(R.id.btnEdit);
        btnCancel = findViewById(R.id.btnCancel);

        edtTitle.setText(getIntent().getStringExtra("titleMyTask"));
        edtDeskripsi.setText(getIntent().getStringExtra("DescMyTask"));
        keyMyTask = getIntent().getStringExtra("keyMyTask");
        date = getIntent().getStringExtra("DateMyTask");
        time = getIntent().getStringExtra("TimeMyTask");

//        setTime();
//        setDate();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //    String keyMyTask = Integer.toString(numRandom);
        String uid = firebaseUser.getUid();
        mdatabase = database.getReference("MyTask").child(uid).child("MyTask" + keyMyTask);

        btnAdd.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

//    private void setTime() {
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//        Time time1 = null;
//        try {
//            time1 = (Time) sdf.parse(time);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Calendar c = Calendar.getInstance();
//        c.setTime(time1);
//
//        pickerTime = new TimePicker(getApplicationContext());
//        pickerTime.setHour(c.get(Calendar.HOUR_OF_DAY));
//        pickerTime.setMinute(c.get(Calendar.MINUTE));
//    }
//
//    private void setDate() {
//        SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy");
//        Date date1 = null;
//        try {
//            date1 = (Date) sdf.parse(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Calendar c = Calendar.getInstance();
//        c.setTime(date1);

//        pickerDate = new DatePicker(getApplicationContext());
//        pickerDate.updset(c.get(Calendar.MONTH));
//        pickerDate.set(Calendar.DAY_OF_MONTH, pickerDate.getDayOfMonth());
//        pickerDate.set(Calendar.YEAR, pickerDate.getYear());
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnEdit: {
                title = edtTitle.getText().toString().trim();
                desc = edtDeskripsi.getText().toString().trim();
                getDateTime();
                MyTask myTask = new MyTask(title, desc, keyMyTask, dateString, timeString);
//                mdatabase.child(uid).child("MyTask" + numRandom).setValue(myTask);
                mdatabase.setValue(myTask);
                finish();
            }
            case R.id.btnCancel: {
                finish();
            }
        }
    }

    private void getDateTime() {
        Calendar calender = Calendar.getInstance();
        calender.clear();
        calender.set(Calendar.MONTH, pickerDate.getMonth());
        calender.set(Calendar.DAY_OF_MONTH, pickerDate.getDayOfMonth());
        calender.set(Calendar.YEAR, pickerDate.getYear());
        calender.set(Calendar.HOUR, pickerTime.getHour());
        calender.set(Calendar.MINUTE, pickerTime.getHour());
        calender.set(Calendar.SECOND, 00);
//        SimpleDateFormat formatter = new SimpleDateFormat(getString(R.string.hour_minutes));
        SimpleDateFormat formatter = new SimpleDateFormat("d MMM yyyy", Locale.getDefault());
        timeString = formatter.format(new Date(calender.getTimeInMillis()));
//        SimpleDateFormat dateformatter = new SimpleDateFormat(getString(R.string.dateformate));
        SimpleDateFormat dateformatter = new SimpleDateFormat("HH:mm",Locale.getDefault());
        dateString = dateformatter.format(new Date(calender.getTimeInMillis()));
    }
}
