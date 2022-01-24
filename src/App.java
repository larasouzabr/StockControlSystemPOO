import personPackage.Provider;

public class App {    
    public static void main(String[] args) throws Exception {

        Provider provider = new Provider("robertinho");

        provider.ProviderObj();
        System.out.println(provider.ProdBD);
    }
}
