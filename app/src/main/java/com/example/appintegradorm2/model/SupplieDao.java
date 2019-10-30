package com.example.appintegradorm2.model;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class SupplieDao {

    private ArrayList<Supplie> BD;

    public ArrayList<Supplie> obterLista(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults list = realm.where(Supplie.class).findAll();
        BD.clear();
        BD.addAll(realm.copyFromRealm(list));
        return BD;
    }

    public void adicionarNaLista(Supplie c){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(c);
        realm.commitTransaction();
    }

    public int atualizaNaLista(Supplie c){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(c);
        realm.commitTransaction();

        for(int i = 0; i < BD.size(); i++){
            if(BD.get(i).getId().equals(c.getId())){
                BD.set(i, c);
                return i;
            }
        }
        return -1; //nao encontrou o compromisso para atualizar
    }

    public int excluiDaLista(Supplie c){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.where(Supplie.class).equalTo("id", c.getId()).findFirst().deleteFromRealm();
        realm.commitTransaction();

        for(int i = 0; i < BD.size(); i++){
            if(BD.get(i).getId().equals(c.getId())){
                BD.remove(i);
                return i;
            }
        }
        return -1;//nao encontrou o compromisso para excluir
    }

    public Supplie obterObjetoPeloId(String id){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Supplie c = realm.copyFromRealm(realm.where(Supplie.class).equalTo("id", id).findFirst());
        realm.commitTransaction();
        return c;
    }



    //Padrão de projeto DAO
    private static SupplieDao INSTANCIA;

    public static SupplieDao obterInstancia(){
        if (INSTANCIA == null){
            INSTANCIA = new SupplieDao();
        }
        return INSTANCIA;
    }

    private SupplieDao(){
        BD = new ArrayList<Supplie>();
    }




}
