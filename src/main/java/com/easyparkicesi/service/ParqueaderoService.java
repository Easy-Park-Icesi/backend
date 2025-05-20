package com.easyparkicesi.service;

import com.easyparkicesi.model.Parqueadero;
import com.easyparkicesi.model.Zona;
import com.google.firebase.database.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;


@Service
public class ParqueaderoService {

    private final DatabaseReference db = FirebaseDatabase.getInstance().getReference("zonas");

    public void toggleDisponibilidadParqueadero(String zonaId, String parqueaderoId) {
        db.child(zonaId).child("parqueaderos")
        .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<Parqueadero> parqueaderos = new ArrayList<>();

                 // Reconstruye la lista desde Firebase
                 for (DataSnapshot ds : snapshot.getChildren()) {
                    Parqueadero p = ds.getValue(Parqueadero.class);
                    parqueaderos.add(p);
                }

                boolean found = false;
                for (Parqueadero p : parqueaderos) {
                    if (p.getId().equals(parqueaderoId)) {
                        p.setDisponible(!p.isDisponible());
                        found = true;
                        break;
                    }
                } 

                if (found) {
                      // Reescribe la lista completa
                    snapshot.getRef().setValueAsync(parqueaderos);
                } else {
                    System.out.println("Parqueadero no encontrado con ID: " + parqueaderoId);
                }
            }  

          @Override
          public void onCancelled(DatabaseError error) {
              System.out.println("Error: " + error.getMessage());
            }
        });
    }


    public void contarOcupados(String zonaId, Callback callback) {
        db.child(zonaId).child("parqueaderos")
          .addListenerForSingleValueEvent(new ValueEventListener() {
              @Override
              public void onDataChange(DataSnapshot snapshot) {
                  int ocupados = 0;
                  for (DataSnapshot ds : snapshot.getChildren()) {
                      Parqueadero p = ds.getValue(Parqueadero.class);
                      if (p != null && !p.isDisponible()) {
                          ocupados++;
                      }
                  }
                  callback.onResult(ocupados);
              }

              @Override
              public void onCancelled(DatabaseError error) {
                  System.out.println("Error: " + error.getMessage());
              }
          });
    }


    public interface Callback {
        void onResult(int result);
    }
}
