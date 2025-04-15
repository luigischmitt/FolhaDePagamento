# Sistema de Folha de Pagamento

## Descrição
Este sistema gerencia diferentes tipos de funcionários (horistas, comissionados, assalariados) e foi projetado para ser extensível e modular. Com a introdução de uma classe abstrata `Funcionario`, o sistema centraliza a lógica comum, reduzindo duplicação de código e facilitando a adição de novos tipos de funcionários, como freelancers e estagiários.

---

## Estrutura
### Interface `Empregado`
Define os métodos básicos que todos os funcionários devem implementar:
- `calculoPagamento()`: Calcula o pagamento.
- `getNome()` / `setNome(String nome)`: Obtém/define o nome.
- `getId()` / `setId(String id)`: Obtém/define o ID.
- `status()`: Retorna uma descrição do funcionário.

### Classe Abstrata `Funcionario`
A classe abstrata `Funcionario` implementa a interface `Empregado` e centraliza a lógica comum:
- **Atributos**: `nome` e `id`.
- **Validações**:
  - O nome não pode ser vazio.
  - O ID deve seguir o formato `XXX` (três dígitos).
- **Métodos Comuns**:
  - `getNome`, `setNome`, `getId`, `setId` e `status`.

### Implementações
As classes específicas herdam de `Funcionario` e implementam a lógica de pagamento:
1. **Horista**:
   - Atributos: `valorHora` e `horasTrabalhadas`.
   - Pagamento: `valorHora * horasTrabalhadas`.
2. **Comissionado**:
   - Atributos: `vendas` e `taxaComissao`.
   - Pagamento: `vendas * taxaComissao`.
3. **Assalariado**:
   - Atributo: `salario`.
   - Pagamento: Valor fixo mensal.

---

## Extensibilidade
Para adicionar novos tipos de funcionários:
1. **Criar uma classe que herde de `Funcionario`**:
   - Exemplo: `Freelancer` com lógica específica para pagamento.
2. **Implementar o método `calculoPagamento`**:
   - Defina como o pagamento será calculado para o novo tipo.
3. **Adicionar lógica no método `cadastrarEmpregado`**:
   - Inclua um novo `case` no `switch` para lidar com o novo tipo.

### Exemplo: Classe `Freelancer`
```java
public class Freelancer extends Funcionario {
    private double valorProjeto;

    public Freelancer(String nome, String id, double valorProjeto) {
        super(nome, id);
        if (valorProjeto <= 0) {
            throw new IllegalArgumentException("O valor do projeto deve ser maior que zero.");
        }
        this.valorProjeto = valorProjeto;
    }

    @Override
    public double calculoPagamento() {
        return valorProjeto;
    }

    @Override
    public String status() {
        return super.status() + ", Valor do Projeto: R$ " + String.format("%.2f", valorProjeto);
    }
}
