
import java.util.Scanner;
import personPackage.Employee;
import stockPackage.Stock;
import personPackage.Provider;

public class App {    
    public static void main(String[] args) throws Exception {
		Stock estoque = new Stock();
		if(Stock.stock.size() == 0){
			Stock.readList();
		} 
   		// Apresentação dos responsáveis:
		System.out.println("\n\nAlunos:\n\tLara Gabrielly Souza | 508159\n\tVictor Anthony | 508653");
		System.out.println("Professor: Atílio Gomes \nCurso: Eng. de Software\nDisciplina: Programação Orientada a Objetos \n");
        System.out.println("Bem-vindo ao sistema de Estoque da Oficina Bug! \n------------------------------------------------------ ");
		// Instancia a lista de produtos que estará disponível como DB.
        System.out.println("\nDigite o seu nome:");
        Scanner userName = new Scanner(System.in);
		String userNameStr = userName.next();

		// Inicializa o funcionário
		Employee employee = new Employee(userNameStr);
		Provider provider = new Provider("Fornecedor da Oficina");
		provider.ProviderObj();
        System.out.println("Bem-vindo(a), "+ userNameStr +"! Você poderá usar o seguinte comando para começarmos: ");
		// Loop de funcionamento em modo de interação:
		boolean sair = false;

		do
		{
			String userEntryStr = "";

			System.out.println("\najuda - Exibe os comandos disponíveis.");
			System.out.print("Entre com o comando desejado: ");
			Scanner userEntry = new Scanner(System.in);

			userEntryStr = userEntry.nextLine();

			switch(userEntryStr.toLowerCase())
			{
				case "ajuda":			//Exibe a ajuda do programa.
				{
					System.out.println("$ajuda\n");
					System.out.println("\nComandos disponíveis para o usuário:\n----------------------------------------");
					System.out.println("Ajuda - Exibe a ajuda do programa.");
					System.out.println("(1) - AdicionarProduto - Adiciona um novo produto ao estoque");
					System.out.println("(2) - MostrarProdutos - Exibe os produtos registrados no estoque.");
					System.out.println("(3) - ProcurarProduto - Busca por um produto no estoque, podendo ser pelo nome ou id.");
					System.out.println("(4) - RetirarDoEstoque - Retira o produto do estoque");
                    System.out.println("(5) - ReporProduto - Repõe o produto do estoque");
					System.out.println("(6) - DeletarProduto - Apaga um produto do estoque e todas as suas informações");
					System.out.println("(7) - ListarProdutosFornecedor - Lista todos os produtos disponíveis pelo fornecedor");
					System.out.println("(8) - EditarProduto - Editar o id do produto");
					System.out.println("-----------------------------------------------\nSair - Encerra o programa.");

					break;
				}

				case "1": //Cria um novo produto no estoque
				{
					employee.createProduct(estoque);
					break;
				}
				case "2":	//Exibe todos os produtos disponíveis no banco de dados do estoque.
				{
					employee.productList();
					break;
				}
				case "3":	//Busca por uma identificação de um produto (ID de produto).
				{
					System.out.println("$3\n");
					employee.findProduct(estoque);
					break;
				}
                case "4":  //Retira unidades de um produto do estoque
				{
					System.out.println("$4\n");
					employee.productShipment(estoque);
					break;
				}
				case "5":	//Repõe unidades de um produto do estoque.
				{
					System.out.println("$5\n");
					employee.productReplenish(estoque);
					break;
				}
				case "6":	//Deleta um produto do estoque
				{
					System.out.println("$6\n");
					employee.deleteProduct(estoque);
					break;
				}
				case "7":	//Lista os produtos disponíveis no fornecedor
				{
					provider.listProviderProducts();
					break;
				}
				case "8":	//Edita o ID de um produto
				{
					employee.editProduct(estoque);
					break;
				}
				case "sair": //Sai da aplicação
				{
					Stock.writeList();
					System.out.println("$sair\n");
					System.out.println("\n\nSaindo do programa...\n\n");
					sair = true;
					break;
				}
			}
		}
		while(!sair);
	}
    

}
