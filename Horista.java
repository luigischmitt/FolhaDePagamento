public class Horista extends Funcionario {
    private double valorHora;
    private int horasTrabalhadas;

    public Horista(String nome, String id, double valorHora, int horasTrabalhadas) {
        super(nome, id);
        if (valorHora <= 0) {
            throw new IllegalArgumentException("O valor por hora deve ser maior que zero.");
        }
        if (horasTrabalhadas < 0) {
            throw new IllegalArgumentException("As horas trabalhadas não podem ser negativas.");
        }
        this.valorHora = valorHora;
        this.horasTrabalhadas = horasTrabalhadas;
    }

    @Override
    public double calculoPagamento() {
        return valorHora * horasTrabalhadas;
    }

    @Override
    public String status() {
        return super.status() + ", Valor por Hora: R$ " + String.format("%.2f", valorHora) +
               ", Horas Trabalhadas: " + horasTrabalhadas +
               ", Pagamento: R$ " + String.format("%.2f", calculoPagamento());
    }
}
