package personPackage;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import productPackage.Product;

public class Provider extends Person {

    public static ArrayList<Product> ProdBD = new ArrayList<Product>(21);
    Locale localeBR = new Locale("pt","BR");

    public Provider(String name){
        super(name);
    }

    public void ProviderObj(){
        { 
            Product Obj00 = new Product("Alicate", 1, 1000, 30.00f);
            Product Obj01 = new Product("Chave de Fenda", 2, 1000, 19.90f);
            Product Obj02 = new Product("Chave de Catraca", 3, 1000, 44.90f);
            Product Obj03 = new Product("Macaco", 4, 1000, 14.90f);
            Product Obj04 = new Product("Paquímetro", 5, 1000, 70.00f);
            Product Obj05 = new Product("Torquímetro", 6, 1000, 650.00f);
            Product Obj06 = new Product("Pneu", 7, 1000, 129.90f);
            Product Obj07 = new Product("Painel Porta Ferramentas", 8, 1000, 130.00f);
            Product Obj08 = new Product("Bateria de Carro", 9, 1000, 199.90f);
            Product Obj09 = new Product("Chave Estrela", 10, 1000, 260.00f);
            Product Obj10 = new Product("Bandeja Coletora Oleo", 11, 1000, 200.00f);
            Product Obj11 = new Product("Limpador de Vidro", 12, 1000, 49.90f);
            Product Obj12 = new Product("Calibrador Digital", 13, 1000, 280.00f);
            Product Obj13 = new Product("Torno de Bancada", 14, 1000, 520.00f);
            Product Obj14 = new Product("Tapete para Carro", 15, 1000, 129.90f);
            Product Obj15 = new Product("Carrinho para Ferramentas",16 , 1000, 1049.00f);
            Product Obj16 = new Product("Kit de Limpeza", 17, 1000, 59.90f);
            Product Obj17 = new Product("Encolhedor de Molas", 18, 1000, 27.00f);
            Product Obj18 = new Product("Densimetro", 19, 1000, 50.00f);
            Product Obj19 = new Product("Óleo", 20, 1000, 99.90f);
            Product Obj20  = new Product("Bateria de Moto", 21, 1000, 119.90f);
        
            ProdBD.add(0, Obj00);
            ProdBD.add(1, Obj01);
            ProdBD.add(2, Obj02);
            ProdBD.add(3, Obj03);
            ProdBD.add(4, Obj04);
            ProdBD.add(5, Obj05);
            ProdBD.add(6, Obj06);
            ProdBD.add(7, Obj07);
            ProdBD.add(8, Obj08);
            ProdBD.add(9, Obj09);
            ProdBD.add(10, Obj10);
            ProdBD.add(11, Obj11);
            ProdBD.add(12, Obj12);
            ProdBD.add(13, Obj13);
            ProdBD.add(14, Obj14);
            ProdBD.add(15, Obj15);
            ProdBD.add(16, Obj16);
            ProdBD.add(17, Obj17);
            ProdBD.add(18, Obj18);
            ProdBD.add(19, Obj19);     
            ProdBD.add(20, Obj20);     
        }
    }

    public void listProviderProducts(){
        NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);

        System.out.println("Produtos fornecidos:\n--------------------------------------------------");

        for (Product produto : ProdBD)
        {
            String ProdutoInfo = "";
          
            ProdutoInfo += Integer.toString(produto.getId()) + " | ";
            ProdutoInfo += produto.getName() + " | ";
            ProdutoInfo += dinheiro.format(produto.getPrice()) + " | ";
            ProdutoInfo += Integer.toString(produto.getQtd()) + "und ";

            System.out.println(ProdutoInfo);
        }
        System.out.println("--------------------------------------------------");

    }

    public static boolean sellProduct(String name, int qtdToBeSold){
        boolean disponibilityProvider = false;
        boolean exist = false;

        for(int i = 0; i< ProdBD.size(); i++){
            if(Provider.ProdBD.get(i).getName().startsWith(name)){
                if(Provider.ProdBD.get(i).getQtd() >= qtdToBeSold){
                    Provider.ProdBD.get(i).setQtd(Provider.ProdBD.get(i).getQtd() - qtdToBeSold);
                    disponibilityProvider = true;
                    exist = true;
                    break;
                }
                else{
                    System.out.println("ATENÇÃO: O número máximo de unidades que o fornecedor possui é " + Provider.ProdBD.get(i).getQtd()+"\n");
                    disponibilityProvider = false;
                    exist = true;
                    break;
                } 
            }    
        }

        if(exist == false) System.out.println("ATENÇÃO: Produto não existe\n");

        return disponibilityProvider;

    }    
}    
    
   
