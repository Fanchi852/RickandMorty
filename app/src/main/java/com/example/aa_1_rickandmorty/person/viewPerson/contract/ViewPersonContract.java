package com.example.aa_1_rickandmorty.person.viewPerson.contract;

import com.example.aa_1_rickandmorty.beans.Episode;
import com.example.aa_1_rickandmorty.beans.Location;

/*
en este caso de uso la informacion ya esta en el dispositivo por lo que no hay llamadas a la base de datos se a creado este archivo por que en un principio
se penso en realizar dos llamadas a la base de datos una por cada tipo de dato que se muestra pero trabajar el doble para obtener lo mismo me parecio demasioado
asi que e dejado el sodico a medio hacer

la idea era hacer dos llamadas a la api una para traer las localizaciones y extraer de ellas el nombre y otra para traer el episodio. pero el nombre de la localizacion ya esta en
la llamada normal asi que se a optado por no mostrar el episodio y dejar la aplicacion asi
*/
public interface ViewPersonContract {

    interface View{
        void successEpisode(Episode episode);
        void successLocation(Location location);
        void error(String error);
    }

    interface Presenter{
        void getEpisode();
        void getLocation();
    }

    interface Model{
        void getEpisodeWS(ViewPersonContract.Model.OnListEpisodeListener onViewEpisodeListener);
        interface OnListEpisodeListener {
            void onFinished(Episode episode);
            void onFailure(String Error);
        }
        void getLocationWS(ViewPersonContract.Model.OnListLocationListener onViewLocationListener);
        interface OnListLocationListener {
            void onFinished(Location location);
            void onFailure(String Error);
        }
    }


}
