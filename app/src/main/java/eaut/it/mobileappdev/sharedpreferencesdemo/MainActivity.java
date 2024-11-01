package eaut.it.mobileappdev.sharedpreferencesdemo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText name, age;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.edit1);
        age = findViewById(R.id.edit2);
        checkBox = findViewById(R.id.checkBox);
    }

    // Fetch the stored data in onResume() Because this is what will be called when the app opens again
    @Override
    protected void onResume() {
        super.onResume();
        // Fetching the stored data from the SharedPreference
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String s1 = sh.getString("name", "");
        int a = sh.getInt("age", 0);
        boolean saveSp = sh.getBoolean("saveSp",false);

        // Setting the fetched data in the EditTexts
        name.setText(s1);
        age.setText(String.valueOf(a));
        checkBox.setChecked(saveSp);
    }

    // Store the data in the SharedPreference in the onPause() method
    // When the user closes the application onPause() will be called and data will be stored
    @Override
    protected void onPause() {
        super.onPause();
        // Creating a shared pref object with a file name "MySharedPref" in private mode
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(checkBox.isChecked()) {
            // write all the data entered by the user in SharedPreference and apply
            editor.putString("name", name.getText().toString());
            editor.putInt("age", Integer.parseInt(age.getText().toString()));
        }
        editor.putBoolean("saveSp",checkBox.isChecked());
        editor.apply();
    }

    @Override
    public void onClick(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Xóa từng giá trị cụ thể
        editor.remove("name");
        editor.remove("age");
        editor.apply();

        // Xóa toàn bộ dữ liệu
        editor.clear();
        editor.apply();

        name.setText("");
        age.setText("0");
    }
}
