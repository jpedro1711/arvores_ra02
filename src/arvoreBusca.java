public class arvoreBusca {
    private Node raiz;
    public int n = 0;

    public arvoreBusca() {
        this.raiz = null;
    }

    public Node inserir(int e) {
        if (this.raiz == null) {
            raiz = new Node();
            raiz.setInfo(e);
            n++;
            return raiz;
        }
        Node novo = new Node();
        novo.setInfo(e);
        Node aux = raiz;
        while (true) {
            if (novo.getInfo()< aux.getInfo()) { // Se for menor que a raiz
                // Esquerda
                if (aux.getEsquerdo()== null) { // Se o nó da esquerda for null
                    aux.setEsquerdo(novo);
                    n++;
                    return novo;
                }
                aux = aux.getEsquerdo(); // Senão, passa para o próximo nó (esquerda)
            } else { // Se for maior que a raiz
                // direita
                if (aux.getDireito() == null) { // Se o nó da direita for null
                    aux.setDireito(novo);
                    n++;
                    return novo;
                }
                aux = aux.getDireito(); // Senão, passa para o próximo nó (direita)
            }
        }

    }

    public Integer removeMaior() {
        if (raiz != null) {
            if (raiz.getDireito() == null) { // Senão tiver na direita
                if (raiz.getEsquerdo() != null) { // E tiver na esquerda
                    Node novo = raiz.getEsquerdo();
                    raiz.setEsquerdo(null);
                    raiz = novo;
                }
            }
            Node excluido = raiz;
            Node pai = raiz;
            while (excluido.getDireito() != null) {
                pai = excluido;
                excluido = excluido.getDireito();
            }
            pai.setDireito(null);
            return excluido.getInfo();
        }
        return null;
    }

    public Integer removeMenor() {
        if (raiz != null) {
            if (raiz.getEsquerdo() != null) {
                Node excluido = raiz;
                Node pai = excluido;

                while (excluido.getEsquerdo() != null) {
                    pai = excluido;
                    excluido = excluido.getEsquerdo();
                }
                pai.setEsquerdo(null);
                return excluido.getInfo();
            }
        }
        return null;
    }

    public Node busca(int e) {
        if (this.raiz == null) {
            return null;
        }
        if (this.raiz.getInfo() == e) {
            return this.raiz;
        }
        Node aux = raiz;
        while (true) {
            if (e < aux.getInfo()) { // Se for menor que a raiz
                // Esquerda
                if (aux.getEsquerdo() == null) {
                    return null;
                }
                if (aux.getEsquerdo().getInfo()== e) { // Se o nó da esquerda for null
                    return aux.getEsquerdo();
                }
                aux = aux.getEsquerdo(); // Senão, passa para o próximo nó (esquerda)
            } else { // Se for maior que a raiz
                // direita
                if (aux.getDireito() == null) {
                    return null;
                }
                if (aux.getDireito().getInfo() == e) { // Se o nó da direita for null
                    return aux.getDireito();
                }
                aux = aux.getDireito(); // Senão, passa para o próximo nó (direita)
            }
        }

    }

    public Node buscarPai(Node no) {
        if (raiz == null || raiz == no) {
            return null; // Não há pai se a árvore estiver vazia ou se o nó for a raiz
        }

        Node atual = this.raiz;
        Node pai = null;

        while (atual != null) {
            if (no.getInfo() < atual.getInfo()) {
                if (atual.getEsquerdo() == no) {
                    pai = atual;
                    break; // Encontrou o pai, pode sair do loop
                } else {
                    pai = atual;
                    atual = atual.getEsquerdo();
                }
            } else {
                if (atual.getDireito() == no) {
                    pai = atual;
                    break; // Encontrou o pai, pode sair do loop
                } else {
                    pai = atual;
                    atual = atual.getDireito();
                }
            }
        }

        return pai;
    }


    public void remover(int valor) {
        if (this.raiz != null) {
            if (this.raiz.getInfo() == valor) {
                Node dir = raiz.getDireito();
                dir.setEsquerdo(this.raiz.getEsquerdo());
                this.raiz.setEsquerdo(null);
                this.raiz = dir;
            }
            Node no = busca(valor);
            // Remover nó folha
            if (no.getEsquerdo() == null && no.getDireito() == null) {
                Node pai = buscarPai(no);
                if (pai.getEsquerdo() == no) pai.setEsquerdo(null);
                if (pai.getDireito() == no) pai.setDireito(null);
                no = null;
                return;
            }
            // Remover nó com 1 filho
            if (no.getEsquerdo() != null && no.getDireito() == null) {
                Node pai = buscarPai(no);
                if (pai.getDireito() == no) pai.setDireito(no.getEsquerdo());
                if (pai.getEsquerdo() == no) pai.setEsquerdo(no.getEsquerdo());
                return;
            }
            if (no.getEsquerdo() == null && no.getDireito() == null) {
                Node pai = buscarPai(no);
                if (pai.getDireito() == no) pai.setDireito(no.getDireito());
                if (pai.getEsquerdo() == no) pai.setEsquerdo(no.getDireito());
                return;
            }
            // Remover nó com 2 filhos
            if (no.getDireito() != null && no.getEsquerdo() != null) {
                // Pegar o mais a direita da sub-árvore esquerda
                Node aux = no.getEsquerdo();
                Node pai = null;
                while (aux.getDireito() != null) {
                    pai = aux;
                    aux = aux.getDireito();
                }
                if (pai == null) { // Quer dizer que o pai desse nó é a raiz
                    aux.setDireito(no.getDireito());
                    this.raiz.setDireito(aux);
                    return;
                }
                pai.setDireito(null);
                no.setInfo(aux.getInfo());
                return;
            }
        }
    }

    public void imprime(Node node, String prefix, boolean isLeft) {
        if (node != null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.getInfo());

            // Calculate new prefixes for left and right children
            String newPrefix = prefix + (isLeft ? "│ " : " ");

            // Recursively call for left and right children
            imprime(node.getDireito(), newPrefix, true);
            imprime(node.getEsquerdo(), newPrefix, false);
        } else {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + "Vazio");
        }
    }

    public Node getRaiz() {
        return raiz;
    }
}

