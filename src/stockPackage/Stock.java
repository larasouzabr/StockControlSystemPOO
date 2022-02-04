package stockPackage;

import java.util.ArrayList;
import java.util.Locale;
import productPackage.AvalClass;
import productPackage.AvalProductEnum;
import productPackage.Product;
import productPackage.SearchProdClass;
import productPackage.SearchProductEnum;
import personPackage.Provider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.rmi.server.ExportException;
import java.text.NumberFormat;


public class Stock implements Serializable {
    public static ArrayList<Product> stock = new ArrayList<Product>();
    Locale localeBR = new Locale("pt", "BR");



        /**
         * Método para escrever os dados da aplicação em um arquivo
         */
        static void writeList() {
            try (
                FileOutputStream productFile = new FileOutputStream("product.ser");
                ObjectOutputStream productStream = new ObjectOutputStream(productFile);
                )
            {
                for(Product item : stock) {
                    productStream.writeObject(item);
                }
            }
            catch(IOException e) {
                System.out.println("There was a problem writing the file");
            }
        }
    
         /**
          * Método para ler os dados que forem sendo registrados na aplicação
          */
        public static void readList() {
            Product tempProduct;
            boolean endOfFile = false;
    
            try (
                    FileInputStream productFile = new FileInputStream("product.ser");
                    ObjectInputStream productStream = new ObjectInputStream(productFile);
                )
            {
                while(endOfFile == false) { 
                    try {
                        tempProduct = (Product) productStream.readObject();
                        stock.add(tempProduct);
                    }
                    catch(ExportException e) {
                        endOfFile = true;
                    }                
                }
            }
            catch(FileNotFoundException e) {
                System.out.println("\nNo previous file was read");
            }
            catch(ClassNotFoundException e) { 
                System.out.println("\nTrying to read an object of an unknown class");
            }
            catch(StreamCorruptedException e) { 
                System.out.println("\nUnreadable file format");
            }
            catch(IOException e) {
                System.out.println("\nerror: There was a problem reading the file");
            }
        }

    /**
     * Adiciona um novo produto no estoque
     * Verifica se o produto está sendo disponibilizado pelo fornecedor em Provider.java
     * @param name - nome do produto que será adicionado
     * @param id - id do produto que será adicionado
     */
    public void stockAdd(String name, int id) {
        boolean disponibilityStock = true;

        for (int i = 0; i < stock.size(); i++)
            if (stock.get(i).getName().startsWith(name)) {
                System.out.println("ATENÇÃO: Produto já existente no estoque\n");
                disponibilityStock = false;
                break;
            }

        for (int i = 0; i < stock.size(); i++)
            if (stock.get(i).getId() == id) {
                System.out.println("ATENÇÃO: Já existe um produto com esse ID no estoque\n");
                disponibilityStock = false;
                break;
            }

        if (disponibilityStock == true) {
            boolean disponibilityProvider = false;
            for (int j = 0; j < Provider.ProdBD.size(); j++) {

                if (Provider.ProdBD.get(j).getName().startsWith(name)) {
                    Product productAdd = new Product(name, id, 0,
                            Product.calculateProfitMargin(Provider.ProdBD.get(j).getPrice()),
                            SearchProductEnum.NãoProcurado, AvalProductEnum.INDISPONIVEL);

                    stock.add(productAdd);
                    writeList();
                    if(stock.size() == 0){
                        readList();
                    }    
                    System.out.println(
                            "Produto Adicionado no Estoque\n Caso queira adicionar unidades desse produto digite 5\n");
                    disponibilityProvider = true;
                    break;
                }
            }

            if (disponibilityProvider == false)
                System.out.println(
                        "ATENÇÃO: Esse produto não é disponibilizado pelo fornecedor\nCaso queira ver os disponiveis digite 7");
        }
    }


