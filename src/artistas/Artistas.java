package artistas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Artistas {

    Commons com = new Commons();

    private int maxArtistas;
    private int maxAlbuns;
    private String[] artistas;
    private String[][] albuns;
    int posicao; 
    int volArtistas = 0;
    int volAlbuns = 0;

    public void inicializa(int maxArtistas, int maxAlbuns) throws InterruptedException {

        com.limparTela();
        this.maxArtistas = maxArtistas;
        this.maxAlbuns = maxAlbuns;
        this.artistas = new String[maxArtistas];
        this.albuns = new String[maxArtistas][maxAlbuns];

        System.out.println("| Inicializando o sistema...\n");
        System.out.println("| Artistas cadastrados: " + this.volArtistas);
        System.out.println("| Álbuns cadastrados: " + this.volAlbuns);
        System.out.println("| Limite de artistas: " + this.maxArtistas);
        System.out.println("| Limite de álbuns: " + this.maxAlbuns);
        com.aguardaInput();
    }

    public void armazenarArtista(String nome) {
        if (this.volArtistas >= this.maxArtistas) {
            com.limparTela();
            System.out.println("Não é possível adicionar mais de " + this.maxArtistas + " artistas no sistema.");
            System.out.println("Remova algum artista antes de adicionar um novo.");
            com.aguardaInput();
        } else {
            this.artistas[this.volArtistas] = nome;
            this.volArtistas++;
            com.limparTela();
            System.out
                    .println("Cadastro do artista " + this.artistas[this.volArtistas - 1] + " foi criado com sucesso!");
            com.aguardaInput();
        }
    }

    public void removeArtista(String nome) {
        int posicao = this.buscaArtista(nome);
        if (this.artistas == null || posicao < 0 || posicao >= this.artistas.length) {
            com.limparTela();
            System.out.println("O artista não foi encontrado.");
            com.aguardaInput();
        } else {
            com.limparTela();
            this.removeAlbuns(nome);
            String[] novoArtistas = new String[this.artistas.length];
            for (int i = 0, k = 0; i < this.artistas.length; i++) {
                if (i == posicao) {
                    continue;
                }
                novoArtistas[k++] = this.artistas[i];
            }
            this.artistas = novoArtistas;
            this.volArtistas--;
            this.volAlbuns = this.volAlbuns - this.checarQuantidadeAlbuns(posicao);
            System.out.println("Artista e seus álbuns foram removidos com sucesso!");
            com.aguardaInput();
        }
    }

    public void removeTodosArtistas() {
        com.limparTela();
        if (this.volArtistas <= 0){
            com.limparTela();
            System.out.println("Nenhum artista a ser removido...");
            com.aguardaInput();
        } else {
            this.removeTodosAlbuns();
            List<String> novoArtistas = new ArrayList<String>(Arrays.asList(this.artistas));
            for (int i = 0; i < novoArtistas.size(); i++) {
                novoArtistas.remove(i);
            }
            this.artistas = novoArtistas.toArray(new String[] {});
            this.volArtistas = 0;
            System.out.println("Todos artistas e seus álbuns foram removidos com sucesso!");
            com.aguardaInput();
        }
    }

    public void removeTodosAlbuns() {
        if (this.volAlbuns > 0) {
            List<List<String>> novoAlbuns = Arrays.stream(this.albuns).map(Arrays::asList).collect(Collectors.toList());
            novoAlbuns.clear();
            this.albuns = novoAlbuns.toArray(new String[][] {});
            this.volAlbuns = 0;
        }
    }

    public void removeAlbuns(String nome) {
        int posicao = this.buscaArtista(nome);
        if (this.artistas == null || posicao < 0 || posicao >= this.artistas.length) {
            com.limparTela();
            System.out.println("O álbum não foi encontrado.");
            com.aguardaInput();
        } else {
            List<String[]> novoAlbuns = new ArrayList<String[]>(Arrays.asList(this.albuns));
            novoAlbuns.remove(posicao);
            this.albuns = novoAlbuns.toArray(new String[][] {});
        }
    }

    public int buscaArtista(String nome) {
        int posicao = -1;
        for (int i = 0; i < this.artistas.length; i++) {
            if (Arrays.asList(this.artistas[i]).contains(nome)) {
                posicao = i;
            }
        }
        return posicao;
    }

    public int[] buscaAlbum(String nome) {
        int posicaoAlbum = -1;
        int posicaoArtista = -1;
        for (int i = 0; i < this.maxArtistas; i++) {
            for (int j = 0; j < this.maxAlbuns; j++) {
                if (Arrays.asList(this.albuns[i][j]).contains(nome)) {
                    posicaoAlbum = j;
                    posicaoArtista = i;
                }
            }
        }
        return new int[] { posicaoAlbum, posicaoArtista };
    }

    public void listarArtistas() {
        System.out.println("| Cadastro de Artistas |\n");
        if (this.volArtistas == 0) {
            System.out.println("\nNão há artistas cadastrados!\n");
        } else {
            for (int i = 0; i < this.artistas.length; i++) {
                if (this.artistas[i] != null) {
                    String artista = this.artistas[i];
                    System.out.println(i + 1 + ") Artista/Banda: " + artista);
                }
            }
            System.out.println("\nExistem " + this.volArtistas + " artistas cadastrados no sistema.");
        }
        com.aguardaInput();
    }

    public void listarAlbuns() {
        if (this.volAlbuns == 0) {
            System.out.println("\nNão há álbuns cadastrados!\n");
        } else {
            for (int i = 0; i < this.artistas.length; i++) {
                if (this.artistas[i] != null) {
                    String artista = this.artistas[i];
                    for (int j = 0; j < this.albuns.length; j++) {
                        if (this.albuns[i][j] != null) {
                            String album = this.albuns[i][j];
                            System.out.println("Álbum: " + album);
                            System.out.println("Artista: " + artista + "\n");
                        }
                    }
                }
            }
            System.out.println("\nExistem " + this.volAlbuns + " álbuns cadastrados no sistema.");
        }
        com.aguardaInput();
    }

    public void imprimeArtista(int index) {
        if (!(index >= 0 && index < this.volArtistas)) {
            com.limparTela();
            System.out.println("Artista não encontrado.");
            com.aguardaInput();
        } else {
            com.limparTela();
            System.out.println("Artista/banda: " + this.artistas[index]);
            for (int i = 0; i < this.maxAlbuns; i++) {
                if (this.albuns[index][i] != null) {
                    System.out.println(this.albuns[index][i]);
                }
            }
            com.aguardaInput();
        }
    }

    public void imprimeAlbum(int posAlbum, int posArtista) {
        if (!(posAlbum >= 0 && posAlbum < this.volAlbuns)) {
            com.limparTela();
            System.out.println("Álbum não encontrado.");
            com.aguardaInput();
        } else {
            com.limparTela();
            System.out.println("Álbum: " + this.albuns[posArtista][posAlbum]);
            System.out.println("Artista/banda: " + this.artistas[posArtista]);
            com.aguardaInput();
        }
    }

    public int checarQuantidadeAlbuns(int posicaoArtista) {
        int contagemAlbuns = 0;
        for (int i = 0; i < this.albuns.length; i++) {
            if (this.albuns[posicaoArtista][i] != null) {
                contagemAlbuns++;
            }
        }
        return contagemAlbuns;
    }

    public void armazenarAlbum(int posicaoArtista, String nomeAlbum) {
        if (this.volAlbuns >= this.maxAlbuns) {
            com.limparTela();
            System.out.println("Não é possível adicionar mais de " + this.maxAlbuns + " álbuns no sistema.");
            System.out.println("Remova algum álbum antes de adicionar um novo.");
            com.aguardaInput();
        } else if (posicaoArtista < 0) {
            com.limparTela();
            System.out.println("Artista não encontrado.");
            com.aguardaInput();
        } else {
            int contagemAlbuns = this.checarQuantidadeAlbuns(posicaoArtista);
            this.albuns[posicaoArtista][contagemAlbuns] = nomeAlbum;
            com.limparTela();
            System.out.println(
                    "Cadastro do álbum " + this.albuns[posicaoArtista][contagemAlbuns] + " foi criado com sucesso!");
            this.volAlbuns++;
            com.aguardaInput();
        }
    }

    // DEBUG METHOD
    public void listarArtistaEalbuns() {
        System.out.println("| Cadastro de Artistas |\n");
        if (this.volArtistas == 0) {
            System.out.println("\nNão há artistas cadastrados!\n");
        } else {
            for (int i = 0; i < this.artistas.length; i++) {
                if (this.artistas[i] != null) {
                    String artista = this.artistas[i];
                    System.out.println("\nNome: " + artista);
                    System.out.println("Álbuns do artista: ");
                    for (int j = 0; j < this.maxAlbuns; j++) {
                        if (this.albuns[i][j] != null) {
                            System.out.println(this.albuns[i][j]);
                        }
                    }
                }
            }
            System.out.println("\nExistem " + this.volArtistas + " artistas cadastrados no sistema.");
        }
        com.aguardaInput();
    }
}