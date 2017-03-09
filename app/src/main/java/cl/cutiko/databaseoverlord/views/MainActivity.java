package cl.cutiko.databaseoverlord.views;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import cl.cutiko.databaseoverlord.R;
import cl.cutiko.databaseoverlord.adapters.PendingsAdapter;
import cl.cutiko.databaseoverlord.database.PendingReader;
import cl.cutiko.databaseoverlord.models.Pending;

public class MainActivity extends AppCompatActivity {

    private PendingsAdapter adapter = new PendingsAdapter();

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

            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.pendingsRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        new GetAllPendings(this).execute();
    }

    private class GetAllPendings extends PendingReader {

        public GetAllPendings(Context context) {
            super(context);
        }

        @Override
        protected void onPostExecute(List<Pending> pendings) {
            adapter.addPendings(pendings);
        }
    }
}
