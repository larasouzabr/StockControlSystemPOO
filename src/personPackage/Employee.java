package personPackage;

import productPackage.Product;
import stockPackage.Stock;

public class Employee extends Person{

    
    Stock stockAccess = new Stock();
    
    public Employee(String name){
        super(name);
    }
    
    public void createProduct(String name, int id){
        stockAccess.stockAdd(name, id);

    }
    public void productShipment(String name, int qtd){
        stockAccess.stockShipment(name, qtd);
    }
    public void productReplenish(String name, int qtdToBeReplenish){
        stockAccess.stockReplanish(name, qtdToBeReplenish);

    }
    void editProduct(int id, String name ){
        
    }
    void deleteProduct(String name){

    }
    public void findProductById(int id){
        System.out.println("Pesquisando ID de produto: " + id);
        boolean ProdutoEncontrado = false;

        for(int i = 0; i < stockAccess.stock.size(); i++)
        {
            if(stockAccess.stock.get(i).getId() == id)
            {

                System.out.println(stockAccess.stock.get(i).toString());
                ProdutoEncontrado = true;
                break;
            }
        }

        if(ProdutoEncontrado == false)
        {
            System.out.println("AVISO: Produto não encontrado!");
        }
    }
    public void findProductByName(String name){

         System.out.println("Pesquisando Nome de produto: " + name);
        boolean ProdutoEncontrado = false;

        for(int i = 0; i < stockAccess.stock.size(); i++)
        {
            if(stockAccess.stock.get(i).getName().endsWith(name))
            {

                System.out.println(stockAccess.stock.get(i).toString());
                ProdutoEncontrado = true;
                break;
            }
        }

        if(ProdutoEncontrado == false)
        {
            System.out.println("AVISO: Produto não encontrado!");
        }

    }
    public static float calculateProfitMargin(float suggestedPrice){
        return suggestedPrice *  1.15f;
    }
}
