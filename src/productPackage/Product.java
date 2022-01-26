package productPackage;


public class Product {

    private String name;
    private int quantity;
    private int id;
    private float price;
    private SearchProductEnum productSearch;
    private AvalProductEnum productAvailability;
    public int recommendedQuantity = 33;
    /**
     * Construtor da classe Product
     * @param name - Nome do Produto
     * @param id - Código do Produto
     * @param quantity - Quantidade disponível no estoque
     * @param price - Preço do produto
     * @param productSearch - a popularidade do produto
     * @param productAvailability - a disponibilidade do produto
     */

    public Product(String name, int id, int quantity, float price){
        this.name = name;
        this.quantity = quantity;
        this.id = id;
        this.price = price;
    }

    public Product(String name, int id, int quantity, float price, SearchProductEnum searchEnum, AvalProductEnum availability){
        this.name = name;
        this.quantity = quantity;
        this.id = id;
        this.price = price;
        this.productSearch = searchEnum;
        this.productAvailability = availability;   
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

    public AvalProductEnum disponibility(){

        float PorcentEstoque = this.quantity / this.recommendedQuantity;

        if(PorcentEstoque == 0)     //Sem estoque.
        {
            return AvalProductEnum.INDISPONIVEL;
        }
        else if(PorcentEstoque <= 0.1)      //Estoque muito baixo.
        {
            return AvalProductEnum.MUITOBAIXA;
        }
        else if(PorcentEstoque <= 0.25 && PorcentEstoque > 0.1)     //Estoque baixo
        {
            return AvalProductEnum.BAIXA;
        }
        else if(PorcentEstoque <= 0.5 && PorcentEstoque > 0.25)     //Estoque médio
        {
            return AvalProductEnum.MEDIA;
        }
        else if(PorcentEstoque <= 0.75 && PorcentEstoque > 0.5)     //Estoque alto
        {
            return AvalProductEnum.ALTA;
        }
        else if(PorcentEstoque <= 1 && PorcentEstoque > 0.75)       //Estoque cheio ou muito alto
        {
            return AvalProductEnum.MUITOALTA;
        }
        else
        {
            return AvalProductEnum.INDISPONIVEL;   // Em caso de uma falha no estoque, impedir a possibilidade de compra.
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
    public SearchProductEnum getProductSearch() {
        return this.productSearch;
    }
    public AvalProductEnum getProductAvailability() {
        return this.productAvailability;
    }
    // setters 
    private void setId(int id){
        this.id = id;
    }

    public void setQtd(int qtd){
        this.quantity = qtd;
    }

    private void setPrice(float price){
        this.price = price;
    }

    private void setName(String name){
        this.name = name;
    }
    
    public void setProductSearch(SearchProductEnum procuraEnum){
        this.productSearch = procuraEnum;
    }

    public void setProductAvailability(AvalProductEnum avalEnum){
        this.productAvailability = avalEnum;
    }
    public String toString(){
        String separator ="-------------------------------";
        String productInfo = "\nProduto: " + this.getName() + "\n" + "Código: " + this.getId() + "\n" + "Valor Unitário: " + this.getPrice() + "\n" + "Quantidade disponível: " + this.getQtd() + "\n";
       if(this.getProductSearch() == null || this.getProductAvailability() == null){
        return productInfo + separator;
       } 
     else return productInfo + "Popularidade: " + SearchProdClass.ProcuraProduto2String(this.getProductSearch())+ "\n" + "Disponibilidade no estoque: " + AvalClass.DispE2Str(this.getProductAvailability()) + "\n" + separator;
    }

}
