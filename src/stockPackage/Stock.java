package stockPackage;

import java.util.ArrayList;

import productPackage.AvalClass;
import productPackage.AvalProductEnum;
import productPackage.Product;
import productPackage.SearchProdClass;
import productPackage.SearchProductEnum;
import personPackage.Provider;

public class Stock {
    public ArrayList<Product> stock = new ArrayList<Product>();
    
     public Stock(){

            Product Obj00 = new Product("Alicate", 1, 22, 30.00f, SearchProductEnum.MuitoProcurado,AvalProductEnum.MEDIA);
            Product Obj01 = new Product("Chave de Fenda", 2, 13, 19.90f);
            Product Obj02 = new Product("Chave de Catraca", 3, 6, 44.90f);
            Product Obj03 = new Product("Macaco", 4, 7, 14.90f);
            Product Obj04 = new Product("Paquímetro", 5, 13, 70.00f);
            Product Obj05 = new Product("Torquímetro", 6, 24, 650.00f);
            Product Obj06 = new Product("Pneu", 7, 42, 129.90f);
            Product Obj07 = new Product("Painel Porta Ferramentas", 8, 37, 130.00f);
            Product Obj08 = new Product("Bateria de Carro", 9, 55, 199.90f);
            Product Obj09 = new Product("Chave Estrela", 10, 4, 260.00f);
            Product Obj10 = new Product("Bandeja Coletora Oleo", 11, 120, 200.00f);
            Product Obj11 = new Product("Limpador de Vidro", 12, 40, 49.90f);
            Product Obj12 = new Product("Calibrador Digital", 13, 55, 280.00f);
            Product Obj13 = new Product("Torno de Bancada", 14, 43, 520.00f);
            Product Obj14 = new Product("Tapete para Carro", 15, 77, 129.90f);
            Product Obj15 = new Product("Carrinho para Ferramentas",16 , 75, 1049.00f);
            Product Obj16 = new Product("Kit de Limpeza", 17, 102, 59.90f);
            Product Obj17 = new Product("Encolhedor de Molas", 18, 33, 27.00f);
            Product Obj18 = new Product("Densimetro", 19, 26, 50.00f);
            Product Obj19 = new Product("Óleo", 20, 78, 99.90f);
            Product Obj20  = new Product("Bateria de Moto", 21, 88, 119.90f);
      
            stock.add(0, Obj00);
            stock.add(1, Obj01);
            stock.add(2, Obj02);
            stock.add(3, Obj03);
            stock.add(4, Obj04);
            stock.add(5, Obj05);
            stock.add(6, Obj06);
            stock.add(7, Obj07);
            stock.add(8, Obj08);
            stock.add(9, Obj09);
            stock.add(10, Obj10);
            stock.add(11, Obj11);
            stock.add(12, Obj12);
            stock.add(13, Obj13);
            stock.add(14, Obj14);
            stock.add(15, Obj15);
            stock.add(16, Obj16);
            stock.add(17, Obj17);
            stock.add(18, Obj18);
            stock.add(19, Obj19);     
            stock.add(20, Obj20);   
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
