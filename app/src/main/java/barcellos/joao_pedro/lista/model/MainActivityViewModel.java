package barcellos.joao_pedro.lista.model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {
    List<MyItem> itens = new ArrayList<>();
    // Lista de itens cadastrados

    public List<MyItem> getItens(){
        return itens;
        // retorna todos os itens
    }
}
