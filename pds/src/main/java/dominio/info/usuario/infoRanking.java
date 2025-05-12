package dominio.info.usuario;

import java.util.Map;

public class infoRanking {

    private int numero;
    private Map<String, String> ranking;

    public infoRanking(Map<String, String> ranking) {
        this.ranking = ranking;
        //this.numero = numero;
    }

    public Map<String, String> getRanking() {
        return ranking;
    }

}
