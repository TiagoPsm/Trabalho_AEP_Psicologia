package psicomatch.Modelos;

public class Profissional extends Pessoa{
    private double valorConsulta;

    public Profissional(String nome, int telefone, String cpf, double valorConsulta) {
        super(nome, telefone, cpf);
        this.valorConsulta = valorConsulta;
    }
}
