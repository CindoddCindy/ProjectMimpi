package cindodcindy.sirihpinang.prayernote.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import cindodcindy.sirihpinang.prayernote.R;
import cindodcindy.sirihpinang.prayernote.model.DataPray;
import cindodcindy.sirihpinang.prayernote.model.DataPrayAnsw;
import cindodcindy.sirihpinang.prayernote.view.AnswPrayList;
import cindodcindy.sirihpinang.prayernote.view.ListDoa;

public class WriteAnsw extends AppCompatActivity {

   public Calendar calendar;

    private DataPrayAnsw dataPrayAnsw;

    private TextView textView_date_answ;
    private  TextView textView_save_pr_answ;
    private EditText editText_pr_answ;
    private TextView textView_date_dr_pray;
    private  TextView textView_pray_dr_pray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_answ);

        calendar = Calendar.getInstance();

        dataPrayAnsw = new DataPrayAnsw(this);


        textView_date_answ=findViewById(R.id.tv_date_answ);
        editText_pr_answ=findViewById(R.id.write_pray_answ);
        textView_save_pr_answ=findViewById(R.id.tv_pray_answ_save);

        textView_date_dr_pray=findViewById(R.id.tv_get_date_pray);
        textView_pray_dr_pray=findViewById(R.id.tv_get_pray);

        if(getIntent().getExtras()!=null){
            /**
             * Jika Bundle ada, ambil data dari Bundle
             */
            Bundle bundle = getIntent().getExtras();
            textView_date_dr_pray.setText(bundle.getString("date_pray"));
            textView_pray_dr_pray.setText(bundle.getString("pray"));
        }

            textView_save_pr_answ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    dataPrayAnsw.insertDataAnsw(textView_date_dr_pray.getText().toString(), textView_pray_dr_pray.getText().toString(),textView_date_answ.getText().toString(), editText_pr_answ.getText().toString());
                    Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(WriteAnsw.this, AnswPrayList.class);
                    startActivity(intent);
                    finish();




            }
        });


        textView_date_answ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(WriteAnsw.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String formatTanggal = "dd-MM-yyyy";
                        SimpleDateFormat sdf = new SimpleDateFormat(formatTanggal);
                        textView_date_answ.setText(sdf.format(calendar.getTime()));
                    }
                },
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();



            }
        });
    }
}
