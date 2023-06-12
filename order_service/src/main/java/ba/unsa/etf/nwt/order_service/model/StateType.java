package ba.unsa.etf.nwt.order_service.model;

import java.util.Arrays;

public enum StateType {
    PREGLED_NARUDZBE("Pregled narudzbe",0),
    NABAVKA_MATERIJALA("Nabavka materijala",1),
    MONTIRANJE("Montiranje",2),
    SPREMNO_ZA_ISPORUKU("Spremno za isporuku",3),
    ISPORUCENO("Isporuceno",4);
    public final String label;
    public final Integer idState;
    StateType(String label,Integer idState){
        this.label = label;
        this.idState = idState;
    }

//    public static StateType valueOfNumber(Integer number){
//        return Arrays.stream(values()).filter(stateType -> stateType.idState.equals(number)).findFirst().orElse(null);
//    }
    public static StateType valueOfLabel(String label){
        return Arrays.stream(values()).filter(stateType -> stateType.label.equals(label)).findFirst().orElse(null);
    }
}
