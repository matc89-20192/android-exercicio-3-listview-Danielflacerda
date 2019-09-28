package matc89.exercicio3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static matc89.exercicio3.R.id.listView;

public class MainActivity extends AppCompatActivity {

    private Button btnAdd;
    private Button btnRemove;
    private EditText edtdescricao;
    private EditText edtprioridade;
    private ListView list;
    private ArrayList<Tarefa> tarefas = new ArrayList<>();
    private Adapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.buttonAdicionar);
        btnRemove = (Button) findViewById(R.id.buttonRemover);
        edtdescricao = (EditText) findViewById(R.id.editDescricao);
        edtprioridade = (EditText) findViewById(R.id.editPrioridade);
        list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);
        adapter = new Adapter(this, tarefas);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int i, long l) {
                Tarefa tarefa = (Tarefa) list.getItemAtPosition(i);
                tarefas.remove(tarefa);
                adapter.notifyDataSetChanged();
            }
        });

    }

    public void addTask(View view)
    {
        if(tarefas.isEmpty()){
            btnRemove.setEnabled(true);
        }

        String descricao = edtdescricao.getText().toString();
        int prioridade = Integer.parseInt(edtprioridade.getText().toString());

        if(validarTarefa(descricao, prioridade)) {
            Tarefa tarefa = new Tarefa(descricao, prioridade);
            tarefas.add(tarefa);
            adapter.notifyDataSetChanged();
        }

        sortArrayList();
    }

    public void onRemoveClick(View view) {
        tarefas.remove(0);
        adapter.notifyDataSetChanged();
        if(tarefas.isEmpty()){
            btnRemove.setEnabled(false);
        }
    }
    public void sortArrayList(){
        Collections.sort(tarefas, new Comparator<Tarefa>() {
            @Override
            public int compare(Tarefa task, Tarefa task1) {
                if(task.getPrioridade() < task1.getPrioridade())
                {
                    return -1;
                }
                else if(task.getPrioridade() > task1.getPrioridade())
                {
                    return 1;
                }
                else
                {
                    return 0;
                }
            }
        });
        adapter.notifyDataSetChanged();
    }
    public boolean validarTarefa(String descricao, int prioridade) {
        Boolean aux = false;
        if (prioridade < 1 || prioridade > 10) {
            Toast.makeText(this, "A prioridade deve estar entre 1 e 10.", Toast.LENGTH_SHORT).show();
            return aux;
        } else {
            for (Tarefa tarefa : tarefas) {
                if (edtdescricao.equals(tarefa.getDescricao())) {
                    Toast.makeText(this, "Tarefa já cadastrada.", Toast.LENGTH_SHORT).show();
                    return aux;
                }
            }
        }
        aux = true;
        return aux;
    }

    protected void onSaveInstanceState(Bundle state)
    {
        super.onSaveInstanceState(state);
    }
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
