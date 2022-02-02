package productPackage;

public class SearchProdClass {
     
     /**
      * Converte o valor de SearchProductEnum.java para string
      * @param ProcuraEnum - Enum que será analisado
      * @return - Retorna em forma de string aquilo que estiver escrito no enum
      */
     public static String ProcuraProduto2String(SearchProductEnum ProcuraEnum)
     {
         switch (ProcuraEnum)
         {
             case PoucoProcurado:
                 return "POUCO PROCURADO";
             case Procurado:
                 return "PROCURADO";
             case MuitoProcurado:
                 return "MUITO PROCURADO";
             default:
                 return "NÃO PROCURADO";
         }
     }

     /**
      * Converte uma string em um valor válido de SearchProductEnum.java
      * @param procura - string a ser analisada
      * @return - valor do enum que a string corresponder
      */
     public static SearchProductEnum ProcuraProduto2String(String procura)
     {
         if(procura.equals("POUCO PROCURADO"))
         {
             return SearchProductEnum.PoucoProcurado;
         }
         else if(procura.equals("PROCURADO"))
         {
             return SearchProductEnum.Procurado;
         }
         else if(procura.equals("MUITO PROCURADO"))
         {
             return SearchProductEnum.MuitoProcurado;
         }
         else
         {
             return SearchProductEnum.NãoProcurado;
         }
     }
}
