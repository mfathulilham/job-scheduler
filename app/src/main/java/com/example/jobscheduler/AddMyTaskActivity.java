package com.example.jobscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;


public class AddMyTaskActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtTitle, edtDeskripsi;
    Button btnAdd, btnCancel;
    private String timeString;
    private String dateString;

    private DatabaseReference mdatabase;
    FirebaseUser firebaseUser;
    private Integer numRandom = new Random().nextInt();
    private String keyMyTask = Integer.toString(numRandom);
    private String uid;

    private DatePicker pickerDate;
    private TimePicker pickerTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_my_task);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mdatabase = database.getReference("MyTask");
        uid= firebaseUser.getUid();

        pickerDate = findViewById(R.id.datePicker);
        pickerTime = findViewById(R.id.timePicker);
        edtTitle = findViewById(R.id.edtTitle);
        edtDeskripsi = findViewById(R.id.edtDeskripsi);
//        edtDue = findViewById(R.id.edtDue);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);

        btnAdd.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd: {
                String title = edtTitle.getText().toString().trim();
                String desc = edtDeskripsi.getText().toString().trim();
//                due = edtDue.getText().toString().trim();
                getDateTime();
                MyTask myTask = new MyTask(title, desc, keyMyTask, dateString, timeString);
//                mdatabase.child(uid).child("MyTask" + numRandom).setValue(myTask);
//                mdatabase.child(uid).push().setValue(myTask);
                mdatabase.child(uid).child("MyTask" + numRandom).setValue(myTask);
                Toast.makeText(AddMyTaskActivity.this, "Succesfully",
                        Toast.LENGTH_SHORT).show();
                finish();
                break;
            }
            case R.id.btnCancel: {
                finish();
                break;
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
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.getDefault());
        timeString = formatter.format(new Date(calender.getTimeInMillis()));
//        SimpleDateFormat dateformatter = new SimpleDateFormat(getString(R.string.dateformate));
        SimpleDateFormat dateformatter = new SimpleDateFormat("d MMM yyyy",Locale.getDefault());
        dateString = dateformatter.format(new Date(calender.getTimeInMillis()));
    }
}