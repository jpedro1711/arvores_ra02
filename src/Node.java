public class Node {
    private Node esquerdo;
    private Integer info;
    private Node direito;

    public Node() {
        esquerdo = null;
        info = null;
        direito = null;
    }

    public Node getEsquerdo() {
        return esquerdo;
    }

    public void setEsquerdo(Node esquerdo) {
        this.esquerdo = esquerdo;
    }

    public Integer getInfo() {
        return info;
    }

    public void setInfo(Integer info) {
        this.info = info;
    }

    public Node getDireito() {
        return direito;
    }

    public void setDireito(Node direito) {
        this.direito = direito;
    }
}
