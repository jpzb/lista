package barcellos.joao_pedro.galeria.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import barcellos.joao_pedro.galeria.R;

public class MainActivity extends AppCompatActivity {
    
    static int NEW_ITEM_REQUEST = 1;
    // Identificiador da chamada de Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}