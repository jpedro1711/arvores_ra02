public class arvoreAVL {
    private Node raiz;
    public int n = 0;

    public arvoreAVL() {
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
                    percorrerEsquerda(this.raiz.getDireito(), this.raiz);
                    return novo;
                }
                aux = aux.getEsquerdo(); // Senão, passa para o próximo nó (esquerda)
            } else { // Se for maior que a raiz
                // direita
                if (aux.getDireito() == null) { // Se o nó da direita for null
                    aux.setDireito(novo);
                    n++;
                    percorrerDireita(this.raiz.getEsquerdo(), this.raiz);
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

    public Node rotacaoEsquerda(Node raiz) {
        Node novaRaiz = raiz.getDireito(); // Filho a direita do no
        Node temp = novaRaiz.getEsquerdo();; // Filho a esquerda do filho direito
        raiz.setDireito(temp);
        novaRaiz.setEsquerdo(raiz);
        if (raiz == this.raiz) {
            this.raiz = novaRaiz;
        }
        return novaRaiz;
    }

    public Node rotacaoDireita(Node raiz) {
        Node novaRaiz = raiz.getEsquerdo();
        Node temp = novaRaiz.getDireito();
        raiz.setEsquerdo(temp);
        novaRaiz.setDireito(raiz);
        if (raiz == this.raiz) {
            this.raiz = novaRaiz;
        }
        return novaRaiz;
    }

    public Node rotacaoEsquerdaDireita(Node no) {
        no.setEsquerdo(rotacaoEsquerda(no.getEsquerdo()));
        return rotacaoDireita(no);
    }

    public Node rotacaoDireitaEsquerda(Node no) {
        no.setDireito(rotacaoDireita(no.getDireito()));
        return rotacaoEsquerda(no);
    }

    public int calcularAltura(Node no) {
        int altura = 0;
        Node atual = no;
        if (no.getEsquerdo() == null && no.getDireito() == null) {
            return altura;
        }
        else if (no.getEsquerdo() != null && no.getDireito() == null) {
            while (atual.getEsquerdo() != null) {
                altura++;
                atual = atual.getEsquerdo();
            }
            return altura;
        }
        else if (no.getEsquerdo() == null && no.getDireito() != null) {
            while (atual.getDireito() != null) {
                altura++;
                atual = atual.getDireito();
            }
            return altura;
        } else {
            int alturaDireita = 0;
            while (atual.getDireito() != null) {
                alturaDireita++;
                atual = atual.getDireito();
            }
            atual = no;
            int alturaEsquerda = 0;
            while (atual.getEsquerdo() != null) {
                alturaEsquerda++;
                atual = atual.getEsquerdo();
            }
            // Pega a maior altura entre direita e esquerda
            if (alturaDireita > alturaEsquerda) {
                return alturaDireita;
            } else {
                return alturaEsquerda;
            }
        }
    }

    public int calcularFB(Node no) {
        if (no == null) return 0;
        if (no.getEsquerdo() == null && no.getDireito() == null) {
            return 0;
        } else if (no.getEsquerdo() == null && no.getDireito() != null) {
            // -1 porque é necessário adicionar a altura da raiz até o nó
            return 0 - calcularAltura(no.getDireito()) - 1;
        } else if (no.getEsquerdo() != null && no.getDireito() == null) {
            // +1 porque é necessário adicionar a altura da raiz até o nó
            return 1 + calcularAltura(no.getEsquerdo()) - 0;
        } else {
            return calcularAltura(no.getEsquerdo()) - calcularAltura(no.getDireito());
        }
    }

    public Node balancear(Node node) {
        int fb = calcularFB(node);
        if (fb > 1 || fb < -1) {
            if (fb > 1) {
                // está desbalanceado para esq
                if (calcularFB(node.getEsquerdo()) > 0) {
                    // Rotação à direita
                    return rotacaoDireita(node);
                } else {
                    // Rotação Dupla à Direita
                    return rotacaoEsquerdaDireita(node);
                }
            } else {
                // Está desbalanceado para dir
                if (calcularFB(node.getDireito()) < 0) {
                    // Rotação à esquerda
                    return rotacaoEsquerda(node);
                } else {
                    // Rotação Dupla à Esquerda
                    return rotacaoDireitaEsquerda(node);
                }
            }
        }
        return node;
    }


    public void percorrerDireita(Node raiz, Node pai) {
        if (raiz != null) {
            percorrerDireita(raiz.getDireito(), raiz);
            if (calcularFB(raiz) > 1 || calcularFB(raiz) < -1) {
                if (pai != null) {
                    pai.setDireito(balancear(raiz));
                } else {
                    // Quer dizer que esse elemento não tem pai, ou seja, é raiz
                    while (calcularFB(this.raiz) > 1 || calcularFB(this.raiz) < -1) {
                        this.raiz = balancear(this.raiz);
                    }
                }
            }
        }
    }


    public void percorrerEsquerda(Node raiz, Node pai) {
        if (raiz != null) {
            percorrerEsquerda(raiz.getEsquerdo(), raiz);
            if (calcularFB(raiz) > 1 || calcularFB(raiz) < -1) {
                if (pai != null) {
                    pai.setEsquerdo(balancear(raiz));
                }
                else {
                    while (calcularFB(this.raiz) > 1 || calcularFB(this.raiz) < -1) {
                        this.raiz = balancear(this.raiz);
                    }
                }
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
                percorrerEsquerda(this.raiz.getDireito(), this.raiz);
                percorrerDireita(this.raiz.getEsquerdo(), this.raiz);
                return;
            }
            // Remover nó com 1 filho
            if (no.getEsquerdo() != null && no.getDireito() == null) {
                // Se o filho estiver na esquerda
                Node pai = buscarPai(no);
                if (pai.getDireito() == no) pai.setDireito(no.getEsquerdo());
                if (pai.getEsquerdo() == no) pai.setEsquerdo(no.getEsquerdo());
                percorrerEsquerda(this.raiz.getDireito(), this.raiz);
                percorrerDireita(this.raiz.getEsquerdo(), this.raiz);
                return;
            }
            if (no.getEsquerdo() == null && no.getDireito() != null) {
                // Se o filho estiver na direita
                Node pai = buscarPai(no);
                if (pai.getDireito() == no) pai.setDireito(no.getDireito());
                if (pai.getEsquerdo() == no) pai.setEsquerdo(no.getDireito());
                percorrerEsquerda(this.raiz.getDireito(), this.raiz);
                percorrerDireita(this.raiz.getEsquerdo(), this.raiz);
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
                    percorrerEsquerda(this.raiz.getDireito(), this.raiz);
                    percorrerDireita(this.raiz.getEsquerdo(), this.raiz);
                    return;
                }
                pai.setDireito(null);
                no.setInfo(aux.getInfo());
                percorrerEsquerda(this.raiz.getDireito(), this.raiz);
                percorrerDireita(this.raiz.getEsquerdo(), this.raiz);
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