    /**
     * Retira unidades de um produto no estoque
     * Verifica se o produto existe no estoque
     * Verifica se a quantidade de unidades que serão retiradas condiz com a quantidade de unidades existentes
     * @param name - Nome do produto
     * @param quantity - Quantidade de unidades que serão retiradas do estoque
     */
    public void stockShipment(String name, int quantity) {

        boolean disponibilityStock = false;

        for (int i = 0; i < stock.size(); i++) {

            if (stock.get(i).getName().startsWith(name)) {

                if (stock.get(i).getQtd() >= quantity) {
                    stock.get(i).setQtd(stock.get(i).getQtd() - quantity);
                    stock.get(i).calculatePdctSearch(stock.get(i), quantity);
                    System.out.println("Você retirou " + quantity + " unidades de " + name
                            + " do estoque e agora possui " + stock.get(i).getQtd() + " disponíveis\n");
                    disponibilityStock = true;
                    break;

                } else {
                    System.out.println("ATENÇÃO: Quantidade máxima do produto " + name + " presente no estoque é "
                            + stock.get(i).getQtd() + "\n");
                    disponibilityStock = true;
                    break;
                }

            }

        }

        if (disponibilityStock == false)
            System.out.println("ATENÇÃO: Produto não existe no estoque\n");
    }

    /**
     * Edita o ID de um produto
     * Verifica se o ID digitado já existe
     * @param id - Novo id do produto
     * @param name - Nome do produto
     */

    public void stockEditProduct(int id, String name) {

        boolean idExist = false;

        for (int i = 0; i < stock.size(); i++) {

            if (stock.get(i).getId() == id) {
                System.out.println("ATENÇÃO: Esse ID já existe em outro produto");
                idExist = true;
                break;
            }

        }

        if (idExist == false) {
            for (int i = 0; i < stock.size(); i++) {
                if (stock.get(i).getName().startsWith(name)) {
                    stock.get(i).setId(id);
                    System.out.println("ID modificado com sucesso! Para ver as informações desse produto digite 3");
                }
            }
        }
    }


    /**
     * Repõe unidades de um produto existente no estoque
     * Verifica se o produto existe no estoque
     * Verifica se o fornecedor possui unidades suficientes em Provider.java
     * @param name - Nome do produto
     * @param quantity - Quantidade de unidades a serem repostas
     */
    public void stockReplenish(String name, int quantity) {

        boolean stockDisponible = false;

        if (Provider.sellProduct(name, quantity)) {
            for (int i = 0; i < stock.size(); i++) {
                if (stock.get(i).getName().startsWith(name)) {
                    stock.get(i).setQtd(stock.get(i).getQtd() + quantity);
                    System.out.println("O produto " + stock.get(i).getName() + " foi reabastecido com " + quantity
                            + " unidades e agora possui " + stock.get(i).getQtd() + "\n");
                    stock.get(i).disponibility(stock.get(i));
                    stockDisponible = true;
                    break;
                }
            }

        if(stockDisponible == false)  
            System.out.println("ATENÇÃO: O produto " + name + " não existe no seu estoque\n");

        }

    }

    /**
     * Deleta um produto do estoque
     * Verifica se o produto existe no estoque
     * @param name - Nome do produto
     */
    public void stockDeleteProduct(String name) {

        boolean existInStock = false;

        for (int i = 0; i < stock.size(); i++) {

            if (stock.get(i).getName().startsWith(name)) {
                existInStock = true;
                System.out.println("O produto " + stock.get(i).getName()
                        + " e todas as suas informações foram deletadas do estoque\n");
                stock.remove(i);
            }
        }

        if (existInStock == false)
            System.out.println("ATENÇÃO: O produto " + name + " não existe no seu estoque\n");
    }

    /**
     * Lista os produtos do estoque
     */
    public void listProducts() {
        if (stock.size() == 0) {
            System.out.println("---------------------");
            System.out.println("NÃO HÁ PRODUTOS NO ESTOQUE");
            System.out.println("---------------------");

        } else {

            NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
            System.out.println("\nProdutos encontrados:\nID | NOME |  VALOR  |  UND  |  PROCURA  |  DISPONIBILIDADE\n---------------------------------------------------------");
                
            for (Product produto : stock) {
                String ProdutoInfo = "";
                ProdutoInfo += Integer.toString(produto.getId()) + " | ";
                ProdutoInfo += produto.getName() + " | ";
                ProdutoInfo += dinheiro.format(produto.getPrice()) + " | ";
                ProdutoInfo += Integer.toString(produto.getQtd()) + "und | ";
                ProdutoInfo += SearchProdClass.ProcuraProduto2String(produto.getProductSearch()) + " | ";
                ProdutoInfo += AvalClass.DispE2Str(produto.getProductAvailability());

                System.out.println(ProdutoInfo);
            }
            System.out.println("---------------------------------------------------------");
        }
    }
}
