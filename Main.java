import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Empregado> empregados = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        System.out.println("Bem-vindo ao sistema de folha de pagamento!");

        while (flag) {
            try {
                limparTela();
                System.out.println("\n======== MENU ========");
                System.out.println("[0] - SAIR");
                System.out.println("[1] - CADASTRAR");
                System.out.println("[2] - VISUALIZAR EMPREGADOS");
                System.out.print("Escolha uma opção: ");
                int escolha = sc.nextInt();
                sc.nextLine(); 

                switch (escolha) {
                    case 0:
                        System.out.println("Programa encerrado. Até mais!");
                        flag = false;
                        break;
                    case 1:
                        cadastrarEmpregado(sc);
                        break;
                    case 2:
                        visualizarEmpregados();
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        pausar(sc);
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                sc.nextLine(); 
                pausar(sc);
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
                pausar(sc);
            }
        }
        sc.close();
    }

    private static void cadastrarEmpregado(Scanner sc) {
        try {
            System.out.println("Digite o tipo de empregado (horista, comissionado, assalariado): ");
            String tipo = sc.nextLine().toLowerCase();

            switch (tipo) {
                case "horista":
                    String nomeHorista = lerString(sc, "Digite o nome do empregado:");
                    String idHorista = lerId(sc, "Digite o ID do empregado (formato: XXX):");
                    double valorHora = lerDouble(sc, "Digite o valor por hora:");
                    int horasTrabalhadas = lerInt(sc, "Digite as horas trabalhadas no mês:");

                    Horista horista = new Horista(nomeHorista, idHorista, valorHora, horasTrabalhadas);
                    empregados.add(horista);
                    System.out.println("Horista cadastrado com sucesso!");
                    break;

                case "comissionado":
                    String nomeComissionado = lerString(sc, "Digite o nome do empregado:");
                    String idComissionado = lerId(sc, "Digite o ID do empregado (formato: XXX):");
                    double vendas = lerDouble(sc, "Digite o total de vendas:");
                    double taxaComissao = lerDouble(sc, "Digite a taxa de comissão (entre 0 e 1):");
                    if (taxaComissao > 1) {
                        throw new CustomException("A taxa de comissão deve estar entre 0 e 1.");
                    }

                    Comissionado comissionado = new Comissionado(nomeComissionado, idComissionado, vendas, taxaComissao);
                    empregados.add(comissionado);
                    System.out.println("Comissionado cadastrado com sucesso!");
                    break;

                case "assalariado":
                    String nomeAssalariado = lerString(sc, "Digite o nome do empregado:");
                    String idAssalariado = lerId(sc, "Digite o ID do empregado (formato: XXX):");
                    double salario = lerDouble(sc, "Digite o salário:");

                    Assalariado assalariado = new Assalariado(nomeAssalariado, idAssalariado, salario);
                    empregados.add(assalariado);
                    System.out.println("Assalariado cadastrado com sucesso!");
                    break;

                default:
                    throw new CustomException("Tipo de empregado inválido. Os tipos válidos são: horista, comissionado, assalariado.");
            }
        } catch (CustomException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
        pausar(sc);
    }

    private static void visualizarEmpregados() {
        limparTela();
        if (empregados.isEmpty()) {
            System.out.println("Nenhum empregado cadastrado.");
        } else {
            System.out.println("Lista de empregados cadastrados:");
            int contador = 1;
            for (Empregado empregado : empregados) {
                System.out.println(contador + ". " + empregado.status());
                contador++;
            }
        }
        pausar(new Scanner(System.in));
    }

    private static String lerString(Scanner sc, String mensagem) throws CustomException {
        System.out.println(mensagem);
        String entrada = sc.nextLine();
        if (entrada.isEmpty()) {
            throw new CustomException("O valor não pode ser vazio.");
        }
        return entrada;
    }

    private static double lerDouble(Scanner sc, String mensagem) throws CustomException {
        try {
            System.out.println(mensagem);
            String entrada = sc.nextLine(); 
            double valor = Double.parseDouble(entrada);
            if (valor <= 0) {
                throw new CustomException("O valor deve ser maior que zero.");
            }
            return valor;
        } catch (NumberFormatException e) {
            throw new CustomException("Entrada inválida. Por favor, insira um número válido (use ponto para separar decimais).");
        }
    }

    private static int lerInt(Scanner sc, String mensagem) throws CustomException {
        try {
            System.out.println(mensagem);
            int entrada = sc.nextInt();
            if (entrada < 0) {
                throw new CustomException("O valor não pode ser negativo.");
            }
            sc.nextLine(); 
            return entrada;
        } catch (InputMismatchException e) {
            sc.nextLine();
            throw new CustomException("Entrada inválida. Por favor, insira um número inteiro válido.");
        }
    }

    private static String lerId(Scanner sc, String mensagem) throws CustomException {
        System.out.println(mensagem);
        String id = sc.nextLine();
        if (!id.matches("\\d{3}")) {
            throw new CustomException("O ID deve conter exatamente 3 dígitos (exemplo: 123).");
        }
        return id;
    }

    private static void limparTela() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Não foi possível limpar a tela.");
        }
    }

    private static void pausar(Scanner sc) {
        System.out.println("\nPressione Enter para continuar...");
        sc.nextLine();
    }
}
