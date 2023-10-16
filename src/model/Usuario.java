package model;

import java.time.LocalDate;
import java.util.Comparator;

public class Usuario implements Comparable<Usuario> {
    String nome;
    String cpf;
    LocalDate dataDeNascimento;
    boolean indicadorCrianca;
    int contadorDeLeituras;
    boolean possuiEmprestimo;

    public Usuario(String nome, String cpf, LocalDate dataDeNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
        possuiEmprestimo = false;
        contadorDeLeituras = 0;
        if (getIdade() > 14){
            indicadorCrianca = false;
        }
        else { indicadorCrianca = true;}
    }

    public int getContadorDeLeituras() {
        return contadorDeLeituras;
    }

    public void setContadorDeLeituras(int contadorDeLeituras) {
        this.contadorDeLeituras = contadorDeLeituras;
    }

    public boolean isPossuiEmprestimo() {
        return possuiEmprestimo;
    }

    public void setPossuiEmprestimo(boolean possuiEmprestimo) {
        this.possuiEmprestimo = possuiEmprestimo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public boolean isIndicadorCrianca() {
        return indicadorCrianca;
    }
    public int getIdade(){
        LocalDate hoje = LocalDate.now();
        return dataDeNascimento.until(hoje).getYears();
    }
    public void setIndicadorCrianca(boolean indicadorCrianca) {
        this.indicadorCrianca = indicadorCrianca;
    }

    @Override
    public String toString() {
        return nome + ", " + cpf;
    }

    public String toStringRanking(){
        return nome + ", " + cpf + ", quantidade de livros lidos: " + contadorDeLeituras;
    }

    @Override
    public int compareTo(Usuario o) {
        return Integer.compare(o.contadorDeLeituras, this.contadorDeLeituras);
    }
}
