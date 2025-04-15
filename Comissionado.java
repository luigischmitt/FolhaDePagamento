public class Comissionado extends Funcionario {
    private double vendas;
    private double taxaComissao;

    public Comissionado(String nome, String id, double vendas, double taxaComissao) {
        super(nome, id);
        if (vendas < 0) {
            throw new IllegalArgumentException("O total de vendas não pode ser negativo.");
        }
        if (taxaComissao <= 0 || taxaComissao > 1) {
            throw new IllegalArgumentException("A taxa de comissão deve estar entre 0 e 1.");
        }
        this.vendas = vendas;
        this.taxaComissao = taxaComissao;
    }

    @Override
    public double calculoPagamento() {
        return vendas * taxaComissao;
    }

    @Override
    public String status() {
        return super.status() + ", Vendas: R$ " + String.format("%.2f", vendas) +
               ", Taxa de Comissão: " + String.format("%.2f", taxaComissao * 100) + "%" +
               ", Pagamento: R$ " + String.format("%.2f", calculoPagamento());
    }
}
