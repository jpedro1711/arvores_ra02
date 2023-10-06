public class arvoreAVL {
    private Node raiz;
    public int n = 0;

    public arvoreAVL() {
        this.raiz = null;
    }

    public void add(int e) {
        this.raiz = inserir(this.raiz, e);
    }

    public Node inserir(Node raiz, int e) {
        if (raiz == null) {
            Node novo = new Node();
            novo.setInfo(e);
            n++;
            return novo;
        }
        if (e < this.raiz.getInfo()) {
            raiz.setEsquerdo(inserir(raiz.getEsquerdo(), e));
        } else {
            raiz.setDireito(inserir(raiz.getDireito(), e));
        }
        return balancear(raiz);
    }



    public Node remover(Node raiz, int valor) {
        if (raiz == null) {
            return raiz;
        }
        if (valor < raiz.getInfo()) {
            raiz.setEsquerdo(remover(raiz.getEsquerdo(), valor));
        }
        else if (valor > raiz.getInfo()) {
            raiz.setDireito(remover(raiz.getDireito(), valor));
        }
        else { // valor == raiz.getInfo()
            // 2 filhos
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
        return balancear(raiz);
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

    public void percorrePreOrder(Node raiz) { // Percorre pre order
        if (raiz != null) {
            System.out.print(raiz.getInfo()+ " "); // Visite a raiz da arvora
            percorrePreOrder(raiz.getEsquerdo()); // Percorra a subarvore da esquerda
            percorrePreOrder(raiz.getDireito()); // Percorra a subarvore da direita
        }
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

    public void percorreInOrder(Node raiz) {
        if (raiz != null) {
            percorreInOrder(raiz.getEsquerdo()); // percorra subarvora da esquerda
            System.out.print(raiz.getInfo() + " "); // Visite raiz
            percorreInOrder(raiz.getDireito()); // Percorra subarvore da direita
        }
    }

    public Node getRaiz() {
        return raiz;
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

    public Node balancear(Node node) {
        int fb = calcularFB(node);
        if (fb > 1 || fb < -1) {
            if (fb > 1) {
                // está desbalanceado para esq
                if (calcularFB(node.getEsquerdo()) > 0) { // Está desbalanceado por causa de nó a esq na subarvore
                    // Rotação à direita
                    return rotacaoDireita(node);
                } else {
                    // Rotação Dupla à Direita
                    return rotacaoEsquerdaDireita(node);
                }
            } else {
                // Está desbalanceado para dir
                if (calcularFB(node.getDireito()) < 0) { // Está desbalanceado por causa de nó a dir na subarvore
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



    public void percorrePosOrder(Node raiz) {
        if (raiz != null) {
            percorrePosOrder(raiz.getEsquerdo()); // percorra subarvore da esquerda
            percorrePosOrder(raiz.getDireito()); // percorra subarvore da direita
            System.out.print(raiz.getInfo() + " "); // visite a raiz
        }
    }
}
