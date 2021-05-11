package sg.edu.rp.c346.id20022404.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatePicker dp;
    TimePicker tp;
    Button btnConfirm, btnReset;
    TextView tvName, tvPhone,tvResult;
    EditText name, phone, gSize;
    RadioGroup rGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);
        tvName = findViewById(R.id.displayName);
        tvPhone = findViewById(R.id.displayPhone);
        tvResult = findViewById(R.id.displayResult);
        btnConfirm = findViewById(R.id.buttonConfirm);
        btnReset = findViewById(R.id.buttonReset);
        name = findViewById(R.id.editTextName);
        phone = findViewById(R.id.editTextPhone);
        gSize = findViewById(R.id.editTextGroup);
        rGroup = findViewById(R.id.smokingOpt);
        String blank = "";

        tp.setCurrentHour(19);
        tp.setCurrentMinute(30);
        dp.updateDate(2020, 5,1);
        rGroup.check(R.id.nSmoking);

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if(hourOfDay < 8) {
                    tp.setCurrentHour(8);
                }
                else if(hourOfDay > 20) {
                    tp.setCurrentHour(20);
                    tp.setCurrentMinute(59);
                }
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(name.getText().toString()) || !TextUtils.isEmpty(phone.getText().toString()) || !TextUtils.isEmpty(gSize.getText().toString())) {
                    String areaOpt = "";
                    if (rGroup.getCheckedRadioButtonId() == R.id.smoking) {
                        areaOpt = "Smoking Area";
                    } else {
                        areaOpt = "Non-smoking Area";
                    }

                    String timeis = tp.getCurrentHour() + ":" + String.format("%02d", tp.getCurrentMinute());
                    String dateis = dp.getDayOfMonth() + "/" + (dp.getMonth()+1) + "/" + dp.getYear();
                    tvName.setText("Reservee name: " + name.getText().toString());
                    tvPhone.setText("Mobile number: " + phone.getText().toString());
                    tvResult.setText("Reservation for a group of " + gSize.getText().toString() + " on " + dateis + " at " + timeis
                            + " in a " + areaOpt);

                } else {
                    Toast.makeText(MainActivity.this, "Error: Empty field is not allowed!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tp.setCurrentHour(19);
                tp.setCurrentMinute(30);
                dp.updateDate(2020, 5,1);
                rGroup.check(R.id.nSmoking);
                name.setText(blank);
                phone.setText(blank);
                gSize.setText(blank);
                tvName.setText(blank);
                tvPhone.setText(blank);
                tvResult.setText(blank);

            }
        });
    }

}