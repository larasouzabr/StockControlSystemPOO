package productPackage;

public class SearchProdClass {
     //Converte o valor de SearchProductEnum para string
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
 
     //Converte uma string em um valor válido de SearchProductEnum
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
