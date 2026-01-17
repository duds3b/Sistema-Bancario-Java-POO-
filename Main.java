import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Banco banco = new Banco();

        while (true) {
            System.out.println("\n=== JAVA BANK CLI ===");
            System.out.println("1) Criar conta");
            System.out.println("2) Listar contas");
            System.out.println("3) Depositar");
            System.out.println("4) Sacar");
            System.out.println("5) Transferir");
            System.out.println("0) Sair");
            System.out.print("Escolha: ");

            String op = sc.nextLine().trim();

            try {
                if (op.equals("1")) {
                    System.out.print("Nome do titular: ");
                    String nome = sc.nextLine();
                    Conta conta = banco.criarConta(nome);
                    System.out.println("Conta criada! Numero: " + conta.getNumero());

                } else if (op.equals("2")) {
                    if (banco.listarContas().isEmpty()) {
                        System.out.println("Nenhuma conta cadastrada.");
                    } else {
                        for (Conta c : banco.listarContas()) {
                            System.out.println(c);
                        }
                    }

                } else if (op.equals("3")) {
                    Conta conta = pedirConta(sc, banco);
                    System.out.print("Valor: ");
                    double valor = Double.parseDouble(sc.nextLine().replace(",", "."));
                    conta.depositar(valor);
                    System.out.println("Deposito OK. Saldo: " + String.format("%.2f", conta.getSaldo()));

                } else if (op.equals("4")) {
                    Conta conta = pedirConta(sc, banco);
                    System.out.print("Valor: ");
                    double valor = Double.parseDouble(sc.nextLine().replace(",", "."));
                    conta.sacar(valor);
                    System.out.println("Saque OK. Saldo: " + String.format("%.2f", conta.getSaldo()));

                } else if (op.equals("5")) {
                    System.out.println("Conta ORIGEM:");
                    Conta origem = pedirConta(sc, banco);

                    System.out.println("Conta DESTINO:");
                    Conta destino = pedirConta(sc, banco);

                    System.out.print("Valor: ");
                    double valor = Double.parseDouble(sc.nextLine().replace(",", "."));

                    origem.transferirPara(destino, valor);
                    System.out.println("Transferencia OK.");
                    System.out.println("Saldo origem: " + String.format("%.2f", origem.getSaldo()));
                    System.out.println("Saldo destino: " + String.format("%.2f", destino.getSaldo()));

                } else if (op.equals("0")) {
                    System.out.println("Falou.");
                    break;

                } else {
                    System.out.println("Opcao invalida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        sc.close();
    }

    private static Conta pedirConta(Scanner sc, Banco banco) {
        System.out.print("Numero da conta: ");
        int numero = Integer.parseInt(sc.nextLine().trim());
        Conta conta = banco.buscarContaPorNumero(numero);
        if (conta == null) {
            throw new IllegalArgumentException("Conta nao encontrada.");
        }
        return conta;
    }
}
