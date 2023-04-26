package barcellos.joao_pedro.lista.model;

import android.net.Uri;

import androidx.lifecycle.ViewModel;


public class NewItemActivityViewModel extends ViewModel {

    Uri selectPhotoLocation = null;

    public Uri getSelectPhotoLocation(){
        return selectPhotoLocation;
        // Retornando a localização da foto
    }

    public void setSelectPhotoLocation(Uri selectPhotoLocation){
        this.selectPhotoLocation = selectPhotoLocation;
        // Setando a localização da foto
    }
}
