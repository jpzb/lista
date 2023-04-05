package barcellos.joao_pedro.lista.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import barcellos.joao_pedro.lista.R;
import barcellos.joao_pedro.lista.activity.MainActivity;
import barcellos.joao_pedro.lista.model.MyItem;

public class MyAdapter extends RecyclerView.Adapter {

    MainActivity mainActivity;
    List<MyItem> itens;

    public MyAdapter(MainActivity mainActivity, List<MyItem> itens){
        // Construtor que receberá a MainActivity e a list de itens e irá colocar-los nos atributos
        // da classe
        this.mainActivity = mainActivity;
        this.itens = itens;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        // Inflador que irá ler os itens do layout de MainActivity e cria-los
        View v = inflater.inflate(R.layout.item_list, parent, false);
        // Elementos criados pelo inflador serão armazenados na View v
        return new MyViewHolder(v);
        // v será armazenado no objeto MyViewHolder e será retornado pela função
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // Guarda os itens
        MyItem myItem = itens.get(position);

        View v = holder.itemView;

        ImageView imvfoto = v.findViewById(R.id.imvPhoto);
        imvfoto.setImageURI(myItem.photo);
        // Preenche a UI com o ImageView com a foto

        TextView tvTitle = v.findViewById(R.id.tvTitle);
        tvTitle.setText(myItem.title);
        // Preenche a UI com o TextView com o título

        TextView tvDesc = v.findViewById(R.id.tvDesc);
        tvDesc.setText(myItem.descripion);
        // Preenche a UI com o TextView com a descrição
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }
}
