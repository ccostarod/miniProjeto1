package model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Movimentacao {
    Usuario usuario;
    Exemplar exemplar;
    LocalDate data;
    boolean statusLeitura;

    public Movimentacao(Usuario usuario, Exemplar exemplar, LocalDate data, boolean statusLeitura) {
        this.usuario = usuario;
        this.exemplar = exemplar;
        this.data = data;
        this.statusLeitura = statusLeitura;
    }
    public Movimentacao(Usuario usuario, Exemplar exemplar, LocalDate data) {
        this.usuario = usuario;
        this.exemplar = exemplar;
        this.data = data;
    }


    public LocalDate getData() {
        return data;
    }

        public void setData(LocalDate data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public void setStatusLeitura(){
        if (statusLeitura){
            usuario.setContadorDeLeituras(usuario.getContadorDeLeituras() + 1);
        }
        return;
    }
    public String getStatusLeitura(){
        if (statusLeitura){
            return "Lido";
        }
        else {
            return "Nao lido";
        }
    }
    public String toStringEmprestado() {
        LocalDate dataDevolucao = data.plus(7, ChronoUnit.DAYS);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return "Emprestado - Usuario: " + usuario + "\nExemplar: " + exemplar + ", Devolução até " + dataDevolucao.format(formato);
    }

    public String toStringDevolvido() {
        return "Devolvido - Usuario: " + usuario + "\nExemplar: " + exemplar + ", data de devolução: " + data + ", status de leitura: " + getStatusLeitura() + "\n";
    }

    public String toStringRanking(){
        return "Usuario: " + usuario + ", quantidade de livros lidos: " + usuario.contadorDeLeituras;
    }


}
