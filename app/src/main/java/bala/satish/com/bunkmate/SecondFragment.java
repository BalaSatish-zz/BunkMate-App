package bala.satish.com.bunkmate;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;


public class SecondFragment extends Fragment implements View.OnClickListener {

    RadioGroup rgPercentage,rgOther;
    EditText CurrentAggregate, SemestersCompleted, etOther;
    TextView tvInfo;
    ImageView ivImage;
    View view;
    String Name="";
    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_second,container,false);

        if(AppCompatDelegate.getDefaultNightMode()== AppCompatDelegate.MODE_NIGHT_YES){
            view.setBackgroundColor(Color.parseColor("#353333"));
        }
        else {
            view.setBackgroundColor(Color.parseColor("#F2FFFFFF"));

        }

        Bundle b = getActivity().getIntent().getExtras();
        Name = b.getString("name");

        rgPercentage = (RadioGroup) view.findViewById(R.id.rgPercentage);
        CurrentAggregate = (EditText) view.findViewById(R.id.etCurrentAggregate);
        SemestersCompleted = (EditText) view.findViewById(R.id.etSemestersCompleted);
        CurrentAggregate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                rgPercentage.clearCheck();
                rgOther.clearCheck();
            }
        });
        SemestersCompleted.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                rgPercentage.clearCheck();
                rgOther.clearCheck();
            }
        });

        etOther = (EditText) view.findViewById(R.id.etOther);
        tvInfo = (TextView) view.findViewById(R.id.tvInfo);
        ivImage = (ImageView) view.findViewById(R.id.ivImage);
        rgOther = (RadioGroup) view.findViewById(R.id.rgOther);
        String[] ids={"rForty","rFortyFive","rFifty","rFiftyFive","rSixty","rSixtyFive","rSeventy","rSeventyFive","rEighty","rEightyFive","rNinety","rNinetyFive","rOther"};
        for(String id: ids){
            int ResId = getResources().getIdentifier(id,"id",getActivity().getPackageName());
            RadioButton r = (RadioButton) view.findViewById(ResId);
            r.setOnClickListener(this);
        }
        return view;
    }
    @Override
    public void onClick(View v) {
        int ResId = getResources().getIdentifier("rOther", "id", getActivity().getPackageName());
        RadioButton r = (RadioButton) view.findViewById(ResId);
        if (v != r) {
            rgOther.clearCheck();
            CurrentAggregate.onEditorAction(EditorInfo.IME_ACTION_DONE);
            SemestersCompleted.onEditorAction(EditorInfo.IME_ACTION_DONE);
            etOther.onEditorAction(EditorInfo.IME_ACTION_DONE);
            if ((CurrentAggregate.getText().toString().trim()).isEmpty() || (SemestersCompleted.getText().toString().trim()).isEmpty()) {
                //android.widget.Toast.makeText(MainActivity.this, "Sorry, we are Bad at Guessing", Toast.LENGTH_SHORT).show();
                Spannable s1 = new SpannableString("Sorry, we are Bad at Guessing");
                s1.setSpan(new RelativeSizeSpan(1.0f), 0,s1.length(), 0); // set size
                s1.setSpan(new ForegroundColorSpan(Color.RED),0,s1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvInfo.setText(""+s1);
                int res = getResources().getIdentifier("bored", "drawable", getActivity().getPackageName());
                ivImage.setBackgroundResource(res);
                return;
            }
            int SelecctedID = rgPercentage.getCheckedRadioButtonId();
            RadioButton rb = (RadioButton) view.findViewById(SelecctedID);
            String value = rb.getText().toString();
            value = value.replaceAll("%", "");
            double RA = (double) Double.parseDouble(value);
            double CA = 0, NSC = 0;
            CA = (double) Double.parseDouble(CurrentAggregate.getText().toString().trim());
            NSC = (double) Double.parseDouble(SemestersCompleted.getText().toString().trim());
            int x = (int) NSC;
            if (NSC > 7 || NSC<=0) {
                Spannable s1 = new SpannableString("Invalid Value: ");
                s1.setSpan(new RelativeSizeSpan(1.0f), 0,s1.length(), 0); // set size
                s1.setSpan(new ForegroundColorSpan(Color.RED),0,s1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Spannable s4 = new SpannableString(""+x);
                s4.setSpan(new RelativeSizeSpan(1.4f), 0,s4.length(), 0); // set size
                s4.setSpan(new ForegroundColorSpan(Color.RED),0,s4.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvInfo.setText(""+s1);
                tvInfo.append(s4);
                int res = getResources().getIdentifier("bored", "drawable", getActivity().getPackageName());
                ivImage.setBackgroundResource(res);
                return;
            }
            Calculate(CA, NSC, RA);
        } else {
            rgPercentage.clearCheck();
            CurrentAggregate.onEditorAction(EditorInfo.IME_ACTION_DONE);
            SemestersCompleted.onEditorAction(EditorInfo.IME_ACTION_DONE);
            etOther.onEditorAction(EditorInfo.IME_ACTION_DONE);
            if ((CurrentAggregate.getText().toString().trim()).isEmpty() || (SemestersCompleted.getText().toString().trim()).isEmpty()) {
                //android.widget.Toast.makeText(MainActivity.this, "Sorry, we are Bad at Guessing", Toast.LENGTH_SHORT).show();
                Spannable s1 = new SpannableString("Sorry, we are Bad at Guessing");
                s1.setSpan(new RelativeSizeSpan(1.0f), 0,s1.length(), 0); // set size
                s1.setSpan(new ForegroundColorSpan(Color.RED),0,s1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvInfo.setText(""+s1);
                int res = getResources().getIdentifier("bored", "drawable", getActivity().getPackageName());
                ivImage.setBackgroundResource(res);
                return;
            }
            int SelectedID = rgPercentage.getCheckedRadioButtonId();
            RadioButton rb = (RadioButton) view.findViewById(SelectedID);
            String value = etOther.getText().toString().trim();
            if (value.isEmpty()) {
                Spannable s1 = new SpannableString("Waiting for inputs");
                s1.setSpan(new RelativeSizeSpan(1.0f), 0,s1.length(), 0); // set size
//                s1.setSpan(new ForegroundColorSpan(Color.BLACK),0,s1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvInfo.setText(""+s1);
                int res = getResources().getIdentifier("bored", "drawable", getActivity().getPackageName());
                ivImage.setBackgroundResource(res);
                return;
            } else if (Double.parseDouble(value) > 100) {
                Spannable s1 = new SpannableString("Invalid Value: ");
                s1.setSpan(new RelativeSizeSpan(1.0f), 0,s1.length(), 0); // set size
                s1.setSpan(new ForegroundColorSpan(Color.RED),0,s1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Spannable s4 = new SpannableString(""+value);
                s4.setSpan(new RelativeSizeSpan(1.4f), 0,s4.length(), 0); // set size
                s4.setSpan(new ForegroundColorSpan(Color.RED),0,s4.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvInfo.setText(""+s1);
                tvInfo.append(s4);
                int res = getResources().getIdentifier("bored", "drawable", getActivity().getPackageName());
                ivImage.setBackgroundResource(res);
                return;
            }
            value = value.replaceAll("%", "");
            double RA = (double) Double.parseDouble(value);
            double CA = 0, NSC = 0;
            CA = (double) Double.parseDouble(CurrentAggregate.getText().toString().trim());
            NSC = (double) Double.parseDouble(SemestersCompleted.getText().toString().trim());
            int x = (int)NSC;
            if (NSC > 7 || NSC<=0) {
                Spannable s1 = new SpannableString("Invalid Value: ");
                s1.setSpan(new RelativeSizeSpan(1.0f), 0,s1.length(), 0); // set size
                s1.setSpan(new ForegroundColorSpan(Color.RED),0,s1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Spannable s4 = new SpannableString(""+x);
                s4.setSpan(new RelativeSizeSpan(1.4f), 0,s4.length(), 0); // set size
                s4.setSpan(new ForegroundColorSpan(Color.RED),0,s4.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvInfo.setText(""+s1);
                tvInfo.append(s4);
                int res = getResources().getIdentifier("bored", "drawable", getActivity().getPackageName());
                ivImage.setBackgroundResource(res);
                return;
            }
            Calculate(CA, NSC, RA);
        }
    }
    public void Calculate(double PA, double NSC, double RA){
        double x = 0;
        double SR = 8 - NSC;
        x = ((8 * RA) - (NSC * PA)) / (SR);
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.FLOOR);
        if (x < 100 && x>0) {
            int res = getResources().getIdentifier("nerd", "drawable", getActivity().getPackageName());
            ivImage.setBackgroundResource(res);
//            tvInfo.setText("You have to score " + new Double(df.format(x)) + " in next " + (int) SR + " Semesters to reach " + new Double(df.format(RA)) + "% Aggregate ");
            Spannable s1 = new SpannableString("Hi ");
            s1.setSpan(new RelativeSizeSpan(1.0f), 0,s1.length(), 0); // set size
//            s1.setSpan(new ForegroundColorSpan(Color.BLACK),0,s1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            Spannable s2 = new SpannableString(""+Name);
            s2.setSpan(new RelativeSizeSpan(1.1f), 0,s2.length(), 0); // set size
            s2.setSpan(new ForegroundColorSpan(Color.RED),0,s2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            Spannable s3 = new SpannableString(", you have to score ");
            s3.setSpan(new RelativeSizeSpan(1.0f), 0,s3.length(), 0); // set size
//            s3.setSpan(new ForegroundColorSpan(Color.BLACK),0,s3.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            Spannable s4 = new SpannableString(""+new Double(df.format(x)));
            s4.setSpan(new RelativeSizeSpan(1.4f), 0,s4.length(), 0); // set size
            s4.setSpan(new ForegroundColorSpan(Color.RED),0,s4.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            Spannable s5 = new SpannableString(" in next ");
            s5.setSpan(new RelativeSizeSpan(1.0f), 0,s5.length(), 0); // set size
//            s5.setSpan(new ForegroundColorSpan(Color.BLACK),0,s5.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            Spannable s6 = new SpannableString(""+(int)SR);
            s6.setSpan(new RelativeSizeSpan(1.4f), 0,s6.length(), 0); // set size
            s6.setSpan(new ForegroundColorSpan(Color.RED),0,s6.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            Spannable s7 = new SpannableString(" semesters to reach ");
            s7.setSpan(new RelativeSizeSpan(1.0f), 0,s7.length(), 0); // set size
//            s7.setSpan(new ForegroundColorSpan(Color.BLACK),0,s7.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            Spannable s8 = new SpannableString(""+new Double(df.format(RA))+"%");
            s8.setSpan(new RelativeSizeSpan(1.4f), 0,s8.length(), 0); // set size
            s8.setSpan(new ForegroundColorSpan(Color.RED),0,s8.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            Spannable s9 = new SpannableString(" aggregate.");
            s9.setSpan(new RelativeSizeSpan(1.0f), 0,s9.length(), 0); // set size
//            s9.setSpan(new ForegroundColorSpan(Color.BLACK),0,s9.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


            tvInfo.setText(""+s1);
            tvInfo.append(s2);
            tvInfo.append(s3);
            tvInfo.append(s4);
            tvInfo.append(s5);
            tvInfo.append(s6);
            tvInfo.append(s7);
            tvInfo.append(s8);
            tvInfo.append(s9);
        } else if(x>100){
//            tvInfo.setText("Scoring More than 100% is impossible.");
            Spannable s3 = new SpannableString("Sorry ");
            s3.setSpan(new RelativeSizeSpan(1.0f), 0,s3.length(), 0); // set size
//            s3.setSpan(new ForegroundColorSpan(Color.BLACK),0,s3.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            Spannable s4 = new SpannableString(""+Name);
            s4.setSpan(new RelativeSizeSpan(1.4f), 0,s4.length(), 0); // set size
            s4.setSpan(new ForegroundColorSpan(Color.RED),0,s4.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            Spannable s5 = new SpannableString(", scoring more than ");
            s5.setSpan(new RelativeSizeSpan(1.0f), 0,s5.length(), 0); // set size
//            s5.setSpan(new ForegroundColorSpan(Color.BLACK),0,s5.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            Spannable s6 = new SpannableString("100%");
            s6.setSpan(new RelativeSizeSpan(1.4f), 0,s6.length(), 0); // set size
            s6.setSpan(new ForegroundColorSpan(Color.RED),0,s6.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            Spannable s7 = new SpannableString(" in next semesters is impossible.");
            s7.setSpan(new RelativeSizeSpan(1.0f), 0,s7.length(), 0); // set size
//            s7.setSpan(new ForegroundColorSpan(Color.BLACK),0,s7.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvInfo.setText(""+s3);
            tvInfo.append(s4);
            tvInfo.append(s5);
            tvInfo.append(s6);
            tvInfo.append(s7);
            int res = getResources().getIdentifier("impossible", "drawable", getActivity().getPackageName());
            ivImage.setBackgroundResource(res);

        }
        else if(x<=0){
            Spannable s1 = new SpannableString("Really! ");
            s1.setSpan(new RelativeSizeSpan(1.0f), 0,s1.length(), 0); // set size
//            s1.setSpan(new ForegroundColorSpan(Color.BLACK),0,s1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            Spannable s2 = new SpannableString(""+Name);
            s2.setSpan(new RelativeSizeSpan(1.1f), 0,s2.length(), 0); // set size
            s2.setSpan(new ForegroundColorSpan(Color.RED),0,s2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            Spannable s3 = new SpannableString(", you have to score ");
            s3.setSpan(new RelativeSizeSpan(1.0f), 0,s3.length(), 0); // set size
//            s3.setSpan(new ForegroundColorSpan(Color.BLACK),0,s3.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            Spannable s4 = new SpannableString(""+new Double(df.format(x)));
            s4.setSpan(new RelativeSizeSpan(1.4f), 0,s4.length(), 0); // set size
            s4.setSpan(new ForegroundColorSpan(Color.RED),0,s4.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            Spannable s5 = new SpannableString(" in next ");
            s5.setSpan(new RelativeSizeSpan(1.0f), 0,s5.length(), 0); // set size
//            s5.setSpan(new ForegroundColorSpan(Color.BLACK),0,s5.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            Spannable s6 = new SpannableString(""+(int)SR);
            s6.setSpan(new RelativeSizeSpan(1.4f), 0,s6.length(), 0); // set size
            s6.setSpan(new ForegroundColorSpan(Color.RED),0,s6.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            Spannable s7 = new SpannableString(" semesters to reach ");
            s7.setSpan(new RelativeSizeSpan(1.0f), 0,s7.length(), 0); // set size
//            s7.setSpan(new ForegroundColorSpan(Color.BLACK),0,s7.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            Spannable s8 = new SpannableString(""+new Double(df.format(RA))+"%");
            s8.setSpan(new RelativeSizeSpan(1.4f), 0,s8.length(), 0); // set size
            s8.setSpan(new ForegroundColorSpan(Color.RED),0,s8.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            Spannable s9 = new SpannableString(" aggregate.");
            s9.setSpan(new RelativeSizeSpan(1.0f), 0,s9.length(), 0); // set size
//            s9.setSpan(new ForegroundColorSpan(Color.BLACK),0,s9.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


            tvInfo.setText(""+s1);
            tvInfo.append(s2);
            tvInfo.append(s3);
            tvInfo.append(s4);
            tvInfo.append(s5);
            tvInfo.append(s6);
            tvInfo.append(s7);
            tvInfo.append(s8);
            tvInfo.append(s9);

            int res = getResources().getIdentifier("smile", "drawable", getActivity().getPackageName());
            ivImage.setBackgroundResource(res);
        }
    }

}