public abstract class Funcionario implements Empregado {
    protected String nome;
    protected String id;

    public Funcionario(String nome, String id) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }
        if (id == null || !id.matches("\\d{3}")) {
            throw new IllegalArgumentException("O ID deve conter exatamente 3 dígitos (exemplo: 123).");
        }
        this.nome = nome;
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }
        this.nome = nome;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        if (id == null || !id.matches("\\d{3}")) {
            throw new IllegalArgumentException("O ID deve conter exatamente 3 dígitos (exemplo: 123).");
        }
        this.id = id;
    }

    @Override
    public String status() {
        return "Nome: " + nome + ", ID: " + id;
    }

    // Método abstrato para cálculo de pagamento
    @Override
    public abstract double calculoPagamento();
}