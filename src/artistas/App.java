package artistas;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        // Cria variáveis de assignment
        String nome;
        String album;
        int maxArtistas;
        int maxAlbuns;
        int posicao;

        Scanner scan = new Scanner(System.in); // Importa classe scanner (input)

        // Instancia as classes "Commons" (para métodos "limparTela()" e
        // "aguardaInput()") e "Agenda" (demais métodos)
        Commons com = new Commons();
        Artistas art = new Artistas();

        // Instancia variáveis de controle (opção do menu e posPessoa para execução do
        // método "buscaPessoa()")
        int opcao = 0;

        // inicialização
        System.out.println("Insira o volume máximo de artistas:");
        maxArtistas = scan.nextInt();
        System.out.println("Insira o volume máximo de álbuns:");
        maxAlbuns = scan.nextInt();

        art.inicializa(maxArtistas, maxAlbuns);

        // Início do menu de interação
        while (opcao >= 0) {
            com.limparTela();
            System.out.println("| Sistema de Controle Musical v1.0 |\n");
            System.out.println("Selecione a operação desejada:");
            System.out.println(
                    "\n1 - Cadastrar artista\n2 - Visualizar artista\n3 - Remover artista\n4 - Cadastrar álbum\n5 - Visualizar álbum\n6 - Listar todos artistas\n7 - Listar todos álbuns\n8 - Sair");
            opcao = scan.nextInt();
            if (opcao < 0 || opcao > 8) {
                System.out.println("Opção inválida.");
                opcao = 7;
            }

            // Opções do menu de interação
            switch (opcao) {
                case 1:
                    com.limparTela();
                    System.out.println("| CADASTRAR ARTISTA |");
                    System.out.println("Insira as informações do artista #" + (art.volArtistas + 1) + ":");
                    scan.nextLine();
                    System.out.println("Nome: ");
                    nome = scan.nextLine();
                    art.armazenarArtista(nome);
                    break;

                case 2:
                    com.limparTela();
                    System.out.println("| VISUALIZAR ARTISTA |");
                    System.out.println("Informe o nome do artista: ");
                    scan.nextLine();
                    nome = scan.nextLine();
                    posicao = art.buscaArtista(nome);
                    art.imprimeArtista(posicao);
                    com.limparTela();
                    com.aguardaInput();
                    break;

                case 3:
                    com.limparTela();
                    System.out.println("| REMOVER ARTISTA* |");
                    System.out.println("| Aviso: Esta operação irá remover também todos os álbuns do artista/banda. |");
                    System.out.println("Informe o nome do artista/banda a ser removido: ");
                    scan.nextLine();
                    nome = scan.nextLine();
                    com.limparTela();
                    art.removeArtista(nome);
                    break;

                case 4:
                    com.limparTela();
                    System.out.println("| CADASTRAR ÁLBUM |");
                    System.out.println("Informe o nome do artista: ");
                    scan.nextLine();
                    nome = scan.nextLine();
                    posicao = art.buscaArtista(nome);
                    System.out.println("Informe o nome do álbum: ");
                    album = scan.nextLine();
                    art.armazenarAlbum(posicao, album);
                    break;

                case 5:
                    com.limparTela();
                    System.out.println("| VISUALIZAR ÁLBUM |");
                    System.out.println("Informe o nome do álbum: ");
                    scan.nextLine();
                    nome = scan.nextLine();
                    int albuns[] = art.buscaAlbum(nome);
                    art.imprimeAlbum(albuns[0], albuns[1]);
                    com.limparTela();
                    com.aguardaInput();
                    break;

                case 6:
                    com.limparTela();
                    System.out.println("| LISTAR TODOS ARTISTAS |");
                    art.listarArtistas();
                    com.aguardaInput();
                    break;

                case 7:
                    com.limparTela();
                    System.out.println("| LISTAR TODOS ÁLBUNS |");
                    art.listarAlbuns();
                    break;

                case 8:
                    opcao = -1;
                    break;
            }
        }
        scan.close();
    }
}
