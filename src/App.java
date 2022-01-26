import java.util.Scanner;

import personPackage.Employee;
import stockPackage.Stock;

public class App {    
    public static void main(String[] args) throws Exception {

   // Apresentação dos responsáveis:
		System.out.println("\n\nAlunos:\n\tLara Gabrielly Souza | 508159\n\tVictor Anthony");
		System.out.println("Professor: Atílio Gomes \nCurso: Eng. de Software\nDisciplina: Programação Orientada a Objetos \n");
        System.out.println("Bem-vindo ao sistema de Estoque da Oficina Bug! \n------------------------------------------------------ ");
		// Instancia a lista de produtos que estará disponível como DB.
		Stock estoque = new Stock();
        System.out.println("\nDigite o seu nome:");
        Scanner userName = new Scanner(System.in);
		String userNameStr = userName.next();

		// Inicializa o funcionário
		Employee employee = new Employee(userNameStr);
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
					System.out.println("\nComandos disponíveis para o usuário:\n----------------------------------------");
					System.out.println("Ajuda - Exibe a ajuda do programa.");
					System.out.println("MostrarProdutos - Exibe os produtos registrados no estoque.");
					System.out.println("ProcurarProduto - Busca por um produto no estoque, podendo ser pelo nome ou id.");
					System.out.println("RetirarDoEstoque - Retira o produto do estoque");
                    System.out.println("ReporProduto - Repõe o produto do estoque");
					System.out.println("-----------------------------------------------\nSair - Encerra o programa.");

					break;
				}
				case "mostrarprodutos":	//Exibe todos os produtos disponíveis no banco de dados do estoque.
				{
					estoque.listProducts();
					break;
				}
				case "procurarproduto":	//Busca por uma identificação de um produto (ID de produto).
				{
					employee.findProduct(estoque);
					break;
				}
                case "reporproduto":	//Busca por uma identificação de um produto (ID de produto).
				{
					employee.productReplenish(estoque);
					break;
				}
                case "retirardoestoque":
				{
					employee.productShipment(estoque);
					break;
				}
				case "sair":
				{
					System.out.println("\n\nSaindo do programa...\n\n");
					sair = true;
					break;
				}
			}
		}
		while(!sair);
	}
    

}
