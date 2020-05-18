package cindodcindy.sirihpinang.prayernote.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import cindodcindy.sirihpinang.prayernote.R;
import cindodcindy.sirihpinang.prayernote.model.DataPray;
import cindodcindy.sirihpinang.prayernote.model.PrayPojo;

public class EditActivity extends AppCompatActivity {
    private TextView textView_date_edit, textView_btn_edit_save;
    private EditText editText_edit_pray;
    private Calendar calendar;
    private DataPray dataPray;
    private PrayPojo prayPojo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        textView_date_edit=findViewById(R.id.write_date_edit);
        editText_edit_pray=findViewById(R.id.write_pray_edit);
        textView_btn_edit_save=findViewById(R.id.save_pray_edit);

        dataPray = new DataPray(this);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            prayPojo = dataPray.getData(bundle.getInt("ID"));
            textView_date_edit.setText(prayPojo.getDate());
            editText_edit_pray.setText(prayPojo.getPray());
        }

        calendar = Calendar.getInstance();


        textView_date_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(EditActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String formatTanggal = "dd-MM-yyyy";
                        SimpleDateFormat sdf = new SimpleDateFormat(formatTanggal);
                        textView_date_edit.setText(sdf.format(calendar.getTime()));
                    }
                },
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();


            }
        });

        textView_btn_edit_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataPray.updateData(prayPojo.getPrayId(), textView_date_edit.getText().toString(), editText_edit_pray.getText().toString());
                finish();

            }
        });
    }
}
