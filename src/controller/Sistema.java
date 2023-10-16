package controller;

import model.Movimentacao;
import model.Exemplar;
import model.Usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Sistema {
    private List<Exemplar> exemplares;
    private List<Usuario> usuarios;
    private List<Movimentacao> emprestimos;
    private List<Movimentacao> devolucoes;
    public Sistema() {
        exemplares = new ArrayList<>();
        usuarios = new ArrayList<>();
        emprestimos = new ArrayList<>();
        devolucoes = new ArrayList<>();
    }

    public List<Exemplar> getExemplares() {
        return exemplares;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Movimentacao> getEmprestimos() {
        return emprestimos;
    }

    public List<Movimentacao> getDevolucoes() {
        return devolucoes;
    }

    public void atualizarAcervo(Exemplar exemplar){
        exemplares.add(exemplar);
    }

    public List<Exemplar> pesquisarExemplar(String key){
        List<Exemplar> exemplaresProcurados = new ArrayList<>();
        exemplaresProcurados = exemplares.stream().filter(x-> x.getAutor().contains(key) || x.getTitulo().contains(key)
                || x.getResumo().contains(key)).collect(Collectors.toList());
        return exemplaresProcurados;
    }


    public boolean cadastrarUsuario(Usuario usuario){
        boolean verificarCPF = usuarios.stream().anyMatch(x-> x.getCpf().equals(usuario.getCpf()));
        if (!verificarCPF){
            usuarios.add(usuario);
            return true;
        }
        else
            return false;
    }

    public boolean procurarCPF(String cpf){
        if (!usuarios.isEmpty()){
            for (Usuario usuario : usuarios) {
                if (usuario.getCpf().equalsIgnoreCase(cpf)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean alterarUsuario(String cpf, int key, String update) {
        if (!usuarios.isEmpty()){
            for (Usuario usuario : usuarios) {
                if (usuario.getCpf().equals(cpf)) {
                    if (key == 1) { // nome
                        usuario.setNome(update);
                    } else if (key == 2) { // cpf
                        usuario.setCpf(update);
                    } else if (key == 3) {
                        DateTimeFormatter formatoDesejado = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        LocalDate novaData = LocalDate.parse(update, formatoDesejado);
                        usuario.setDataDeNascimento(novaData);
                        usuario.setIndicadorCrianca(usuario.getIdade() <= 14);
                        System.out.println(usuario);

                    }
                    return true;
                }
            }

        }
        return false;
    }

    public int emprestimoExemplar(String cpf, int key, LocalDate data){
        if (!usuarios.isEmpty() && !exemplares.isEmpty()){
            for (Usuario usuario : usuarios){
                if (usuario.getCpf().equals(cpf)){
                    for (Movimentacao emprestimo : emprestimos){
                        if (emprestimo.getUsuario().equals(usuario)){
                            emprestimo.getUsuario().setPossuiEmprestimo(true);
                            break;
                        }
                    }
                    if (!usuario.isPossuiEmprestimo() && exemplares.get(key - 1).isDisponivel()){
                        exemplares.get(key-1).setDisponivel(false);
                        emprestimos.add(new Movimentacao(usuario, exemplares.get(key - 1), data));
                        usuario.setPossuiEmprestimo(true);
                        return 1;
                    }
                    else if (usuario.isPossuiEmprestimo()){
                        return 2;
                    }
                    else{
                        return 3;
                    }
                }
            }

        }
        return 0; // Cpf nao encontrado!
    }


    public boolean devolucaoExemplar(String cpf, boolean statusLeitura, LocalDate dataDeDevolucao){
        if (!emprestimos.isEmpty()){
            for (Movimentacao emprestimo : emprestimos){
                if (emprestimo.getUsuario().getCpf().equals(cpf) && emprestimo.getUsuario().isPossuiEmprestimo() && (dataDeDevolucao.isAfter(emprestimo.getData()) || dataDeDevolucao.isEqual(emprestimo.getData()))){
                    emprestimo.getExemplar().setDisponivel(true);
                    emprestimo.getUsuario().setPossuiEmprestimo(false);
                    Movimentacao devolucao;
                    devolucoes.add(devolucao = new Movimentacao(emprestimo.getUsuario(), emprestimo.getExemplar(), dataDeDevolucao, statusLeitura));
                    devolucao.setStatusLeitura();
                    emprestimos.remove(emprestimo);
                    return true;
                }
            }
        }
        return false;
    }




    public void imprimirRegistros(){
        if (!emprestimos.isEmpty()){
            System.out.println("Emprestimos: ");
            for (Movimentacao emprestimo : emprestimos){
                System.out.println(emprestimo.toStringEmprestado());
            }
        }
        if (!devolucoes.isEmpty()){
            System.out.println("Devoluções: ");
            for (Movimentacao devolucao : devolucoes){
                System.out.println(devolucao.toStringDevolvido());
            }
            System.out.println();
        }
        else{
            System.out.println("Nao ha registros ainda");
        }
    }

    public List<Usuario> rankingCriancas() {
        List<Usuario> rankingCriancas = usuarios.stream().filter(x -> x.isIndicadorCrianca() && x.getContadorDeLeituras() > 0).collect(Collectors.toList());
        Collections.sort(rankingCriancas);
        return rankingCriancas;
    }

}
