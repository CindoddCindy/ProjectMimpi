package cindodcindy.sirihpinang.prayernote.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import cindodcindy.sirihpinang.prayernote.R;
import cindodcindy.sirihpinang.prayernote.model.DataPray;
import cindodcindy.sirihpinang.prayernote.model.PrayPojo;
import cindodcindy.sirihpinang.prayernote.view.ListDoa;

public class WriteDoa extends AppCompatActivity {

    public EditText  textView_write_pray ;
    TextView textView_write_date, textView_save_write;

    Calendar calendar;

    private  DataPray dataPray;
    private Intent dataIntent;
    private boolean isEdit = false;
    private int prayID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_doa);
        textView_write_date = findViewById(R.id.write_date);
        textView_write_pray = findViewById(R.id.write_pray);
        textView_save_write = findViewById(R.id.save_pray);

        calendar = Calendar.getInstance();

        dataPray = new DataPray(this);


        textView_write_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(WriteDoa.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String formatTanggal = "dd-MM-yyyy";
                        SimpleDateFormat sdf = new SimpleDateFormat(formatTanggal);
                        textView_write_date.setText(sdf.format(calendar.getTime()));
                    }
                },
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();


            }
        });

        textView_save_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        dataPray.insertData(textView_write_date.getText().toString(), textView_write_pray.getText().toString(),textView_write_date.getText().toString(),textView_write_pray.getText().toString());
                        Toast.makeText(getApplicationContext(), "Data Tersimpan", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(WriteDoa.this, ListDoa.class);
                        startActivity(intent);
                        finish();

            }
        });


    }


}

