package model;

public class Livro extends Exemplar{
    public Livro(String titulo, String autor, String resumo, String editora, int ano, int quantPaginas, String genero) {
        super(titulo, autor, resumo, editora, ano, quantPaginas, genero);
    }
}
