package net.openwritings.db.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT);

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.linearLayout = new LinearLayout(this);
        this.linearLayout.setLayoutParams(this.layoutParams);
        this.linearLayout.setOrientation(LinearLayout.VERTICAL);
        this.linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        setContentView(this.linearLayout);

        this.addData();

        this.retrieveData();
    }

    private void addData(){
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase(); // Open connection.
        db.execSQL("INSERT INTO Persons(firstname, age) VALUES('John', 22)");
        db.execSQL("INSERT INTO Persons(firstname, age) VALUES('Kate', 27)");
        db.close();
    }

    private void retrieveData(){
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase(); // Open connection.

        Cursor cursor = db.rawQuery("SELECT _id, firstname, age FROM Persons", null);
        while(cursor.moveToNext()){
            int i=0;
            String id        = cursor.getString(i++);
            String firstname = cursor.getString(i++);
            String age       = cursor.getString(i++);

            String text = String.format("id=%s, firstname=%s, age=%s", id, firstname, age);
            TextView textView = new TextView(this);
            textView.setText(text);
            this.linearLayout.addView(textView);
        }
        cursor.close();
        db.close();
    }
}
