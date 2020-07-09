package com.example.jobscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
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

public class AddAssignedActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtTitle, edtDeskripsi;
    private Spinner spinner;
    private String timeString;
    private String dateString;

    private DatabaseReference mdatabase;
    FirebaseUser firebaseUser;
    private Integer numRandom = new Random().nextInt();
    private String keyMyTask = Integer.toString(numRandom);
    private String uid,spinFinal;
    private String user1 = "W49dmS5J3WYOaoC1jn3DGK9wEHj1";
    private String user2 = "eHeHZVeweaexGKLDU9KHfeZ29nP2";
    private String inSpin[] = {"Sir_Ilham"};
    private String outSpin[] = {"Fathul"};

    private DatePicker pickerDate;
    private TimePicker pickerTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assigned);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mdatabase = database.getReference("AssignedTask");
        uid= firebaseUser.getUid();

        pickerDate = findViewById(R.id.datePicker);
        pickerTime = findViewById(R.id.timePicker);
        edtTitle = findViewById(R.id.edtTitle);
        edtDeskripsi = findViewById(R.id.edtDeskripsi);
        spinner = findViewById(R.id.spinner);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnCancel = findViewById(R.id.btnCancel);

        pickerTime.setIs24HourView(true);
        showSpin();
        btnAdd.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    private void showSpin() {
        // inisialiasi Array Adapter dengan memasukkan string array di atas
        if (uid.equals(user1)){
            //Show Sir_Ilham
            final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_dropdown_item, inSpin);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    spinFinal = adapter.getItem(i);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    Toast.makeText(AddAssignedActivity.this, "failed",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            //Show Fathul
            final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, outSpin);
//             mengeset Array Adapter tersebut ke Spinner
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    spinFinal = adapter.getItem(i);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    Toast.makeText(AddAssignedActivity.this, "failed",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAdd : {
//                String spinValue = spinner.getSelectedItem().toString();
                user1 = "W49dmS5J3WYOaoC1jn3DGK9wEHj1";
                user2 = "eHeHZVeweaexGKLDU9KHfeZ29nP2";
                String key;
                if (spinFinal.equals("Fathul")){
                    spinFinal = "Sir_Ilham";
                    key = user1;
                }else {
                    spinFinal = "Fathul";
                    key = user2;
                }
                String title = edtTitle.getText().toString().trim();
                String desc = edtDeskripsi.getText().toString().trim();
//                due = edtDue.getText().toString().trim();
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
                    Assigned assigned = new Assigned(title, desc, spinFinal ,dateString, timeString);
//                mdatabase.child(uid).child("MyTask" + numRandom).setValue(myTask);
//                mdatabase.child(uid).push().setValue(myTask);
                    mdatabase.child(key).child("Assigned" + numRandom).setValue(assigned);
                    Toast.makeText(AddAssignedActivity.this, "Succesfully",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            }
            case R.id.btnCancel : {
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
