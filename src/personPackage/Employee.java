package personPackage;

import java.util.Scanner;
import stockPackage.Stock;

public class Employee extends Person {
    String separator = "---------------------------------------";
    Stock stockAccess = new Stock();

    public Employee(String name) {
        super(name);
    }

    public void createProduct(String name, int id) {
        stockAccess.stockAdd(name, id);
    }

    public void productShipment(String name, int qtd) {
        stockAccess.stockShipment(name, qtd);
    }

    public void productReplenish(String name, int qtdToBeReplenish) {
        stockAccess.stockReplanish(name, qtdToBeReplenish);
    }

    public void productList(){
        stockAccess.listProducts();
    }

    public void editProduct(int id, String name) {
        stockAccess.editProduct(id, name);
    }

    public void deleteProduct(String name) {
        stockAccess.stockDeleteProduct(name);
    }

    public void findProductById(int id) {
        System.out.println("Pesquisando ID de produto: " + id);
        boolean ProdutoEncontrado = false;

        for (int i = 0; i < stockAccess.stock.size(); i++) {
            if (stockAccess.stock.get(i).getId() == id) {

                System.out.println(stockAccess.stock.get(i).toString());
                ProdutoEncontrado = true;
                break;
            }
        }

        if (ProdutoEncontrado == false) {
            System.out.println("AVISO: Produto não encontrado!");
        }
    }

    public void findProductByName(String name) {

        System.out.println("Pesquisando.... \nNome do produto: " + name);
        boolean ProdutoEncontrado = false;

        for (int i = 0; i < stockAccess.stock.size(); i++) {
            if (stockAccess.stock.get(i).getName().endsWith(name)) {

                System.out.println(stockAccess.stock.get(i).toString());
                ProdutoEncontrado = true;
                break;
            }
        }

        if (ProdutoEncontrado == false) {
            System.out.println("AVISO: Produto não encontrado!");
        }

    }

    public static float calculateProfitMargin(float suggestedPrice) {
        return suggestedPrice * 1.15f;
    }


    public void createProduct(Stock estoque) {
        System.out.println(separator+"\n");
        System.out.print("Digite o nome do produto: ");
        Scanner name = new Scanner(System.in);
        String nameStr = iniMaiuscula(name.next());
        System.out.println(separator);
        System.out.print("Digite o código do produto: ");
        Scanner id = new Scanner(System.in);
        int idNbr = Integer.parseInt(id.next());
        System.out.println("\n"+separator);

        try {
            createProduct(nameStr, idNbr);
        } catch (Exception e) {
            System.out.println("\n\n" + e.getMessage() + "\n\n");
        }
    }




    public void findProduct(Stock estoque) {
        System.out.print("Digite um numero para entrar com um nome ou id de um produto:\n(1) - id\n(2) - nome   ");
        Scanner escolha = new Scanner(System.in);
        int escolhaStr = Integer.parseInt(escolha.next());
        if (escolhaStr == 1) {
            System.out.println("\n"+separator);
            System.out.print("Insira o id de um produto: ");
            Scanner id = new Scanner(System.in);
            String idStr = id.next();
            try {
                int ID_ProdInt = Integer.parseInt(idStr);

                findProductById(ID_ProdInt);
            } catch (Exception e) {
                System.out.println("\n\n" + e.getMessage() + "\n\n");
            }
        } else if (escolhaStr == 2) {
            System.out.println("\n"+separator);

            System.out.print("Insira o nome de um produto: ");
            System.out.println(separator+"\n");

            Scanner name = new Scanner(System.in);
            String nameStr = iniMaiuscula(name.next());
            try {
                findProductByName(nameStr);
            } catch (Exception e) {
                System.out.println("\n\n" + e.getMessage() + "\n\n");
            }
        }
    }

    public void productReplenish(Stock estoque) {
        System.out.println(separator);
        System.out.print("Digite o nome do produto: ");
        Scanner name = new Scanner(System.in);
        String nameStr = iniMaiuscula(name.next());
        System.out.println("\n"+separator);
        System.out.print("Digite a quantidade do produto a ser reposta: ");
        Scanner qtd = new Scanner(System.in);
        int qtdNbr = Integer.parseInt(qtd.next());
        System.out.println("\n"+separator);
        try {
        productReplenish(nameStr, qtdNbr);
        } catch (Exception e) {
            System.out.println("\n\n" + e.getMessage() + "\n\n");
        }
    }

    public static String iniMaiuscula(String value) {
        String result = "";
        String[] nomes = value.split(" ");

        for (String palavra : nomes) {
            result = result + " "
            + palavra.replaceFirst(palavra.substring(0, 1), palavra.substring(0, 1).toUpperCase());
        }
        return result.trim();
    }

    public void productShipment(Stock estoque) {
        System.out.println(separator+"\n");
        System.out.print("Digite o nome do produto: ");
        Scanner name = new Scanner(System.in);
        String nameStr = iniMaiuscula(name.next());
        System.out.println(separator);
        System.out.print("Digite a quantidade do produto a sair do estoque: ");
        Scanner qtd = new Scanner(System.in);
        int qtdNbr = Integer.parseInt(qtd.next());
        System.out.println("\n"+separator);

        try {
            productShipment(nameStr, qtdNbr);
        } catch (Exception e) {
            System.out.println("\n\n" + e.getMessage() + "\n\n");
        }
    }

    public void editProduct(Stock estoque) {
        System.out.println(separator+"\n");
        System.out.print("Digite o nome do produto que deseja editar: ");
        Scanner name = new Scanner(System.in);
        String nameStr = iniMaiuscula(name.next());
        System.out.println(separator);
        System.out.print("Digite o novo ID do produto: ");
        Scanner id = new Scanner(System.in);
        int idNbr = Integer.parseInt(id.next());
        System.out.println("\n"+separator);

        try {
            editProduct(idNbr, nameStr);
        } catch (Exception e) {
            System.out.println("\n\n" + e.getMessage() + "\n\n");
        }
    }

    public void deleteProduct(Stock estoque) {
        System.out.println(separator+"\n");
        System.out.print("Digite o nome do produto: ");
        Scanner name = new Scanner(System.in);
        String nameStr = iniMaiuscula(name.next());
        System.out.println("\n"+separator);

        try {
            deleteProduct(nameStr);
        } catch (Exception e) {
            System.out.println("\n\n" + e.getMessage() + "\n\n");
        }
    }

}
