package productPackage;

import java.util.Locale;
import java.io.Serializable;
import java.text.NumberFormat;

public class Product implements Comparable<Product>,Serializable{

    private String name;
    private int quantity;
    private int id;
    private float price;
    private SearchProductEnum productSearch;
    private AvalProductEnum productAvailability;
    public int recommendedQuantity = 100;
    Locale localeBR = new Locale("pt", "BR");


    /**
     * Primeiro construtor da classe Product, ele será usado pelo fornecedor na classe Provider.java
     * 
     * @param name                - Nome do Produto
     * @param id                  - Código do Produto
     * @param quantity            - Quantidade disponível no estoque
     * @param price               - Preço do produto
     */
    public Product(String name, int id, int quantity, float price) {
        this.name = name;
        this.quantity = quantity;
        this.id = id;
        this.price = price;
    }

    /**
     * Segundo construtor da classe Product, ele será usado no estoque na classe Stock.java
     * 
     * @param name                - Nome do Produto
     * @param id                  - Código do Produto
     * @param quantity            - Quantidade disponível no estoque
     * @param price               - Preço do produto
     * @param productSearch       - a popularidade do produto
     * @param productAvailability - a disponibilidade do produto
     */
    public Product(String name, int id, int quantity, float price, SearchProductEnum searchEnum,
            AvalProductEnum availability) {

        this.name = name;
        this.quantity = quantity;
        this.id = id;
        this.price = price;
        this.productSearch = searchEnum;
        this.productAvailability = availability;
    }

    @Override public int compareTo(Product product){

        return Integer.compare(this.quantity, product.getQtd());
    }

    /**
     * Calcula a Procura do produto utilizando o enum SearchProductEnum.java
     * Calcula o percentual de produtos que foram retirados do estoque
     * Classifica a procura do produto de acordo com o percentual
     * Utiliza os setters para modificar a procura cada vez que o método for chamado
     * @param product - Produto em questão
     * @param soldQtd - Quantidade de unidades do produto que foram retiradas do estoque
     */

    public void calculatePdctSearch(Product product, int soldQtd) {
        float totalQtd = soldQtd + product.getQtd();
        float percComprada = soldQtd / totalQtd;
        if (percComprada == 0) {
            product.setProductSearch(SearchProductEnum.NãoProcurado);
        }

        else if (percComprada > 0 && percComprada <= 0.33f) {
            product.setProductSearch(SearchProductEnum.PoucoProcurado);
        }

        else if (percComprada > 0.33f && percComprada <= 0.66f) {
            product.setProductSearch(SearchProductEnum.Procurado);
        }

        else if (percComprada > 0.66f && percComprada <= 1.0f) {
            product.setProductSearch(SearchProductEnum.MuitoProcurado);
        }
    }

    /**
     * Calcula a disponibilidade do produto utilizando o enum AvalProductEnum.java
     * Calcula o percentual de unidades do produtos existentes no estoque
     * Classifica a disponibilidade do produto de acordo com o percentual
     * @param product - Produto em questão
     */
    public void disponibility(Product product) {

        float quantity = product.getQtd();
        float percentProd = quantity / this.recommendedQuantity;
        if (percentProd == 0) // Sem estoque.
        {
            product.setProductAvailability(AvalProductEnum.INDISPONIVEL);
        } else if (percentProd <= 0.1) // Estoque muito baixo.
        {
            product.setProductAvailability(AvalProductEnum.MUITOBAIXA);
        } else if (percentProd <= 0.25 && percentProd > 0.1) // Estoque baixo
        {
            product.setProductAvailability(AvalProductEnum.BAIXA);
        } else if (percentProd <= 0.5 && percentProd > 0.25) // Estoque médio
        {
            product.setProductAvailability(AvalProductEnum.MEDIA);
        } else if (percentProd <= 0.75 && percentProd > 0.5) // Estoque alto
        {
            product.setProductAvailability(AvalProductEnum.ALTA);
        } else if (percentProd > 0.75) // Estoque cheio ou muito alto
        {
            product.setProductAvailability(AvalProductEnum.MUITOALTA);
        }
    }

    /**
     * Calcula a margem de lucro no preço de um produto individual
     * @param suggestedPrice - Preço do produto sugerido pelo fornecedor em Provider.java
     * @return - retorna um novo preço com 15% de lucro aplicado em cima do valor inicial
     */
    public static float calculateProfitMargin(float suggestedPrice) {
        return suggestedPrice * 1.15f;
    }

    // GETTERS
    public int getId() {
        return this.id;
    }

    public int getQtd() {
        return this.quantity;
    }

    public float getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }

    public SearchProductEnum getProductSearch() {
        return this.productSearch;
    }

    public AvalProductEnum getProductAvailability() {
        return this.productAvailability;
    }

    // SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setQtd(int qtd) {
        this.quantity = qtd;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductSearch(SearchProductEnum procuraEnum) {
        this.productSearch = procuraEnum;
    }

    public void setProductAvailability(AvalProductEnum avalEnum) {
        this.productAvailability = avalEnum;
    }

    @Override
    /**
     * Formatando a saída.
     */
    public String toString() {
        
        NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);

        String separator = "-------------------------------";
        String productInfo = "\nProduto: " + this.getName() + "\n" + "Código: " + this.getId() + "\n"
                + "Valor Unitário: " + dinheiro.format(this.getPrice()) + "\n" + "Quantidade disponível: " + this.getQtd() + "\n";
        if (this.getProductSearch() == null || this.getProductAvailability() == null) {
            return productInfo + separator;
        } else
            return productInfo + "Popularidade: " + SearchProdClass.ProcuraProduto2String(this.getProductSearch())
                    + "\n" + "Disponibilidade no estoque: " + AvalClass.DispE2Str(this.getProductAvailability()) + "\n"
                    + separator;
    }

}
