import personPackage.Provider;
import productPackage.Product;
import stockPackage.Stock;

public class App {    
    public static void main(String[] args) throws Exception {

        Stock stock = new Stock();
        
       stock.listProducts();
    }
}
