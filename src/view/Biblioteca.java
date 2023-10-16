package view;

import controller.Sistema;
import model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Biblioteca {
    public static void main(String[] args) {
        Sistema biblioteca = new Sistema();
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        while (true){
            System.out.println("---------MENU PRINCIPAL---------");
            System.out.println("1. Cadastrar Exemplar");
            System.out.println("2. Cadastrar Usuário");
            System.out.println("3. Alterar Dados do Usuário");
            System.out.println("4. Buscar Livro no Acervo");
            System.out.println("5. Registro de Empréstimo");
            System.out.println("6. Registro de Devolução");
            System.out.println("7. Mostrar Registro");
            System.out.println("8. Ranking de Leituras");
            System.out.println("9. Sair");
            System.out.print("Escolha: ");
            int escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha){
                case 1:
                    System.out.println("---------CADASTRO DE EXEMPLAR---------");
                    System.out.println("Escolha o tipo do exemplar: ");
                    System.out.println("1. Livro");
                    System.out.println("2. Revista");
                    System.out.print("Escolha: ");
                    int escolhaExemplar = sc.nextInt();
                    sc.nextLine();
                    switch (escolhaExemplar){
                        case 1:
                            System.out.print("Título do Livro: ");
                            String tituloLivro = sc.nextLine();

                            System.out.print("Autor do Livro: ");
                            String autorLivro = sc.nextLine();

                            System.out.print("Resumo do Livro: ");
                            String resumoLivro = sc.nextLine();

                            System.out.print("Editora do Livro: ");
                            String editoraLivro = sc.nextLine();

                            System.out.print("Ano de Publicação: ");
                            int anoLivro = sc.nextInt();
                            sc.nextLine();

                            System.out.print("Quantidade de Páginas: ");
                            int qtdPaginasLivro = sc.nextInt();
                            sc.nextLine();

                            System.out.print("Gênero do Livro: ");
                            String generoLivro = sc.nextLine();

                            Exemplar livro = new Livro(tituloLivro, autorLivro, resumoLivro, editoraLivro, anoLivro, qtdPaginasLivro, generoLivro);
                            biblioteca.atualizarAcervo(livro);
                            System.out.println("Livro cadastrado com sucesso!");
                            break;

                        case 2:
                            System.out.print("Título da revista: ");
                            String tituloRevista = sc.nextLine();

                            System.out.print("Autor da Revista: ");
                            String autorRevista = sc.nextLine();

                            System.out.print("Resumo da Revista: ");
                            String resumoRevista = sc.nextLine();

                            System.out.print("Editora da Revista: ");
                            String editoraRevista = sc.nextLine();

                            System.out.print("Ano de Publicação: ");
                            int anoRevista = sc.nextInt();
                            sc.nextLine();

                            System.out.print("Quantidade de Páginas: ");
                            int qtdPaginasRevista = sc.nextInt();
                            sc.nextLine();

                            System.out.print("Gênero da Revista: ");
                            String generoRevista = sc.nextLine();

                            System.out.print("Volume da Revista: ");
                            int volumeRevista = sc.nextInt();
                            sc.nextLine();

                            System.out.print("Mes de publicacao: ");
                            String mesDePublicacao = sc.nextLine();

                            Exemplar revista = new Revista(tituloRevista, autorRevista, resumoRevista, editoraRevista, anoRevista, qtdPaginasRevista,
                                    generoRevista, volumeRevista, mesDePublicacao);
                            biblioteca.atualizarAcervo(revista);
                            System.out.println("Revista cadastrada com sucesso!");
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                    break;
                case 2:
                    System.out.println("---------CADASTRO DE USUARIO---------");
                    System.out.print("Nome: ");
                    String nomeUsuario = sc.nextLine();

                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();

                    System.out.print("Data de nascimento (dd-MM-yyyy): ");
                    String dataDeNascimentoSTR = sc.nextLine();
                    LocalDate dataDeNascimento = LocalDate.parse(dataDeNascimentoSTR, formato);

                    Usuario usuario = new Usuario(nomeUsuario, cpf, dataDeNascimento);
                    if(biblioteca.cadastrarUsuario(usuario)){
                        System.out.println("Usuario cadastrado com sucesso!");
                    }
                    else{
                        System.out.println("Ja existe um usuario cadastrado com o CPF informado!");
                    }
                    break;

                case 3:
                    System.out.println("---------ALTERAR USUARIO---------");
                    System.out.print("Digite o CPF do usuario: ");
                    String cpfBusca = sc.nextLine();
                    if (biblioteca.procurarCPF(cpfBusca)){
                        System.out.println("Alteração em que dado:");
                        System.out.println("1. Nome");
                        System.out.println("2. CPF");
                        System.out.println("3. Data de Nascimento");
                        System.out.print("Escolha: ");
                        int escolhaAlteracao = sc.nextInt();
                        sc.nextLine();
                        switch (escolhaAlteracao){
                            case 1:
                                System.out.print("Digite o nome desejado: ");
                                String updateName = sc.nextLine();
                                if(biblioteca.alterarUsuario(cpfBusca, 1, updateName)){
                                    System.out.println("Nome alterado com sucesso!");
                                }
                                else
                                    System.out.println("Erro ao alterar Dado!");
                                break;
                            case 2:
                                System.out.print("Digite o CPF desejado: ");
                                String updateCpf = sc.nextLine();
                                if (biblioteca.alterarUsuario(cpfBusca, 2, updateCpf)){
                                    System.out.println("CPF alterado com sucesso!");
                                }
                                else
                                    System.out.println("Erro ao alterar Dado!");
                                break;
                            case 3:
                                System.out.print("Digite a data de nascimento desejada (dd-MM-yyyy): ");
                                String updatedataDeNascimento = sc.nextLine();
                                if (biblioteca.alterarUsuario(cpfBusca, 3, updatedataDeNascimento)){
                                    System.out.println("Data de nascimento alterada com sucesso!");
                                }
                                else{
                                    System.out.println("Erro ao alterar Dado!");
                                }
                                break;
                            default:
                                System.out.println("Opção inválida. Tente novamente!");
                        }
                    }
                    else {
                        System.out.println("Usuário com CPF " + cpfBusca + " não encontrado.");
                    }
                    break;
                case 4:
                    System.out.println("---------BUSCAR EXEMPLAR---------");
                    System.out.println("Metodo de busca: ");
                    System.out.println("1. Titulo");
                    System.out.println("2. Autor");
                    System.out.println("3. Resumo");
                    System.out.print("Escolha: ");
                    int escolhaBusca = sc.nextInt();
                    sc.nextLine();
                    switch (escolhaBusca){
                        case 1:
                            System.out.print("Digite o titulo que deseja procurar: ");
                            String keyTitulo = sc.nextLine();
                            List<Exemplar> exemplaresEncontrados = biblioteca.pesquisarExemplar(keyTitulo);
                            System.out.println("Quantidade de exemplares encontrados: " + exemplaresEncontrados.size());
                            for (Exemplar x : exemplaresEncontrados){
                                System.out.println(x);
                            }
                            exemplaresEncontrados.clear();
                            break;
                        case 2:
                            System.out.print("Digite o autor que deseja procurar: ");
                            String keyAutor = sc.nextLine();
                            exemplaresEncontrados = biblioteca.pesquisarExemplar(keyAutor);
                            System.out.println("Quantidade de exemplares encontrados: " + exemplaresEncontrados.size());
                            for (Exemplar x : exemplaresEncontrados){
                                System.out.println(x);
                            }
                            exemplaresEncontrados.clear();
                            break;
                        case 3:
                            System.out.print("Digite o resumo que deseja procurar: ");
                            String keyResumo = sc.nextLine();
                            exemplaresEncontrados = biblioteca.pesquisarExemplar(keyResumo);
                            System.out.println("Quantidade de exemplares encontrados: " + exemplaresEncontrados.size());
                            for (Exemplar x : exemplaresEncontrados)
                                System.out.println(x);
                            exemplaresEncontrados.clear();
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente!");
                    }
                    break;
                case 5:
                    System.out.println("---------REGISTRO DE EMPRESTIMO---------");
                    System.out.print("Digite o CPF do usuario: ");
                    String cpfEmprestimo = sc.nextLine();
                    if (biblioteca.procurarCPF(cpfEmprestimo)){
                        System.out.print("Digite a data que o emprestimo foi feito: ");
                        String dataEmprestimoStr = sc.nextLine();

                        LocalDate dataEmprestimo = LocalDate.parse(dataEmprestimoStr, formato);

                        System.out.println("Acervo: ");
                        int i = 1;
                        for (Exemplar exemplar : biblioteca.getExemplares()){
                            System.out.println(i + ". " + exemplar);
                            i++;
                        }
                        System.out.print("Escolha do acervo (1 a " + biblioteca.getExemplares().size() + "): ");
                        int keyEmprestimo = sc.nextInt();
                        sc.nextLine();
                        if(biblioteca.emprestimoExemplar(cpfEmprestimo, keyEmprestimo, dataEmprestimo) == 1){
                            System.out.println("Emprestimo registrado com sucesso!");
                        }
                        else if(biblioteca.emprestimoExemplar(cpfEmprestimo, keyEmprestimo, dataEmprestimo) == 2){
                            System.out.println("Erro ao registrar, parece que esse usuario ja possui um emprestimo!");
                        }
                        else if(biblioteca.emprestimoExemplar(cpfEmprestimo, keyEmprestimo, dataEmprestimo) == 3){
                            System.out.println("Erro ao registrar, parece que esse exemplar ja foi emprestado!");
                        }
                        break;
                    }
                    else {
                        System.out.println("CPF nao foi encontrado!");
                    }
                    break;
                case 6:
                    System.out.println("---------REGISTRO DE DEVOLUCAO---------");
                    System.out.print("Digite o CPF do usuario: ");
                    String cpfDevolucao = sc.nextLine();
                    if (biblioteca.procurarCPF(cpfDevolucao)){
                        System.out.print("Data de devolucao (dd-MM-yyyy): ");
                        LocalDate dataDeDevolucao = LocalDate.parse(sc.nextLine(), formato);
                        System.out.println("Esse Usuario completou a leitura: ");
                        System.out.println("1. Sim");
                        System.out.println("2. Nao");
                        System.out.print("Escolha (1 ou 2): ");
                        int escolhaLeituraInt = sc.nextInt();
                        sc.nextLine();
                        boolean escolhaLeitura = false;
                        if (escolhaLeituraInt == 1){
                            escolhaLeitura = true;
                        }
                        else if(escolhaLeituraInt == 2){
                            escolhaLeitura = false;
                        }
                        if(biblioteca.devolucaoExemplar(cpfDevolucao, escolhaLeitura, dataDeDevolucao)){
                            System.out.println("Devolucao registrada com sucesso!");
                        }
                        else {
                            System.out.println("Erro ao registrar devolucao, reveja e tente novamente!");
                        }
                    }
                    else{
                        System.out.println("CPF nao encontrado!");
                    }
                    break;
                case 7:
                    biblioteca.imprimirRegistros();
                    break;
                case 8:
                    int i = 0;
                    if (biblioteca.rankingCriancas() != null){
                        for (Usuario x : biblioteca.rankingCriancas()) {
                            System.out.println(i + ". " + x.toStringRanking());
                            i++;
                        }
                    }
                    else{
                        System.out.println("Nao há registros de crianças no Ranking!");
                    }
                    break;
                case 9:
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}