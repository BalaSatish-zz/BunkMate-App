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


public class FirstFragment extends Fragment implements View.OnClickListener {
    RadioGroup rgPercentage,rgOther;
    EditText ClassesConducted, ClassesPresent, etOther;
    TextView tvInfo,tvCurrentPercentage;
    ImageView ivImage;
    View view;
    String Name ="";
    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_first,container,false);

        if(AppCompatDelegate.getDefaultNightMode()== AppCompatDelegate.MODE_NIGHT_YES){
            view.setBackgroundColor(Color.parseColor("#353333"));
        }
        else{
            view.setBackgroundColor(Color.parseColor("#F2FFFFFF"));

        }
        Bundle b = getActivity().getIntent().getExtras();
        Name = b.getString("name");
        rgPercentage = (RadioGroup) view.findViewById(R.id.rgPercentage);
        ClassesConducted = (EditText) view.findViewById(R.id.etClassesConducted);
        ClassesPresent = (EditText) view.findViewById(R.id.etClassesPresent);
        tvCurrentPercentage = (TextView) view.findViewById(R.id.tvCurrentPercentage);

        ClassesConducted.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                rgOther.clearCheck();
                rgPercentage.clearCheck();
                tvCurrentPercentage.setVisibility(View.GONE);
            }
        });
        ClassesPresent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                rgOther.clearCheck();
                rgPercentage.clearCheck();
                tvCurrentPercentage.setVisibility(View.GONE);
            }
        });
        etOther = (EditText) view.findViewById(R.id.etOther);
        tvInfo = (TextView) view.findViewById(R.id.tvInfo);
        ivImage = (ImageView) view.findViewById(R.id.ivImage);
        rgOther = (RadioGroup) view.findViewById(R.id.rgOther);
        String[] ids={"rSixtyFive","rSeventy","rSeventyFive","rEighty","rEightyFive","rNinety","rNinetyFive","rOther"};
        for(String id: ids){
            int ResId = getResources().getIdentifier(id,"id",getActivity().getPackageName());
            RadioButton r = (RadioButton) view.findViewById(ResId);
            r.setOnClickListener(this);
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        int ResId = getResources().getIdentifier("rOther","id",getActivity().getPackageName());
        RadioButton r = (RadioButton) view.findViewById(ResId);
        if(v!=r){
            rgOther.clearCheck();
            tvCurrentPercentage.setVisibility(View.GONE);
            AutoCalculate(1);


        }else{
            rgPercentage.clearCheck();
            tvCurrentPercentage.setVisibility(View.GONE);
            AutoCalculate(2);

        }

    }




    public void Calculate(double C, double P, double R) {
        double n = 0, fCurrent = 0;
        double x = 0;
        x = C;
        fCurrent = (P / C) * 100;
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.FLOOR);
        tvCurrentPercentage.setText("Current Percentage: "+new Double(df.format(fCurrent))+"%");
        tvCurrentPercentage.setVisibility(View.VISIBLE);
        while (fCurrent < R) {
            P = P + 1;
            C = C + 1;
            fCurrent = (P / C) * 100;
            if (fCurrent >= R) {
                x = C - x;
                if(x>25){
                    int res=getResources().getIdentifier("sad", "drawable",getActivity().getPackageName());
                    ivImage.setBackgroundResource(res);
                }else{
                    int res=getResources().getIdentifier("smile", "drawable",getActivity().getPackageName());
                    ivImage.setBackgroundResource(res);

                }

                Spannable s1 = new SpannableString("Hi ");
                s1.setSpan(new RelativeSizeSpan(1.0f), 0,s1.length(), 0); // set size
//                s1.setSpan(new ForegroundColorSpan(Color.BLACK),0,s1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Spannable s2 = new SpannableString(""+Name);
                s2.setSpan(new RelativeSizeSpan(1.1f), 0,s2.length(), 0); // set size
                s2.setSpan(new ForegroundColorSpan(Color.RED),0,s2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Spannable s3 = new SpannableString(", you have to attend ");
                s3.setSpan(new RelativeSizeSpan(1.0f), 0,s3.length(), 0); // set size
//                s3.setSpan(new ForegroundColorSpan(Color.BLACK),0,s3.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Spannable s4 = new SpannableString(""+(int)x);
                s4.setSpan(new RelativeSizeSpan(1.4f), 0,s4.length(), 0); // set size
                s4.setSpan(new ForegroundColorSpan(Color.RED),0,s4.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Spannable s5 = new SpannableString(" more classes to maintain your attendance at ");
                s5.setSpan(new RelativeSizeSpan(1.0f), 0,s5.length(), 0); // set size
//                s5.setSpan(new ForegroundColorSpan(Color.BLACK),0,s5.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Spannable s6 = new SpannableString(""+ new Double(df.format(fCurrent))+"%");
                s6.setSpan(new RelativeSizeSpan(1.4f), 0,s6.length(), 0); // set size
                s6.setSpan(new ForegroundColorSpan(Color.RED),0,s6.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Spannable s7 = new SpannableString(".");
                s7.setSpan(new RelativeSizeSpan(1.0f), 0,s7.length(), 0); // set size
//                s7.setSpan(new ForegroundColorSpan(Color.BLACK),0,s7.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                tvInfo.setText(""+s1);
                tvInfo.append(s2);
                tvInfo.append(s3);
                tvInfo.append(s4);
                tvInfo.append(s5);
                tvInfo.append(s6);
                tvInfo.append(s7);
                //tvInfo.setText("Attend " + (int)x + " more classes and maintain your attendance at " + new Double(df.format(fCurrent)) + "%.");
                return;
            }
        }
    }

    public void Bunk(double C, double P, double R){
        double n = 0, fCurrent = 0;
        double x = 0;
        x = C;
        fCurrent = (P / C) * 100;
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.FLOOR);
        tvCurrentPercentage.setText("Current Percentage: "+new Double(df.format(fCurrent))+"%");
        tvCurrentPercentage.setVisibility(View.VISIBLE);
        while (fCurrent > R) {
            C = C + 1;
            fCurrent = (P / C) * 100;
            if (fCurrent <= R) {
                C=C-1;
                x = C - x;
                fCurrent = (P / C) * 100;
                if((int)x>0){
                    // tvInfo.setText("Can bunk " +(int)x + " more classes with attendance at " + new Double(df.format(fCurrent)) + "%.");
                    Spannable s1 = new SpannableString("Hi ");
                    s1.setSpan(new RelativeSizeSpan(1.0f), 0,s1.length(), 0); // set size
//                    s1.setSpan(new ForegroundColorSpan(Color.BLACK),0,s1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    Spannable s2 = new SpannableString(""+Name);
                    s2.setSpan(new RelativeSizeSpan(1.1f), 0,s2.length(), 0); // set size
                    s2.setSpan(new ForegroundColorSpan(Color.RED),0,s2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    Spannable s3 = new SpannableString(", you can bunk ");
                    s3.setSpan(new RelativeSizeSpan(1.0f), 0,s3.length(), 0); // set size
//                    s3.setSpan(new ForegroundColorSpan(Color.BLACK),0,s3.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    Spannable s4 = new SpannableString(""+(int)x);
                    s4.setSpan(new RelativeSizeSpan(1.4f), 0,s4.length(), 0); // set size
                    s4.setSpan(new ForegroundColorSpan(Color.RED),0,s4.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    Spannable s5 = new SpannableString(" more classes keeping your attendance at ");
                    s5.setSpan(new RelativeSizeSpan(1.0f), 0,s5.length(), 0); // set size
//                    s5.setSpan(new ForegroundColorSpan(Color.BLACK),0,s5.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    Spannable s6 = new SpannableString(""+ new Double(df.format(fCurrent))+"%");
                    s6.setSpan(new RelativeSizeSpan(1.4f), 0,s6.length(), 0); // set size
                    s6.setSpan(new ForegroundColorSpan(Color.RED),0,s6.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    Spannable s7 = new SpannableString(".");
                    s7.setSpan(new RelativeSizeSpan(1.0f), 0,s7.length(), 0); // set size
//                    s7.setSpan(new ForegroundColorSpan(Color.BLACK),0,s7.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                    tvInfo.setText(""+s1);
                    tvInfo.append(s2);
                    tvInfo.append(s3);
                    tvInfo.append(s4);
                    tvInfo.append(s5);
                    tvInfo.append(s6);
                    tvInfo.append(s7);

                    int res=getResources().getIdentifier("happy", "drawable",getActivity().getPackageName());
                    ivImage.setBackgroundResource(res);
                }else{
                    //tvInfo.setText("Cannot bunk right now. ");
                    Spannable s1 = new SpannableString("Sorry ");
                    s1.setSpan(new RelativeSizeSpan(1.0f), 0,s1.length(), 0); // set size
//                    s1.setSpan(new ForegroundColorSpan(Color.BLACK),0,s1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    Spannable s2 = new SpannableString(""+Name);
                    s2.setSpan(new RelativeSizeSpan(1.1f), 0,s2.length(), 0); // set size
                    s2.setSpan(new ForegroundColorSpan(Color.RED),0,s2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    Spannable s3 = new SpannableString(", you cannot bunk right now.");
                    s3.setSpan(new RelativeSizeSpan(1.0f), 0,s3.length(), 0); // set size
//                    s3.setSpan(new ForegroundColorSpan(Color.BLACK),0,s3.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    tvInfo.setText(""+s1);
                    tvInfo.append(s2);
                    tvInfo.append(s3);
                    int res=getResources().getIdentifier("sad", "drawable",getActivity().getPackageName());
                    ivImage.setBackgroundResource(res);
                }
                C=C+2;
                return;
            }
        }
    }
    public void AutoCalculate(int n){
        if(n==1){
            ClassesConducted.onEditorAction(EditorInfo.IME_ACTION_DONE);
            ClassesPresent.onEditorAction(EditorInfo.IME_ACTION_DONE);
            etOther.onEditorAction(EditorInfo.IME_ACTION_DONE);
            if((ClassesConducted.getText().toString().trim()).isEmpty() || (ClassesPresent.getText().toString().trim()).isEmpty()){
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
            double C=0,P=0;
            C = (double) Integer.parseInt(ClassesConducted.getText().toString().trim());
            P = (double) Integer.parseInt(ClassesPresent.getText().toString().trim());
            if(P>C){
                double temp;
                temp = P;
                P=C;
                C=temp;
                ClassesConducted.setText(""+(int)C);
                ClassesPresent.setText(""+(int)P);
            }
            value = value.replaceAll("%","");
            double R = (double) Integer.parseInt(value);
            double x = (P/C)*100;
            if(x<R) {
                Calculate(C,P,R);
            }
            else if(x>R){
                Bunk(C,P,R);
            }
            else if(x==R){
                DisplayNotPossible(C,P);
            }
        }
        if(n==2){
            ClassesConducted.onEditorAction(EditorInfo.IME_ACTION_DONE);
            ClassesPresent.onEditorAction(EditorInfo.IME_ACTION_DONE);
            etOther.onEditorAction(EditorInfo.IME_ACTION_DONE);
            if((ClassesConducted.getText().toString().trim()).isEmpty() || (ClassesPresent.getText().toString().trim()).isEmpty()){
                //android.widget.Toast.makeText(MainActivity.this, "Sorry, we are Bad at Guessing", Toast.LENGTH_SHORT).show();
                Spannable s1 = new SpannableString("Sorry, we are Bad at Guessing");
                s1.setSpan(new RelativeSizeSpan(1.0f), 0,s1.length(), 0); // set size
                s1.setSpan(new ForegroundColorSpan(Color.RED),0,s1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvInfo.setText(""+s1);
                int res = getResources().getIdentifier("bored", "drawable", getActivity().getPackageName());
                ivImage.setBackgroundResource(res);
                return;
            }
            String value = etOther.getText().toString().trim();
            if(value.isEmpty()){
                Spannable s1 = new SpannableString("Waiting for inputs");
                s1.setSpan(new RelativeSizeSpan(1.0f), 0,s1.length(), 0); // set size
//                s1.setSpan(new ForegroundColorSpan(Color.BLACK),0,s1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvInfo.setText(""+s1);
                int res=getResources().getIdentifier("waiting", "drawable",getActivity().getPackageName());
                ivImage.setBackgroundResource(res);
                return;
            }else if(Double.parseDouble(value)>=100){
                Spannable s1 = new SpannableString("Invalid Value: ");
                s1.setSpan(new RelativeSizeSpan(1.0f), 0,s1.length(), 0); // set size
                s1.setSpan(new ForegroundColorSpan(Color.RED),0,s1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Spannable s4 = new SpannableString(""+value);
                s4.setSpan(new RelativeSizeSpan(1.4f), 0,s4.length(), 0); // set size
                s4.setSpan(new ForegroundColorSpan(Color.RED),0,s4.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvInfo.setText(""+s1);
                tvInfo.append(s4);
                int res=getResources().getIdentifier("bored", "drawable",getActivity().getPackageName());
                ivImage.setBackgroundResource(res);
                return;
            }
            double C=0,P=0;
            C = (double) Integer.parseInt(ClassesConducted.getText().toString().trim());
            P = (double) Integer.parseInt(ClassesPresent.getText().toString().trim());
            if(P>C){
                double temp;
                temp = P;
                P=C;
                C=temp;
                ClassesConducted.setText(""+(int)C);
                ClassesPresent.setText(""+(int)P);
            }
            value = value.replaceAll("%","");
            double R = (double) Double.parseDouble(value);
            double x = (P/C)*100;
            if(x<R) {
                Calculate(C,P,R);
            }
            else if(x>R){
                Bunk(C,P,R);
            }
            else if(x==R){
                DisplayNotPossible(C,P);
            }
        }
    }

    public  void DisplayNotPossible(double C,double P){
        double fCurrent = (P / C) * 100;
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.FLOOR);
        tvCurrentPercentage.setText("Current Percentage: "+new Double(df.format(fCurrent))+"%");
        tvCurrentPercentage.setVisibility(View.VISIBLE);

        Spannable s1 = new SpannableString("Sorry ");
        s1.setSpan(new RelativeSizeSpan(1.0f), 0,s1.length(), 0); // set size
//        s1.setSpan(new ForegroundColorSpan(Color.BLACK),0,s1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        Spannable s2 = new SpannableString(""+Name);
        s2.setSpan(new RelativeSizeSpan(1.1f), 0,s2.length(), 0); // set size
        s2.setSpan(new ForegroundColorSpan(Color.RED),0,s2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        Spannable s3 = new SpannableString(", you cannot bunk right now.");
        s3.setSpan(new RelativeSizeSpan(1.0f), 0,s3.length(), 0); // set size
//        s3.setSpan(new ForegroundColorSpan(Color.BLACK),0,s3.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvInfo.setText(""+s1);
        tvInfo.append(s2);
        tvInfo.append(s3);
        int res=getResources().getIdentifier("smile", "drawable",getActivity().getPackageName());
        ivImage.setBackgroundResource(res);

    }
}
