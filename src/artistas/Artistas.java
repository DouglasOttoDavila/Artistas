package artistas;

import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.lang3.ArrayUtils;

public class Artistas {

    Commons com = new Commons(); // Importa classe "Commons"

    private int maxArtistas; // Define o máximo
    private int maxAlbuns; // Define o máximo

    // VARIÁVEIS
    private String[] artistas;
    private String[][] albuns;
    int posicao; // Variável para armazenar a posição do array
    int volArtistas = 0; // Quantidade de artistas iniciais
    int volAlbuns = 0;

    // METODOS
    public void inicializa(int maxArtistas, int maxAlbuns) throws InterruptedException{
        
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

    // Armazena um artista no sistema
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
            System.out.println("Cadastro do artista " +  this.artistas[this.volArtistas -1] + " foi criado com sucesso!");
            com.aguardaInput();
        }
    }

    // Remove um artista do sistema
    public void removeArtista(String nome) {
        int posicao = this.buscaArtista(nome);
        if (this.artistas == null || posicao < 0 || posicao >= this.artistas.length){
            com.limparTela();
            System.out.println("O artista não foi encontrado.");
            com.aguardaInput();
        } else {
            com.limparTela();
            this.removeAlbuns(nome);
            String[] novoArtistas = new String [this.artistas.length];
            for (int i = 0, k = 0; i < this.artistas.length; i++){
                if (i == posicao) {
                    continue;
                }
                novoArtistas[k++] = this.artistas[i];
            }
            this.artistas = novoArtistas;
            this.volArtistas--;
            System.out.println("Artista e seus álbuns foram removidos com sucesso!");
            com.aguardaInput();
        }
        /* System.out.println("Array de Artistas: " + Arrays.toString(this.artistas));
        System.out.println("Array de Albuns: " + Arrays.deepToString(this.albuns)); */
    }

    public void removeAlbuns(String nome) {
        int posicao = this.buscaArtista(nome);
        if (this.artistas == null || posicao < 0 || posicao >= this.artistas.length){
            com.limparTela();
            System.out.println("O álbum não foi encontrado.");
            com.aguardaInput();
        } else {
            List <String[]> novoAlbuns = new ArrayList<String[]>(Arrays.asList(this.albuns));
            novoAlbuns.remove(posicao);
            this.albuns = novoAlbuns.toArray(new String[][]{});
        }
    }

    // Busca um contato na agenda através do nome
    public int buscaArtista(String nome) {
        int posicao = -1;
        for (int i = 0; i < this.volArtistas; i++) {
            if (Arrays.asList(this.artistas[i]).contains(nome)){
                posicao = i;
            }
        }
        return posicao; //se não encontrar nenhum resultado, retorna -1
    }

    // Busca um contato na agenda através do nome
    public int[] buscaAlbum(String nome) {
        int posicaoAlbum = -1;
        int posicaoArtista = -1;
        for (int i = 0; i < this.maxArtistas; i++) {
            for(int j = 0; j < this.maxAlbuns; j++){
                if (Arrays.asList(this.albuns[i][j]).contains(nome)) {
                    posicaoAlbum = j;
                    posicaoArtista = i;
                }
            }    
        }
        return new int[] {posicaoAlbum, posicaoArtista};
    }

    public void listarArtistas() {
        System.out.println("| Cadastro de Artistas |\n");
        if (this.volArtistas == 0) {
            System.out.println("\nNão há artistas cadastrados!\n");
        } else {
            for (int i = 0; i < this.artistas.length; i++) {
                if (this.artistas[i] != null) {
                    String artista = this.artistas[i];
                    System.out.println("\nNome: " + artista);
                }
            }
            System.out.println("\nExistem " + this.volArtistas + " artistas cadastrados no sistema.");
        }
        com.aguardaInput();
    }

    // Mostra a agenda com todos seus contatos
    public void imprimeArtistas() {
        System.out.println("| Cadastro de Artistas |\n");
        if (this.volArtistas == 0) {
            System.out.println("\nNão há artistas cadastrados!\n");
        } else {
            for (int i = 0; i < this.artistas.length; i++) {
                if (this.artistas[i] != null) {
                    String artista = this.artistas[i];
                    System.out.println("\nNome: " + artista);
                    System.out.println("Álbuns do artista: ");
                    for (int j = 0; j < this.maxAlbuns; j++ ){
                        if (this.albuns[i][j] != null){
                            System.out.println(this.albuns[i][j]);
                        }
                    }
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
                    System.out.println("Álbuns do artista: ");
                    for (int j = 0; j < this.maxAlbuns; j++ ){
                        if (this.albuns[i][j] != null){
                            System.out.println("Álbum: " + this.albuns[i][j]);
                            System.out.println("\nNome: " + artista);
                        }
                    }
                }
            }
            System.out.println("\nExistem " + this.volArtistas + " artistas cadastrados no sistema.");
        }
        com.aguardaInput();
    }

    // Mostra um contato com base em sua posição na agenda
    public void imprimeArtista(int index) {
        if (!(index >= 0 && index < this.volArtistas)) {
            com.limparTela();
            System.out.println("Artista não encontrado.");
            com.aguardaInput(); 
        } else {
            com.limparTela();
            System.out.println("Artista/banda: " + this.artistas[index]);
            for (int i = 0; i < this.maxAlbuns; i++ ){
                if (this.albuns[index][i] != null){
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

    public int checarQuantidadeAlbuns(int posicaoArtista){
        int contagemAlbuns = 0;
        for (int i = 0; i < this.maxAlbuns; i++){
            if (this.albuns[posicaoArtista][i] != null){
                contagemAlbuns++;
            }
        }
        return contagemAlbuns;
    }

    // Armazena um artista no sistema
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
            System.out.println("Cadastro do álbum " +  this.albuns[posicaoArtista][contagemAlbuns] + " foi criado com sucesso!");
            this.volAlbuns++;
            com.aguardaInput();
        }
    }
}