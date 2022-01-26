import personPackage.Employee;
import personPackage.Provider;
import productPackage.Product;
import stockPackage.Stock;
import personPackage.Employee;
public class App {    
    public static void main(String[] args) throws Exception {

        Stock stock = new Stock();
        Employee employee = new Employee("Roberto");
        Provider provider = new Provider("Mauro");
        
        provider.ProviderObj();
        employee.createProduct(iniMaiuscula("chave inglesa"), 15);
        employee.createProduct(iniMaiuscula("pneu"), 8);
        provider.getDisponibleProducts();
        stock.listProducts();
        employee.findProductByName("Pneu");
        employee.productShipment("Alicate", 2);
        employee.productReplenish("Alicate", 2);
        employee.productShipment("Alicate", 10);
        employee.findProductByName("Alicate");

       stock .listProducts();
    }
    
    public static String iniMaiuscula(String value) {
        String result = "";
        String[] nomes = value.split(" ");
        
        for(String palavra : nomes){
            result = result + " " + palavra.replaceFirst(palavra.substring(0, 1), palavra.substring(0, 1).toUpperCase());
        }
        return result.trim();
    }

}
