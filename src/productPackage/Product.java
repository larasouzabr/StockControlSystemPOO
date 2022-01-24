package productPackage;


public class Product {

    private String name;
    private int quantity;
    private int id;
    private float price;
    private SearchProductEnum procuraDoProduto;
    /**
     * Construtor da classe Product
     * @param name - Nome do Produto
     * @param id - Código do Produto
     * @param quantity - Quantidade disponível no estoque
     * @param price - Preço do produto
     * @param procuraDoProduto - a popularidade do produto
     */

    public Product(String name, int id, int quantity, float price){
        this.name = name;
        this.quantity = quantity;
        this.id = id;
        this.price = price;
    }
    public Product(String name, int id, int quantity, float price, SearchProductEnum ProcuraEnum){
        this.name = name;
        this.quantity = quantity;
        this.id = id;
        this.price = price;
        this.procuraDoProduto = ProcuraEnum;
    }
    
    private void calculatePdctSearch(Product product, int soldQtd){

        float percComprada = soldQtd/this.quantity;

        if(percComprada == 0){
            product.setProductSearch(SearchProductEnum.NãoProcurado);
        }
        
        else if(percComprada > 0 && percComprada <= 0.33f){
            product.setProductSearch(SearchProductEnum.PoucoProcurado);
        }

        else if(percComprada > 0.33f && percComprada <= 0.66f){
            product.setProductSearch(SearchProductEnum.Procurado);
        }

        else if(percComprada > 0.66f && percComprada <= 1.0f) {
            product.setProductSearch(SearchProductEnum.MuitoProcurado);
        }
    }
    
    // getters

    public int getId(){
        return this.id;
    }

    public int getQtd(){
        return this.quantity;
    }

    public float getPrice(){
        return this.price;
    }

    public String getName(){
        return this.name;
    }

    // setters 
    private void setId(int id){
        this.id = id;
    }

    private void setQtd(int qtd){
        this.quantity = qtd;
    }

    private void setPrice(float price){
        this.price = price;
    }

    private void setName(String name){
        this.name = name;
    }
    
    public void setProductSearch(SearchProductEnum procuraEnum){
        this.procuraDoProduto = procuraEnum;
    }

    public String toString(){
        String separator ="-------------------------------";
        String productInfo = "\nProduto: " + this.getName() + "\n" + "Código: " + this.getId() + "\n" + "Valor Unitário: " + this.getPrice() + "\n" + "Quantidade disponível: " + this.getQtd() + "\n" + separator;
        return productInfo;
    }

}
