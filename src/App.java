import java.util.Scanner;

import personPackage.Employee;
import stockPackage.Stock;
import personPackage.Provider;

public class App {    
    public static void main(String[] args) throws Exception {

   // Apresentação dos responsáveis:
		System.out.println("\n\nAlunos:\n\tLara Gabrielly Souza | 508159\n\tVictor Anthony | 508653");
		System.out.println("Professor: Atílio Gomes \nCurso: Eng. de Software\nDisciplina: Programação Orientada a Objetos \n");
        System.out.println("Bem-vindo ao sistema de Estoque da Oficina Bug! \n------------------------------------------------------ ");
		// Instancia a lista de produtos que estará disponível como DB.
		Stock estoque = new Stock();
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

			System.out.println("ajuda - Exibe os comandos disponíveis.");
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
					System.out.println("AdicionarProduto - Adiciona um novo produto ao estoque");
					System.out.println("MostrarProdutos - Exibe os produtos registrados no estoque.");
					System.out.println("ProcurarProduto - Busca por um produto no estoque, podendo ser pelo nome ou id.");
					System.out.println("RetirarDoEstoque - Retira o produto do estoque");
                    System.out.println("ReporProduto - Repõe o produto do estoque");
					System.out.println("DeletarProduto - Apaga um produto do estoque e todas as suas informações");
					System.out.println("-----------------------------------------------\nSair - Encerra o programa.");

					break;
				}

				case "adicionarproduto":
				{
					System.out.println("$adicionarproduto\n");
					employee.createProduct(estoque);
					break;
				}
				case "mostrarprodutos":	//Exibe todos os produtos disponíveis no banco de dados do estoque.
				{
					System.out.println("$mostrarprodutos\n");
					employee.productList();
					break;
				}
				case "procurarproduto":	//Busca por uma identificação de um produto (ID de produto).
				{
					System.out.println("$procurarproduto\n");
					employee.findProduct(estoque);
					break;
				}
                case "reporproduto":	//Busca por uma identificação de um produto (ID de produto).
				{
					System.out.println("$reporproduto\n");
					employee.productReplenish(estoque);
					break;
				}
                case "retirardoestoque":
				{
					System.out.println("$retirardoestoque\n");
					employee.productShipment(estoque);
					break;
				}
				case "deletarproduto":
				{
					System.out.println("$deletarproduto\n");
					employee.deleteProduct(estoque);
					break;
				}
				case "sair":
				{
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
