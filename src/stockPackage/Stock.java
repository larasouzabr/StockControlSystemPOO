package stockPackage;

import java.util.ArrayList;
import java.util.Locale;
import productPackage.AvalClass;
import productPackage.AvalProductEnum;
import productPackage.Product;
import productPackage.SearchProdClass;
import productPackage.SearchProductEnum;
import personPackage.Provider;
import personPackage.Employee;
import java.text.NumberFormat;

public class Stock {
    public ArrayList<Product> stock = new ArrayList<Product>();
    Locale localeBR = new Locale("pt", "BR");

    public void stockAdd(String name, int id) {
        boolean disponibilityStock = true;

        for (int i = 0; i < stock.size(); i++)
            if (stock.get(i).getName().endsWith(name)) {
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

                if (Provider.ProdBD.get(j).getName().endsWith(name)) {

                    Product productAdd = new Product(name, id, 0,
                            Employee.calculateProfitMargin(Provider.ProdBD.get(j).getPrice()),
                            SearchProductEnum.NãoProcurado, AvalProductEnum.INDISPONIVEL);

                    stock.add(productAdd);
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

    public void editProduct(int id, String name) {

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

    public void stockReplanish(String name, int quantity) {

        if (Provider.sellProduct(name, quantity)) {
            for (int i = 0; i < stock.size(); i++) {
                if (stock.get(i).getName().startsWith(name)) {
                    stock.get(i).setQtd(stock.get(i).getQtd() + quantity);
                    System.out.println("O produto " + stock.get(i).getName() + " foi reabastecido com " + quantity
                            + " unidades e agora possui " + stock.get(i).getQtd() + "\n");
                    stock.get(i).disponibility(stock.get(i));
                    break;
                }
            }
        }

    }

    public void stockDeleteProduct(String name) {

        boolean existInStock = false;

        for (int i = 0; i < stock.size(); i++) {

            if (stock.get(i).getName().endsWith(name)) {
                existInStock = true;
                System.out.println("O produto " + stock.get(i).getName()
                        + " e todas as suas informações foram deletadas do estoque\n");
                stock.remove(i);
            }
        }

        if (existInStock == false)
            System.out.println("ATENÇÃO: O produto " + name + " não existe no seu estoque\n");
    }


    public void listProducts() {
        if (stock.size() == 0) {
            System.out.println("---------------------");
            System.out.println("NÃO HÁ PRODUTOS NO ESTOQUE");
            System.out.println("---------------------");

        } else {
            NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);

            System.out.println("Produtos encontrados:\n--------------------------------------------------");

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
            System.out.println("--------------------------------------------------");
        }
    }
}
