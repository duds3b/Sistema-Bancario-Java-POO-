import java.util.ArrayList;

public class Banco {
    private ArrayList<Conta> contas;
    private int proximoNumero;

    public Banco() {
        contas = new ArrayList<>();
        proximoNumero = 1001;
    }

    public Conta criarConta(String titular) {
        if (titular == null || titular.trim().isEmpty()) {
            throw new IllegalArgumentException("Titular nao pode ser vazio.");
        }
        Conta conta = new Conta(proximoNumero, titular.trim());
        contas.add(conta);
        proximoNumero++;
        return conta;
    }

    public ArrayList<Conta> listarContas() {
        return contas;
    }

    public Conta buscarContaPorNumero(int numero) {
        for (Conta c : contas) {
            if (c.getNumero() == numero) return c;
        }
        return null;
    }
}
