package productPackage;

public class AvalClass {
    // Converte o valor de AvalProductEnum para string
    public static String DispE2Str(AvalProductEnum DispProdEnum) {
        switch (DispProdEnum) {
            case MUITOBAIXA:
                return "MUITO BAIXA";
            case BAIXA:
                return "BAIXA";
            case MEDIA:
                return "MEDIA";
            case ALTA:
                return "ALTA";
            case MUITOALTA:
                return "MUITO ALTA";
            default:
                return "INDISPONIVEL";
        }
    }

    // Converte uma string em um valor válido de AvalProductEnum
    public static AvalProductEnum available2String(String available) {
        if (available.equals("MUITO BAIXA")) {
            return AvalProductEnum.MUITOBAIXA;
        }
        if (available.equals("BAIXA")) {
            return AvalProductEnum.BAIXA;
        } else if (available.equals("MEDIA")) {
            return AvalProductEnum.MEDIA;
        } else if (available.equals("ALTA")) {
            return AvalProductEnum.ALTA;
        } else if (available.equals("MUITO ALTA")) {
            return AvalProductEnum.MUITOALTA;
        } else {
            return AvalProductEnum.INDISPONIVEL;
        }
    }

}
