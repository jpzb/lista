package barcellos.joao_pedro.lista.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import barcellos.joao_pedro.lista.R;
import barcellos.joao_pedro.lista.model.NewItemActivityViewModel;

public class NewItemActivity extends AppCompatActivity {


    static int PHOTO_PICKER_REQUEST = 1;
    // Variável que irá receber o endereço da imagem, não a imagem em si, levando para outro app

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        NewItemActivityViewModel vm = new ViewModelProvider(this).get(NewItemActivityViewModel.class);
        // Pegando um ViewModel que armazene a Uri da imagem

        Uri selectPhotoLocation = vm.getSelectPhotoLocation();
        // Pegando o Uri de dentro do ViewModel
        if(selectPhotoLocation != null){
            // Verificando se o Uri é nulo, ou seja, se não foi selecionada nenhuma imagem
            ImageView imvfotoPreview = findViewById(R.id.imvPhotoPreview);
            // se não for nulo, irá setar a foto no ImageView
            imvfotoPreview.setImageURI(selectPhotoLocation);
        }

        Button btnAddItem = findViewById(R.id.btnAddItem);
        // Pegando o Button da activity_new_item.xml

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            // Colocando evento para pegar clicks no botão
            @Override
            public void onClick(View view) {

                Uri selectPhotoLocation = vm.getSelectPhotoLocation();
                if(selectPhotoLocation == null){
                    // Verificando se não foi selecionada uma foto
                    Toast.makeText(NewItemActivity.this, "É necessário selecionar " +
                            "uma imagem!", Toast.LENGTH_LONG).show();
                    // Mensagem de erro informando ao usuário e terminando a função.
                    return;
                }
                EditText etTitle = findViewById(R.id.etTitle);
                // Pegando o EditText de título da activity_new_item.xml
                String title = etTitle.getText().toString();
                // Pegando a string do editText.
                if(title.isEmpty()){
                    // Verificando se a string é vazia
                    Toast.makeText(NewItemActivity.this, "É necessário inserir um " +
                            "título", Toast.LENGTH_LONG).show();
                    // Mensagem de erro informando ao usuário e terminando a função.
                    return;
                }

                EditText etDesc = findViewById(R.id.etDesc);
                // Pegando o EditText de descrição da activity_new_item.xml
                String description = etDesc.getText().toString();
                // Pegando a string do editText.
                if(description.isEmpty()){
                    Toast.makeText(NewItemActivity.this, "É necessário inserir um" +
                            "descrição", Toast.LENGTH_LONG).show();
                    // Mensagem de erro informando ao usuário e terminando a função.
                    return;
                }

                Intent i = new Intent();
                // Criação de uma intent e passando as informações da imagem, do título e da descrição
                i.setData(selectPhotoLocation);
                i.putExtra("title", title);
                i.putExtra("description", description);
                setResult(Activity.RESULT_OK, i);
                // Indicando que há dados para retorno
                finish(); // Finalizando a Activity
            }
        });

        ImageButton imbCl = findViewById(R.id.imbCl);
        // Pegando o ImagemButton da activity_new_item.xml
        imbCl.setOnClickListener(new View.OnClickListener() {
            // Colocando evento para pegar clicks no botão
            @Override
            public void onClick(View view) {
                // Abertura da Galeria
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                // Intent implícito para abrir documentos
                photoPickerIntent.setType("image/*");
                // Define que os documentos que o intent pegar serão do tipo image
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST);
                // Executando a intent
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // requestCode identifica a chamada do startActivityForResult
        // resultCode identifica se a Activity retornou com sucesso
        // data contém os dados retornados
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PHOTO_PICKER_REQUEST){
            // Verifica se o requestCode é igual o fornecido no startActivityForResult
            if(resultCode == Activity.RESULT_OK){
                // Verifica se a Activity retornou com sucesso
                Uri photoSelected = data.getData(); // Guardando o URI da imagem escolhida
                ImageView imvPhotoPreview = findViewById(R.id.imvPhotoPreview);
                // Pegando o ImageView da activity_new_item.xml
                imvPhotoPreview.setImageURI(photoSelected);
                // Colocando a imagem no ImageView

                NewItemActivityViewModel vm = new ViewModelProvider(this).get(NewItemActivityViewModel.class);
                // Pegando o ViewModel setando o Uri da imagem escolhida
                vm.setSelectPhotoLocation(photoSelected);
            }
        }
    }
}