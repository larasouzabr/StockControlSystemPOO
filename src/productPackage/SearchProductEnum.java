package productPackage;

public enum SearchProductEnum{
    NãoProcurado,          //x = 0.
    PoucoProcurado,        //0 <= x <= 33% é comprado.
    Procurado,             //33% < x <= 66% é comprado.
    MuitoProcurado         //66% < x <= 100% é comprado.
}

