package cl.cutiko.databaseoverlord;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cl.cutiko.databaseoverlord.database.PendingCreater;
import cl.cutiko.databaseoverlord.database.PendingReader;
import cl.cutiko.databaseoverlord.models.Pending;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("CLICK", "click");
                List<Pending> pendings = new ArrayList<>();
                pendings.add(new Pending("oli1", true));
                pendings.add(new Pending("oli2", true));
                pendings.add(new Pending("oli3", false));
                Log.d("PENDINGS", String.valueOf(pendings.size()));
                new PendingCreater(MainActivity.this).execute(pendings);
            }
        });

        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("CLICK", "long");
                new PendingReader(MainActivity.this).execute();

                return true;
            }
        });
    }
}
