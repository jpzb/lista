package barcellos.joao_pedro.lista.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import barcellos.joao_pedro.lista.R;
import barcellos.joao_pedro.lista.adapter.MyAdapter;
import barcellos.joao_pedro.lista.model.MyItem;

public class MainActivity extends AppCompatActivity {
    
    static int NEW_ITEM_REQUEST = 1;
    // Identificiador da chamada de Activity

    MyAdapter myAdapter;
    List<MyItem> itens = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvItens = findViewById(R.id.rvItens);
        // Pegando o RecyclerView da activity_main.xml

        myAdapter = new MyAdapter(this, itens);
        // Criando MyAdapter da MainActivity

        rvItens.setAdapter(myAdapter);
        // Setando o MyAdapter como adaptador do RecyclerView

        rvItens.setHasFixedSize(true);
        // Indica que não haverá mudança de tamanho de cada item

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvItens.setLayoutManager(layoutManager);
        // Setando o gerenciador de layout do tipo linear
        // Faz com que a lista organiza-se na vertical
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                rvItens.getContext(), DividerItemDecoration.VERTICAL);
        rvItens.addItemDecoration(dividerItemDecoration);
        // Adiciona a decoração de divisor de lista que divide os itens em uma lista no RecyclerView.


        FloatingActionButton fabAddItem = findViewById(R.id.fabAddNewItem);
        // Pegando o FloatingActionButton da activity_main.xml
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            // Colocando um evento para pegar clicks no botão
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, NewItemActivity.class);
                // Criando a intenção para ir para a nova Activity
                startActivityForResult(i, NEW_ITEM_REQUEST);
                // Iniciando a intenção, assumindo que a nova intenção retornará alguma informação
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        // requestCode identifica a chamada do startActivityForResult
        // resultCode identifica se a Activity retornou com sucesso
        // data contém os dados retornados
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == NEW_ITEM_REQUEST){
            // Verifica se o requestCode é igual o fornecido no startActivityForResult
            if(resultCode == Activity.RESULT_OK){
                // Verifica se a Activity retornou com sucesso
                MyItem myItem = new MyItem(); // Criando um objeto de classe MyItem
                myItem.title = data.getStringExtra("title");
                // Colocando o atributo de título recebido da NewItemActivity no myItem
                myItem.descripion = data.getStringExtra("description");
                // Colocando o atributo de descrição recebido da NewItemActivity no myItem
                myItem.photo = data.getData();
                // Colocando a foto recebida da NewItemActivity no myItem

                itens.add(myItem);
                // Colocando na lista de itens criado no MainActivity
                myAdapter.notifyItemInserted(itens.size()-1);
            }
        }
    }
}