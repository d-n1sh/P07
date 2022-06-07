package sg.edu.rp.c346.id21012612.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText task;
    Button btnAdd;
    Button btnClear;
    Button btnDelete;
    ListView lvTasks;
    Spinner spnAddRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        task = findViewById(R.id.editTextTasks);
        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        lvTasks = findViewById(R.id.lvTasks);
        spnAddRemove = findViewById(R.id.spinner);
        btnDelete = findViewById(R.id.buttonDelete);

        ArrayList<String> alTasks;
        alTasks = new ArrayList<String>();

        ArrayAdapter aaTasks = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, alTasks);

        lvTasks.setAdapter(aaTasks);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alTasks.add(task.getText().toString());
                aaTasks.notifyDataSetChanged();
                task.setText(null);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alTasks.clear();
                aaTasks.notifyDataSetChanged();
            }
        });

        spnAddRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                switch (i){
                    case 0:
                        task.setHint("Type in a new task here");
                        btnDelete.setEnabled(false);
                        alTasks.add(task.getText().toString());
                        aaTasks.notifyDataSetChanged();
                        task.setText(null);
                        break;
                    case 1:
                        if (alTasks.isEmpty()){
                            Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                        }
                        else if(Integer.parseInt(task.getText().toString()) > alTasks.size() || Integer.parseInt(task.getText().toString()) < 0){
                            Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            task.setHint("Type in the index of the task to be removed");
                            btnAdd.setEnabled(false);
                            alTasks.remove(alTasks.get(i));
                            aaTasks.notifyDataSetChanged();
                            task.setText(null);
                            break;
                        }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}