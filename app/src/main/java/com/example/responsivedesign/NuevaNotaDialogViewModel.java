package com.example.responsivedesign;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.responsivedesign.db.Entity.NotaEntity;

import java.util.List;

public class NuevaNotaDialogViewModel extends AndroidViewModel {
    // Nueva
    private LiveData<List<NotaEntity>> allNotas;
    private NotaRepository notaRepository;


    public NuevaNotaDialogViewModel(Application application){
        super(application);
        notaRepository = new NotaRepository(application);
        allNotas = notaRepository.getAll();
    }


    // El fragmento que necesita recibir la nueva lista de datos

    public LiveData<List<NotaEntity>>getAllNotas(){return allNotas;}

    // El fragmento que inserte una nueva nota, deberá comunicarlo a este viewModel
    public void insertarNota(NotaEntity nuevaNotaEntity){notaRepository.insert(nuevaNotaEntity);}
}
