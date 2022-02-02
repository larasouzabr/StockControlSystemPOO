package personPackage;

import java.util.Scanner;
import stockPackage.Stock;

public class Employee extends Person {
    String separator = "---------------------------------------";
    Stock stockAccess = new Stock();

    /**
     * Construtor da classe Employee
     * @param name - nome do funcionário
     */
    public Employee(String name) {
        super(name);
    }

    /**
     * Lista produtos do estoque
     */
    public void productList(){
        stockAccess.listProducts();
    }

    /**
     * Encontra informações de um produto pelo id
     * @param id - id do produto
     */
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

    /**
     * Encontra informações de um produto pelo nome
     * @param name - nome do produto
     */
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

    /**
     * Recebe informações do novo produto para ser adicionado em Stock.java
     * @param estoque - variável do tipo estoque para adicionar as informações
     */
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
        stockAccess.stockAdd(nameStr, idNbr);
        } catch (Exception e) {
            System.out.println("\n\n" + e.getMessage() + "\n\n");
        }
    }



    /**
     * Recebe informações e decide se o usuário quer buscar um produto pelo name e pelo id
     * Chama os métodos findProductById(Stock) ou findProductByName(stock)
     * @param estoque - variável do tipo estoque para adicionar as informações
     */
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

    /**
     * Recebe informações do produto a ser reposto em Stock.java
     * @param estoque - variável do tipo estoque para adicionar as informações
     */
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
      stockAccess.stockReplenish(nameStr, qtdNbr);
        } catch (Exception e) {
            System.out.println("\n\n" + e.getMessage() + "\n\n");
        }
    }

    /**
     * Recebe informações do produto a ser retirado em Stock.java
     * @param estoque - variável do tipo estoque para adicionar as informações
     */
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
         stockAccess.stockShipment(nameStr, qtdNbr);
        } catch (Exception e) {
            System.out.println("\n\n" + e.getMessage() + "\n\n");
        }
    }

    /**
     * Recebe a informação do produto a ser editado em Stock.java
     * @param estoque - variável do tipo estoque para adicionar as informações
     */
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
            stockAccess.stockEditProduct(idNbr, nameStr);
        } catch (Exception e) {
            System.out.println("\n\n" + e.getMessage() + "\n\n");
        }
    }

    /**
     * Recebe a informação do produto a ser deletado em Stock.java
     * @param estoque - variável do tipo estoque para adicionar as informações
     */
    public void deleteProduct(Stock estoque) {
        System.out.println(separator+"\n");
        System.out.print("Digite o nome do produto: ");
        Scanner name = new Scanner(System.in);
        String nameStr = iniMaiuscula(name.next());
        System.out.println("\n"+separator);

        try {
            stockAccess.stockDeleteProduct(nameStr);
        } catch (Exception e) {
            System.out.println("\n\n" + e.getMessage() + "\n\n");
        }
    }

      /**
     * Retorna letra inicial da string digitada em maúscula
     * @param value - String a ser analisada
     * @return - retorna a string com a inicial maiúscula
     */
    public static String iniMaiuscula(String value) {
        String result = "";
        String[] nomes = value.split(" ");

        for (String palavra : nomes) {
            result = result + " "
            + palavra.replaceFirst(palavra.substring(0, 1), palavra.substring(0, 1).toUpperCase());
        }
        return result.trim();
    }

}
