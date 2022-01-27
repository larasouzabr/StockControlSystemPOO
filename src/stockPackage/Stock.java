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

            Product Obj00 = new Product("Alicate", 1, 22, 44.0f, SearchProductEnum.MuitoProcurado,AvalProductEnum.MEDIA);
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
        boolean disponibilityStock = true;

        for(int i = 0; i< stock.size(); i++)
            if(stock.get(i).getName().endsWith(name)){
                 System.out.println("ATENÇÃO: Produto já existente no estoque\n");
                 disponibilityStock = false;
                 break;
            }
        

        if(disponibilityStock == true){
            boolean disponibilityProvider = false;

            for(int j = 0; j< Provider.ProdBD.size(); j++){

                if(Provider.ProdBD.get(j).getName().endsWith(name)){

                    Product productAdd = new Product(name, id, 0, Provider.ProdBD.get(j).getPrice(), SearchProductEnum.NãoProcurado, AvalProductEnum.INDISPONIVEL);
                    stock.add(productAdd);
                    System.out.println("Produto Adicionado no Estoque\n Caso queira adicionar unidades desse produto digite 'reporproduto'\n");
                    disponibilityProvider = true;
                    break;
            }
        }

        if(disponibilityProvider == false) System.out.println("ATENÇÃO: Esse produto não é disponibilizado pelo fornecedor\n");
        }
    }
    public void stockShipment(String name, int quantity){
        
        boolean disponibilityStock = false;

        for(int i = 0; i< stock.size(); i++){

            if(stock.get(i).getName().startsWith(name)){

                if(stock.get(i).getQtd() >= quantity){
                    stock.get(i).setQtd(stock.get(i).getQtd() - quantity);
                    System.out.println("Você retirou " + quantity+ " unidades de " + name + " do estoque e agora possui " + stock.get(i).getQtd() + " disponíveis\n");
                    disponibilityStock = true;
                    break;

                }
                else {
                    System.out.println("ATENÇÃO: Quantidade máxima do produto " + name +" presente no estoque é " + stock.get(i).getQtd() + "\n");
                    disponibilityStock = true;
                    break;
                }

            }

        }

        if(disponibilityStock == false) System.out.println("ATENÇÃO: Produto não existe no estoque\n");
    }

    public void stockReplanish(String name, int quantity){

        boolean disponibilityProvider = false;

        for(int j = 0; j< Provider.ProdBD.size(); j++){
            if(Provider.ProdBD.get(j).getName().startsWith(name)){
                if(Provider.ProdBD.get(j).getQtd() >= quantity){
                    Provider.ProdBD.get(j).setQtd(Provider.ProdBD.get(j).getQtd() - quantity);
                    disponibilityProvider = true;
                    break;
                }

                else {
                    System.out.println("ATENÇÃO: O número máximo de unidades que o fornecedor possui é " + Provider.ProdBD.get(j).getQtd()+"\n");
                    break;
                }
            }
        }

        if(disponibilityProvider == true){
        for(int i = 0; i< stock.size(); i++){
            if(stock.get(i).getName().startsWith(name)){
                stock.get(i).setQtd(stock.get(i).getQtd() + quantity);
                System.out.println("O produto " + stock.get(i).getName() + " foi reabastecido com " + quantity + " unidades e agora possui " + stock.get(i).getQtd()+"\n");
                break;
            }
        }

        } else System.out.println("ATENÇÃO: Produto não existe\n");

    }

    public void stockDeleteProduct(String name){

        boolean existInStock = false;

        for(int i = 0; i< stock.size(); i++){

            if(stock.get(i).getName().endsWith(name)){
                existInStock = true;
                System.out.println("O produto " + stock.get(i).getName() + " e todas as suas informações foram deletadas do estoque\n");
                stock.remove(i);
            }
        }

        if(existInStock == false) System.out.println("ATENÇÃO: O produto " + name + " não existe no seu estoque\n");
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
