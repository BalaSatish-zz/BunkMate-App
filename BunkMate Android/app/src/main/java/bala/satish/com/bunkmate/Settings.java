package bala.satish.com.bunkmate;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    private Switch aSwitch;
    int colorAccent;
    final Context context = this;
    private String Name="";
    SQLiteDatabase mydb;
    private TextView tvChangeTheme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            mydb = openOrCreateDatabase("DB", MODE_PRIVATE, null);
            mydb.execSQL("CREATE TABLE IF NOT EXISTS User(id VARCHAR, name VARCHAR)");
            String sql = "SELECT * From User where id='1';";
            Cursor c = mydb.rawQuery(sql,null);
            if(c.getCount()==0){
                Log.i("msg","main0");
            }
            else if(c.getCount()==1){
                c.moveToNext();
                Name = c.getString(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
            Toast.makeText(this, "Error Occured", Toast.LENGTH_SHORT).show();
        }

        if(AppCompatDelegate.getDefaultNightMode()== AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.AppTheme);
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_settings);
        }
        else{
            setTheme(R.style.Light);
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_settings);
        }
        changeTheme();
        tvChangeTheme = (TextView) findViewById(R.id.textView);
        tvChangeTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Switch switch1 = (Switch)findViewById(R.id.switch1);
                if(switch1.isChecked()){
                    switch1.setChecked(false);
                }
                else{
                    switch1.setChecked(true);
                }
            }
        });
        colorAccent = ContextCompat.getColor(this,R.color.colorAccent);
    }

    public void changeTheme(){

        aSwitch = (Switch)findViewById(R.id.switch1);
        if (AppCompatDelegate.getDefaultNightMode()== AppCompatDelegate.MODE_NIGHT_YES){

            aSwitch.setChecked(true);
        }
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String theme = "";
                if(isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    theme="dark";
                    try {
                        int id = 2;
                        String sql1 ="UPDATE User SET name='"+theme+"' where id='"+id+"';";
                        mydb.execSQL(sql1);
                    }catch (SQLException e){
                        e.printStackTrace();
                    }
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    theme="light";
                    try {
                        int id = 2;
                        String sql1 ="UPDATE User SET name='"+theme+"' where id='"+id+"';";
                        mydb.execSQL(sql1);
                    }catch (SQLException e){
                        e.printStackTrace();
                    }
                }

                restartApp();
            }
        });
    }

    public  void restartApp(){
        Intent restart = new Intent(getApplicationContext(),Settings.class);
        startActivity(restart);
        finish();
    }
    public void cancel(View v){
        Intent i = new Intent(this,WholeActivity.class);
        i.putExtra("name",""+Name);
        startActivity(i);
        finish();
    }
    public void edit(View view){
        SQLiteDatabase mydb =openOrCreateDatabase("DB", MODE_PRIVATE, null);
        String sql = "DELETE FROM User where id='"+1+"'";
        mydb.execSQL(sql);
        Intent i = new Intent(Settings.this,MainActivity.class);
        startActivity(i);
        finish();
    }

    public void about(View view) {
        BottomSheet bottomsheetDialog = new BottomSheet();
        bottomsheetDialog.show(getSupportFragmentManager(),"BottomSheet");
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this,WholeActivity.class);
        i.putExtra("name",""+Name);
        startActivity(i);
        finish();
    }

    public void RateUs(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=bala.satish.com.bunkmate"));
        startActivity(browserIntent);
    }
}
