package com.bta.retotecnico.domain.util;

public class Constans {

    public class URLS {
        public static final String URL = "https://firebasestorage.googleapis.com/";
        public static final String CONSULTA_ESTADO_JUEGO = "v0/b/autoservicio-87532.appspot.com/o/estadosjuego.json?alt=media&token=b1fc6df4-75af-4e35-ac50-87618d816b76";
    }

    public class ERROR {
        public static final String ERROR = "ERROR INESPERADO";
        public static final String FAILURE = "FAILURE";
        public static final String SUCCESS = "SUCCESS";
    }

    public static class ESTADOS_JUEGO {
        public static final Integer CARTAS_VOLTEADAS = 1;
        public static final Integer PARES_ENCONTRADOS = 2;
        public static final Integer JUEGO_TERMINADO = 3;
    }

    public static class CARTAS{
        public static final String DEFAULT = "https://firebasestorage.googleapis.com/v0/b/autoservicio-87532.appspot.com/o/cartavolteada.png?alt=media&token=8dee76cb-da49-4a27-8c35-defbd4674425";
        public static final String NAIPE_NEGRO = "https://firebasestorage.googleapis.com/v0/b/autoservicio-87532.appspot.com/o/naipenegro.jpg?alt=media&token=8766f6eb-305c-4d53-b09a-119866c7c8a7";
        public static final String NAIPE_ROJO = "https://firebasestorage.googleapis.com/v0/b/autoservicio-87532.appspot.com/o/naiperojo.jpg?alt=media&token=05619dd6-2bbe-4386-890d-4040cb494acd";
    }

}
