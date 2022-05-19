package artistas;

public class Commons {

    public void limparTela() {
        for (int limpar = 0; limpar < 1000; limpar++) {
            System.out.println("\b");
        }
    }

    public void aguardaInput() {
        System.out.println("\n<< Pressione ENTER para continuar >>");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }

}
