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
                String date = textView_write_date.getText().toString();
                String pray = textView_write_pray.getText().toString();
                if (isInputValid()) {
                    if (isEdit) {
                        updateNote(prayID, date, pray);
                    } else {
                        saveNote(date, pray);
                    }
                }


            }
        });


    }

    private void saveNote(String dates, String pray) {

         PrayPojo prayPojo = new PrayPojo( dates, pray);
        int success = dataPray.addPray(prayPojo);

        String message = "Note gagal disimpan";

        if (success != 0) {
            message = "Note berhasil disimpan";
            finish();
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    private boolean isInputValid() {
        if (TextUtils.isEmpty(textView_write_date.getText()) || TextUtils.isEmpty(textView_write_pray.getText())) {
            if (TextUtils.isEmpty(textView_write_date.getText())) {
                textView_write_date.setError("Judul tidak boleh kosong!");
            }

            if (TextUtils.isEmpty(textView_write_pray.getText())) {
                textView_write_pray.setError("Note tidak boleh kosong!");
            }

            return false;
        }

        return true;
    }

    private void updateNote(int prayID, String date, String pray) {
        PrayPojo prayPojo = new PrayPojo(prayID, date, pray);
        int success = dataPray.updatePrayNote(prayPojo);

        String message = "Note gagal di update";

        if (success != 0) {
            message = "Note berhasil di update";
            finish();
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}

