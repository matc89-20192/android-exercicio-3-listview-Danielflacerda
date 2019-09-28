package matc89.exercicio3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static matc89.exercicio3.R.id.listView;

public class MainActivity extends AppCompatActivity {

    private Button btnAdd;
    private Button btnRemove;
    private EditText descricao;
    private EditText prioridade;
    private ListView list;
    private ArrayList<Tarefa> tarefas;
    private Adapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.buttonAdicionar);
        btnRemove = (Button) findViewById(R.id.buttonRemover);
        descricao = (EditText) findViewById(R.id.editDescricao);
        prioridade = (EditText) findViewById(R.id.editPrioridade);
        list = (ListView) findViewById(R.id.listView);



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, values);
    }

    public void addTask(View view)
    {

    }
    public boolean validarTarefa(String descricao, int prioridade) {
        Boolean aux = false;
        if (prioridade < 1 || prioridade > 10) {
            Toast.makeText(this, "A prioridade deve estar entre 1 e 10.", Toast.LENGTH_SHORT).show();
            return aux;
        } else {
            for (Tarefa tarefa : tarefas) {
                if (descricao.equals(tarefa.getDescricao())) {
                    Toast.makeText(this, "Tarefa já cadastrada.", Toast.LENGTH_SHORT).show();
                    return aux;
                }
            }
        }
        aux = true;
        return aux;
    }
    public void onRemoveClick(View view) {
        tarefas.remove(0);
        adapter.notifyDataSetChanged();
        if(tarefas.isEmpty()){
            btnRemove.setEnabled(false);
        }
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
