package bala.satish.com.bunkmate;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {
    private EditText etName;
    private String id = "1";
    private String Name="";
    private String theme="";
    SQLiteDatabase mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mydb = openOrCreateDatabase("DB", MODE_PRIVATE, null);
            mydb.execSQL("CREATE TABLE IF NOT EXISTS User(id VARCHAR PRIMARY KEY, name VARCHAR)");
            String sql = "SELECT * From User where id='1';";
            Cursor c = mydb.rawQuery(sql,null);
            if(c.getCount()==0){
                Log.i("msg","main0");
//                Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
            }
            else if(c.getCount()==1){
                c.moveToNext();
                Name = c.getString(1);
                Intent i = new Intent(MainActivity.this,WholeActivity.class);
                i.putExtra("name",""+Name);
//                i.putExtra("Theme",""+theme);
//                Toast.makeText(this, "4", Toast.LENGTH_SHORT).show();
                startActivity(i);
                finish();
            }

            String sql1 = "SELECT * From User where id='2';";
            Cursor c1 = mydb.rawQuery(sql1,null);
            if(c1.getCount()==0){
                Log.i("msg","main0");
                theme = "light";
                String sql2 ="INSERT INTO User(id,name) VALUES('"+2+"','"+theme+"')";
                mydb.execSQL(sql2);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                setTheme();
            }
            else if(c1.getCount()==1){
                c1.moveToNext();
                theme = c1.getString(1);
                if(theme.contains("dark")){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else if(theme.contains("light"))
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                setTheme();
            }

        }catch (SQLException e){
            e.printStackTrace();
            Toast.makeText(this, "Error Occured", Toast.LENGTH_SHORT).show();
        }


        try {
            mydb = openOrCreateDatabase("DB", MODE_PRIVATE, null);

        }catch (SQLException e){
            e.printStackTrace();
            Toast.makeText(this, "Error Occured", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View v) {

    }

    public void AddName(View view) {
        etName = (EditText) findViewById(R.id.etName);
        Name = etName.getText().toString().trim();
        if(Name.isEmpty()){
            Toast.makeText(this, "Please enter your name.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(Name.length()<=2){
            Toast.makeText(this, "Name should be more than 2 characters. ", Toast.LENGTH_SHORT).show();
            return;
        }
        Name = Name.replace(".","");
        Name = Name.replace(",","");
        String sql = "SELECT * From User where id='1';";
        Cursor c = mydb.rawQuery(sql,null);
        if(c.getCount()==0){
            String sql1 ="INSERT INTO User(id,name) VALUES('"+id+"','"+Name+"')";
            mydb.execSQL(sql1);
            Intent i = new Intent(MainActivity.this,WholeActivity.class);
            i.putExtra("name",""+Name);
            startActivity(i);
            finish();
        }
        else if(c.getCount()==1){
            String sql1 ="UPDATE User SET name='"+Name+"' where id='"+id+"';";
            mydb.execSQL(sql1);
            Intent i = new Intent(MainActivity.this,WholeActivity.class);
            i.putExtra("name",""+Name);
            startActivity(i);
            finish();

        }
    }
    public void setTheme(){
        if(AppCompatDelegate.getDefaultNightMode()== AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.AppTheme);
            setContentView(R.layout.activity_main);
        }
        else
        {
            setTheme(R.style.Light);
            setContentView(R.layout.activity_main);
        }
    }
}

