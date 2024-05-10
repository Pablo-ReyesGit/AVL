package ejemplo_arboles;

class Arbol {
Nodo raiz;
private int posicion;
private StringBuilder ordenProvisional;
private String Recorrido;
        

public Arbol() {
    this.raiz = null;
}
 private int altura( Nodo nodo )
    {
        if(nodo==null)
            return -1;
        else
            return nodo.altura;
    }

private int mayor(int n1, int n2)
    {
        if(n1 > n2)
            return n1;
        return n2;
    }
    /**
     * Rotación simple izquierda izquierda para el proceso de balanceo.
     * @param n1
     * @return 
     */
    private Nodo IzquierdaIzquierda(Nodo nodo) {
    Nodo nuevoPadre = nodo.getIzquierda();
    nodo.setIzquierda(nuevoPadre.getDerecha());
    nuevoPadre.setDerecha(nodo);
    nodo.setAltura(mayor(altura(nodo.getIzquierda()), altura(nodo.getDerecha())) + 1);
    nuevoPadre.setAltura(mayor(altura(nuevoPadre.getIzquierda()), altura(nodo)) + 1);
    return nuevoPadre;
}

private Nodo DerechaDerecha(Nodo nodo) {
    Nodo nuevoPadre = nodo.getDerecha();
    nodo.setDerecha(nuevoPadre.getIzquierda());
    nuevoPadre.setIzquierda(nodo);
    nodo.setAltura(mayor(altura(nodo.getIzquierda()), altura(nodo.getDerecha())) + 1);
    nuevoPadre.setAltura(mayor(altura(nodo), altura(nuevoPadre.getDerecha())) + 1);
    return nuevoPadre;
}

private Nodo IzquierdaDerecha(Nodo nodo) {
    nodo.setIzquierda(DerechaDerecha(nodo.getIzquierda()));
    return IzquierdaIzquierda(nodo);
}

private Nodo DerechaIzquierda(Nodo nodo) {
    nodo.setDerecha(IzquierdaIzquierda(nodo.getDerecha()));
    return DerechaDerecha(nodo);
}
       

    private int balance(Nodo nodo) {
        return nodo == null ? 0 : altura(nodo.getIzquierda()) - altura(nodo.getDerecha());
    }

public boolean existe(String busqueda) {
return existe(this.raiz, busqueda);
}

private boolean existe(Nodo n, String busqueda) {
if (n == null) {
return false;
}
if (n.getDato().equals(busqueda)) {
return true;
} else if (busqueda.compareTo(n.getDato()) < 0) {
    posicion =+ 1;
    Recorrido += "- L";
return existe(n.getIzquierda(), busqueda);
} else {
    posicion =+ 1;
    Recorrido += "- R";
return existe(n.getDerecha(), busqueda);   
}
}

