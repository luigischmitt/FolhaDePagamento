public class Assalariado extends Funcionario {
    private double salario;

    public Assalariado(String nome, String id, double salario) {
        super(nome, id);
        if (salario <= 0) {
            throw new IllegalArgumentException("O salário deve ser maior que zero.");
        }
        this.salario = salario;
    }

    @Override
    public double calculoPagamento() {
        return salario;
    }

    @Override
    public String status() {
        return super.status() + ", Salário: R$ " + String.format("%.2f", salario);
    }
}
