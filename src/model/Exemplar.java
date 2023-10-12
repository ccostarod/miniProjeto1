package model;

public abstract class Exemplar {
    protected String titulo;
    protected String autor;
    protected String resumo;
    protected String editora;
    protected int ano;
    protected int quantPaginas;
    protected String genero;
    protected boolean disponivel;



    public Exemplar(String titulo, String autor, String resumo, String editora, int ano, int quantPaginas, String genero) {
        this.titulo = titulo;
        this.autor = autor;
        this.resumo = resumo;
        this.editora = editora;
        this.ano = ano;
        this.quantPaginas = quantPaginas;
        this.genero = genero;
        disponivel = true;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getQuantPaginas() {
        return quantPaginas;
    }

    public void setQuantPaginas(int quantPaginas) {
        this.quantPaginas = quantPaginas;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        if (disponivel){
            return titulo + ", " + autor + ", " + genero + " (Disponivel)";
        }
        else return titulo + ", " + autor + ", " + genero + " (Indisponivel)";
    }
}