    public void insertar(String valor) {
    raiz = insertar(valor, raiz);
}

private Nodo insertar(String valor, Nodo raiz) {
    if (raiz == null) {
        raiz = new Nodo(valor);
    } else if (valor.compareTo(raiz.getDato()) < 0) {
        raiz.setIzquierda(insertar(valor, raiz.getIzquierda()));
        if (altura(raiz.getDerecha()) - altura(raiz.getIzquierda()) == -2) {
            if (valor.compareTo(raiz.getIzquierda().getDato()) < 0)
                raiz = IzquierdaIzquierda(raiz);
            else
                raiz = IzquierdaDerecha(raiz);
        }
    } else if (valor.compareTo(raiz.getDato()) > 0) {
        raiz.setDerecha(insertar(valor, raiz.getDerecha()));
        if (altura(raiz.getDerecha()) - altura(raiz.getIzquierda()) == 2) {
            if (raiz.getDerecha() != null && valor.compareTo(raiz.getDerecha().getDato()) > 0)
                raiz = DerechaDerecha(raiz);
            else
                raiz = DerechaIzquierda(raiz);
        }
    } else {
        System.err.println("No se permiten los valores duplicados: \"" + valor + "\".");
    }

    raiz.setAltura(mayor(altura(raiz.getIzquierda()), altura(raiz.getDerecha())) + 1);
    return raiz;
}

private void preorden(Nodo n) {
if (n != null) {
ordenProvisional.append(n.dato).append("\n");
preorden(n.getIzquierda());
preorden(n.getDerecha());
}
}

private void inorden(Nodo n) {
if (n != null) {
inorden(n.getIzquierda());
ordenProvisional.append(n.dato).append("\n");
inorden(n.getDerecha());
}
}

private void postorden(Nodo n) {
if (n != null) {
postorden(n.getIzquierda());
postorden(n.getDerecha());
ordenProvisional.append(n.dato).append("\n");
}
}

public StringBuilder preorden() {
    ordenProvisional = new StringBuilder();
    this.preorden(this.raiz);
    return ordenProvisional;
}

public StringBuilder inorden() {
    ordenProvisional = new StringBuilder();
    this.inorden(this.raiz);
    return ordenProvisional;
}

public StringBuilder postorden() {
    ordenProvisional = new StringBuilder();
    this.postorden(this.raiz);
    return ordenProvisional;
}

public void vaciar() {
    raiz = null; // Elimina la raíz del árbol, lo que eventualmente eliminará todos los nodos
}

public String Recorrido(){
    return Recorrido;
    
}

private Nodo encontrarMinimo(Nodo nodo) {
    while (nodo.getIzquierda() != null) {
        nodo = nodo.getIzquierda();
    }
    return nodo;
}

public void graficar(String path) {
        raiz.graficar(path);
    }

public String buscar(String busqueda) {
    return buscar(this.raiz, busqueda);
}

private String buscar(Nodo n, String busqueda) {
    if (n == null) {
        return "";
    }
    if (n.getDato().equals(busqueda)) {
        return "";
    } else if (busqueda.compareTo(n.getDato()) < 0) {
        return "L-" + buscar(n.getIzquierda(), busqueda);
    } else {
        return "R-" + buscar(n.getDerecha(), busqueda);
    }
}

public Nodo eliminar(String dato) {
    return eliminar(raiz, dato);
}

   private Nodo eliminar(Nodo nodo, String dato) {
    if (nodo == null) {
        return null;
    }

    if (dato.compareTo(nodo.getDato()) < 0) {
        nodo.setIzquierda(eliminar(nodo.getIzquierda(), dato));
    } else if (dato.compareTo(nodo.getDato()) > 0) {
        nodo.setDerecha(eliminar(nodo.getDerecha(), dato));
    } else {
        // Nodo a eliminar encontrado

        // Caso 1: Nodo con un solo hijo o sin hijos
        if (nodo.getIzquierda() == null || nodo.getDerecha() == null) {
            Nodo temp = null;
            if (nodo.getIzquierda() == null) {
                temp = nodo.getDerecha();
            } else {
                temp = nodo.getIzquierda();
            }

            // Caso de nodo sin hijos
            if (temp == null) {
                temp = nodo;
                nodo = null;
            } else {
                nodo = temp; // Copia el contenido del hijo no nulo
            }
        } else {
            // Caso 2: Nodo con dos hijos
            Nodo temp = encontrarMinimo(nodo.getDerecha());
            nodo.dato = temp.dato;
            nodo.setDerecha(eliminar(nodo.getDerecha(), temp.getDato()));
        }
    }

    // Si el árbol tenía solo un nodo
    if (nodo == null) {
        return nodo;
    }

    // Actualiza la altura del nodo actual
    if(altura(raiz.getDerecha()) - altura(raiz.getIzquierda()) == -2) {
            if(dato.compareTo(raiz.getIzquierda().getDato()) < 0)
                raiz = IzquierdaIzquierda(raiz);
            else
                raiz = IzquierdaDerecha(raiz);
        }
    if(altura(raiz.getDerecha()) - altura(raiz.getIzquierda()) == 2) {
            if(dato.compareTo(raiz.getDerecha().getDato()) > 0)
                raiz = DerechaDerecha(raiz);
            else
                raiz = DerechaIzquierda(raiz);
        }

    return nodo;
}
}


