package stockPackage;

import java.util.ArrayList;

import productPackage.AvalClass;
import productPackage.AvalProductEnum;
import productPackage.Product;
import productPackage.SearchProdClass;
import productPackage.SearchProductEnum;
import personPackage.Provider;
import personPackage.Employee;
import personPackage.Person;

public class Stock {
    public ArrayList<Product> stock = new ArrayList<Product>();
    
     public Stock(){

            Product Obj00 = new Product("Alicate", 1, 22, 30.00f, SearchProductEnum.MuitoProcurado,AvalProductEnum.MEDIA);
            Product Obj01 = new Product("Chave De Fenda", 2, 13, 19.90f, SearchProductEnum.MuitoProcurado,AvalProductEnum.MEDIA);
            Product Obj02 = new Product("Chave De Catraca", 3, 6, 44.90f, SearchProductEnum.MuitoProcurado,AvalProductEnum.MEDIA);
            Product Obj03 = new Product("Macaco", 4, 7, 14.90f, SearchProductEnum.MuitoProcurado,AvalProductEnum.MEDIA);
            Product Obj04 = new Product("Paquímetro", 5, 13, 70.00f, SearchProductEnum.MuitoProcurado,AvalProductEnum.MEDIA);
            Product Obj05 = new Product("Torquímetro", 6, 24, 650.00f, SearchProductEnum.MuitoProcurado,AvalProductEnum.MEDIA);
      
            stock.add(Obj00);
            stock.add(Obj01);
            stock.add(Obj02);
            stock.add(Obj03);
            stock.add(Obj04);
            stock.add(Obj05);
    }

    public void stockAdd(String name, int id){

        boolean providerDisponibility = false;        
        for(int i = 0; i< Provider.getDisponibleProducts().size(); i++){

            if(Provider.ProdBD.get(i).getName().endsWith(name)){
                providerDisponibility = true;
 
                for(int j = 0; j< stock.size(); j++){

                if(stock.get(j).getName().endsWith(name)){
                    System.out.println("O produto " + name + " já está disponível no seu estoque.");
                    break;
                }
                
                else {
                    Product productAdd = new Product(name, id, 0, Employee.calculateProfitMargin(Provider.ProdBD.get(i).getPrice()));
                    stock.add(productAdd);
                    System.out.println("Produto Adicionado no Estoque");
                    break;
                }
                }
            }
        }

        if(providerDisponibility == false) {
            System.out.println("O seu fornecedor não possui o produto " +name);
            System.out.println("Lista dos produtos disponiveis no fornecedor: \n");
            Provider.getDisponibleProducts();
        }
    }

    public ArrayList getProducts(){
        return this.stock;
    }


    public void listProducts(){
        System.out.println("Produtos encontrados:\n--------------------------------------------------");
        for (Product produto : stock)
        {
            String ProdutoInfo = "";
          
            ProdutoInfo += Integer.toString(produto.getId()) + " | ";
            ProdutoInfo += produto.getName() + " | ";
            ProdutoInfo += "R$ " + Float.toString(produto.getPrice()) + " | ";
            ProdutoInfo += Integer.toString(produto.getQtd()) + "und | ";
            ProdutoInfo += SearchProdClass.ProcuraProduto2String(produto.getProductSearch()) + " | ";
            ProdutoInfo += AvalClass.DispE2Str(produto.getProductAvailability());

            System.out.println(ProdutoInfo);
        }
        System.out.println("--------------------------------------------------");
    }
    
    

    
    //Verifica se a quantidade disponível no estoque é suficiente.
    private void ChecarEstoqueProduto(Product produto){
        AvalProductEnum Disponibilidade = produto.disponibility();
        SearchProductEnum Procura = produto.getProductSearch();

        int ReporQuant = produto.recommendedQuantity - produto.getQtd();

        //Verifica se é preciso repor.
        if(PrecisaRepor(Disponibilidade, Procura))
        {
            ReporQuant = ReporQuantRecomendada(ReporQuant, Procura);

            //Repõe o estoque de acordo com as métricas de produra do produto.
         Provider provider = new Provider("CD Distribuições");
         provider.sellExistingProduct(produto, ReporQuant);
        }

    }
    
    private int ReporQuantRecomendada(int QuantRecomendada, SearchProductEnum Procura)
    {
        float ReporPerc = 0.0f;

        if(Procura == SearchProductEnum.PoucoProcurado)
        {
            ReporPerc = 0.33f;
        }
        else if(Procura == SearchProductEnum.Procurado)
        {
            ReporPerc = 0.66f;
        }
        else if(Procura == SearchProductEnum.MuitoProcurado)
        {
            ReporPerc = 1.0f;
        }

        Float ReporQuant = QuantRecomendada * ReporPerc;

        return ReporQuant.intValue();
    }

    //Verifica se é necessário repor o estoque.
    private boolean PrecisaRepor(AvalProductEnum Disponibilidade, SearchProductEnum Procura)
    {
        if(Disponibilidade == AvalProductEnum.MUITOBAIXA)
        {
            if(Procura == SearchProductEnum.PoucoProcurado)
            {
                return true;
            }
            else if(Procura == SearchProductEnum.Procurado)
            {
                return true;
            }
            else if(Procura == SearchProductEnum.MuitoProcurado)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if(Disponibilidade == AvalProductEnum.BAIXA)
        {
            if(Procura == SearchProductEnum.PoucoProcurado)
            {
                return false;
            }
            else if(Procura == SearchProductEnum.Procurado)
            {
                return true;
            }
            else if(Procura == SearchProductEnum.MuitoProcurado)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if(Disponibilidade == AvalProductEnum.MEDIA)
        {
            if(Procura == SearchProductEnum.PoucoProcurado)
            {
                return false;
            }
            else if(Procura == SearchProductEnum.Procurado)
            {
                return false;
            }
            else if(Procura == SearchProductEnum.MuitoProcurado)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if(Disponibilidade == AvalProductEnum.ALTA)
        {
            return false;
        }
        else if(Disponibilidade == AvalProductEnum.MUITOALTA)
        {
            return false;
        }
        else
        {
            return false;
        }
    }
    
}
