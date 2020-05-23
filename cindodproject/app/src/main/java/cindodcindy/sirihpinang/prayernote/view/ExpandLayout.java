package cindodcindy.sirihpinang.prayernote.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import cindodcindy.sirihpinang.prayernote.R;
import cindodcindy.sirihpinang.prayernote.controller.LoginUser;
import cindodcindy.sirihpinang.prayernote.controller.auth.SaveSharedPreference;

public class ExpandLayout extends AppCompatActivity {

    private TextView txtContent, textView_date, textView_answ_pr;
    private ImageView imageView_edit_answ, imageView_del_answ;
    private Animation animationUp;
    private Animation animationDown;
    private CardView cardView_answ_pr;
    private TextView textView_name, textView_pass, textView_logout;

    SharedPreferences sharedpreferences;

    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_layout);

        txtContent=findViewById(R.id.tv_prayer_answered_click);
        textView_date=findViewById(R.id.tv_date_ans);
        textView_answ_pr=findViewById(R.id.tv_answ_pr);
        imageView_edit_answ=findViewById(R.id.iv_edit_answ);
        imageView_del_answ=findViewById(R.id.iv_delete_answ);
        cardView_answ_pr=findViewById(R.id.cv_answ_pr);

        textView_logout=findViewById(R.id.tv_logout);

        textView_name=findViewById(R.id.tv_user_name);
        textView_pass=findViewById(R.id.tv_paas_);


        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sharedpreferences.contains(Name)) {
            textView_name.setText(sharedpreferences.getString(Name, ""));
        }
        if (sharedpreferences.contains(Email)) {
            textView_pass.setText(sharedpreferences.getString(Email, ""));

        }




        cardView_answ_pr.setVisibility(View.GONE);

        animationUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        animationDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

        txtContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cardView_answ_pr.isShown()){
                    cardView_answ_pr.setVisibility(View.GONE);
                    cardView_answ_pr.startAnimation(animationUp);
                }
                else{
                    cardView_answ_pr.setVisibility(View.VISIBLE);
                    cardView_answ_pr.startAnimation(animationDown);
                }

            }
        });

        textView_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveSharedPreference.setLoggedIn(getApplicationContext(), false);
                Intent intent = new Intent(getApplicationContext(), LoginUser.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });

    }
}
