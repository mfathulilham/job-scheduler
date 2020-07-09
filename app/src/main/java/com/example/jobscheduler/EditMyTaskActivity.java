package com.example.jobscheduler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
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

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class EditMyTaskActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtTitle, edtDeskripsi, edtDue;
    private Button btnAdd, btnCancel, btnDelete;
    private DatabaseReference mdatabase;
    FirebaseUser firebaseUser;
    private String keyMyTask,timeString,dateString, date, time;
    DatePicker pickerDate;
    TimePicker pickerTime;

    String title, desc, due;
    String[] kata;

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
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        edtTitle.setText(getIntent().getStringExtra("titleMyTask"));
        edtDeskripsi.setText(getIntent().getStringExtra("DescMyTask"));
        keyMyTask = getIntent().getStringExtra("keyMyTask");
        date = getIntent().getStringExtra("DateMyTask");
        time = getIntent().getStringExtra("TimeMyTask");
//        kata = date.split(" ");
////        Log.i("Tanggal",date);
//        ConvertMonth();
//
//        Integer day = Integer.parseInt(kata[0]);
//        Integer month = Integer.parseInt(kata[1]);
//        Integer year = Integer.parseInt(kata[2]);
//        Log.i("Tanggal","Bulan ="+month);
//        pickerDate.updateDate(year, month, day);
//        setTime();
//        setDate();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //    String keyMyTask = Integer.toString(numRandom);
        String uid = firebaseUser.getUid();
        mdatabase = database.getReference("MyTask").child(uid).child("MyTask" + keyMyTask);

        pickerTime.setIs24HourView(true);
        btnAdd.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    private void ConvertMonth() {
//        for (int i=0; i<=2; i++){
//
//        }
        if (kata[1].equals("Jan")){
            kata[1] = "01";
        }
        else if (kata[1].equals("Feb")){
            kata[1] = "02";
        }
        else if (kata[1].equals("Mar")){
            kata[1] = "03";
        }
        else if (kata[1].equals("Apr")){
            kata[1] = "04";
        }
        else if (kata[1].equals("May")){
            kata[1] = "05";
        }
        else if (kata[1].equals("Jun")){
            kata[1] = "06";
        }
        else if (kata[1].equals("Jul")){
            kata[1] = "07";
        }
        else if (kata[1].equals("Aug")){
            kata[1] = "08";
        }
        else if (kata[1].equals("Sep")){
            kata[1] = "09";
        }
        else if (kata[1].equals("Oct")){
            kata[1] = "10";
        }
        else if (kata[1].equals("Nov")){
            kata[1] = "11";
        }
        else if (kata[1].equals("Dec")){
            kata[1] = "12";
        }
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
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnEdit: {
                title = edtTitle.getText().toString().trim();
                desc = edtDeskripsi.getText().toString().trim();

                if (title.isEmpty() || desc.isEmpty()){
                    if (title.isEmpty()){
                        edtTitle.setError("Can't be blank");
                    }
                    if (desc.isEmpty()){
                        edtDeskripsi.setError("Can't be blank");
                    }
                }
                else {
                    getDateTime();
                    MyTask myTask = new MyTask(title, desc, keyMyTask, dateString, timeString);
//                mdatabase.child(uid).child("MyTask" + numRandom).setValue(myTask);
                    mdatabase.setValue(myTask);
                    Toast.makeText(EditMyTaskActivity.this, "Succesfully",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            }
            case R.id.btnCancel: {
                finish();
                break;
            }
            case R.id.btnDelete : {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditMyTaskActivity.this);
                builder.setTitle("Delete");
                builder.setMessage("Are you sure to delete this task ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Booking");
//                        ref.child(key).removeValue();
                        mdatabase.removeValue();
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.create().show();
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
