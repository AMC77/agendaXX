package com.example.responsivedesign;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.example.responsivedesign.db.Entity.NotaEntity;

public class NuevaNotaDialogFragment extends DialogFragment {

    private NuevaNotaDialogFragment mViewModel;
    public static NuevaNotaDialogFragment newInstance(){return new NuevaNotaDialogFragment}

    @Override     // ---- ON ACTIVITYCREATED ----
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    private View view;
    private EditText etTitulo, etContenido;
    private RadioGroup rgColor;
    private Switch swNotaFavorita;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.nueva_nota_dialog_fragment, container, false);
    }


    public class NuevaNotaDialogFragment  extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Nueva nota");
            builder.setMessage("Introduzca los datos de la nueva nota")

                    // POSITIVE BUTTON QUE LE PASA AL VIEW MODEL ESA NUEVA NOTA
                    .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //Boton positivo , el usuario quiere guardar
                            String titulo = etTitulo.getText().toString();
                            String contenido = etContenido.getText().toString();
                            String color = "azul";
                            switch (rgColor.getCheckedRadioButtonId()){
                                case R.id.radioButtonRojo:
                                    color = "rojo";
                                    break;
                                case R.id.radioButtonVerde:
                                    color = "verde";
                                    break;
                                case R.id.radioButtonAzul:
                                    color = "azul";
                                    break;
                            }
                            boolean esFavorita = swNotaFavorita.isChecked();

                            NuevaNotaDialogViewModel mViewModel  = ViewModelProviders.of(getActivity()).get(NuevaNotaDialogViewModel.class);
                            mViewModel.insertarNota(new NotaEntity(titulo,contenido,esFavorita,color));
                            dialog.dismiss();
                        }

                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                            dialog.dismiss();
                        }
                    });

            LayoutInflater inflater = getActivity().getLayoutInflater();
            view = inflater.inflate(R.layout.nueva_nota_dialog_fragment,null);
            etTitulo = view.findViewById(R.id.editTextTitulo);
            etContenido = view.findViewById(R.id.editTextContenido);
            rgColor = view.findViewById(R.id.radioGroupColor);
            swNotaFavorita = view.findViewById(R.id.switchNotaFavorita);

            builder.setView(view);

            // Create the AlertDialog object and return it
            return builder.create();
        }
    }

}
