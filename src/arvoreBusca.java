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

    public boolean buscar(Node raiz, int info) {
        if (raiz != null) {
            if (info == raiz.getInfo()) {
                return true;
            }
            if (raiz.getDireito() != null && info == raiz.getDireito().getInfo()) {
                return true;
            }
            if (raiz.getEsquerdo() != null && info == raiz.getEsquerdo().getInfo()) {
                return true;
            }
            if (info < this.raiz.getInfo()) {
                return buscar(raiz.getEsquerdo(), info);
            } else {
                return buscar(raiz.getDireito(), info);
            }
        }

        return false;
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


    public Node remover(Node raiz, int valor) {
        if (raiz == null) {
            return raiz;
        }
        if (raiz.getInfo() == valor) {
            if (raiz.getDireito() == null && raiz.getEsquerdo() == null) {
                this.raiz = null;
                return null;
            } else if (raiz.getDireito() != null) {
                this.raiz = this.raiz.getDireito();
                return this.raiz;
            } else if (raiz.getDireito() == null && raiz.getEsquerdo() != null) {
                this.raiz = this.raiz.getEsquerdo();
                return this.raiz;
            }
        }
        if (valor < raiz.getInfo()) {
            raiz.setEsquerdo(remover(raiz.getEsquerdo(), valor));
        }
        else if (valor > raiz.getInfo()) {
            raiz.setDireito(remover(raiz.getDireito(), valor));
        }
        else { // valor == raiz.getInfo()
            if (raiz.getInfo() == this.raiz.getInfo()) {
                Node dir = this.raiz.getDireito();
                Node esq = this.raiz.getEsquerdo();
                if (dir != null) {
                    dir.setEsquerdo(raiz.getEsquerdo());
                    this.raiz = dir;
                }
                if (dir == null && esq != null) {
                    this.raiz.setEsquerdo(null);
                    this.raiz = esq;
                }
                if (dir == null && esq == null) {
                    this.raiz = null;
                }
            }
            // 2 filhos
            if (raiz.getEsquerdo() != null && raiz.getDireito() != null) {
                Node aux = raiz.getEsquerdo();
                Node paiAux = raiz;
                while (aux.getDireito() != null) {
                    paiAux = aux;
                    aux = aux.getDireito(); // Menor elemento da sub-arvore dir
                }
                paiAux.setDireito(null);
                raiz.setInfo(aux.getInfo());
            } else if (raiz.getEsquerdo() != null && raiz.getDireito() == null) {
                // 1 filho na esq
                raiz = raiz.getEsquerdo();
                // 1 filho na dir
            } else if (raiz.getEsquerdo() == null && raiz.getDireito() != null) {
                raiz = raiz.getDireito();
            } else if (raiz.getEsquerdo() == null && raiz.getDireito() == null) {
                // Sem filhos
                raiz = null;
            }
        }
        return raiz;
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

