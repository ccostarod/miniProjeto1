package model;

public class Revista extends Exemplar{
    private int volume;
    private String mes;
    public Revista(String titulo, String autor, String resumo, String editora, int ano, int quantPaginas, String genero, int volume, String mes) {
        super(titulo, autor, resumo, editora, ano, quantPaginas, genero);
        this.volume = volume;
        this.mes = mes;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
}
