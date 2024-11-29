package com.example;

import java.util.*;

class Vertice {
    int id;

    public Vertice(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Vertice vertice = (Vertice) obj;
        return id == vertice.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

class Grafo {
    private Map<Vertice, List<Vertice>> adjacencias;

    public Grafo() {
        adjacencias = new HashMap<>();
    }

    // Adiciona um vértice ao grafo
    public void adicionarVertice(Vertice v) {
        adjacencias.putIfAbsent(v, new ArrayList<>());
    }

    // Adiciona uma aresta entre dois vértices
    public void adicionarAresta(Vertice v1, Vertice v2) {
        adjacencias.putIfAbsent(v1, new ArrayList<>());
        adjacencias.putIfAbsent(v2, new ArrayList<>());
        adjacencias.get(v1).add(v2);
        adjacencias.get(v2).add(v1); // No caso de grafo não direcionado
    }

    // Função para obter o grau de um vértice
    public int d(Vertice v) {
        if (!adjacencias.containsKey(v)) {
            throw new IllegalArgumentException("Vértice não encontrado no grafo.");
        }
        return adjacencias.get(v).size(); // Retorna o número de arestas conectadas ao vértice
    }

    // Exemplo de impressão do grafo
    public void imprimirGrafo() {
        for (Vertice v : adjacencias.keySet()) {
            System.out.print("Vértice " + v.id + ": ");
            for (Vertice vizinho : adjacencias.get(v)) {
                System.out.print(vizinho.id + " ");
            }
            System.out.println();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        // Criando vértices
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        Vertice v3 = new Vertice(3);
        Vertice v4 = new Vertice(4);

        // Adicionando vértices ao grafo
        grafo.adicionarVertice(v1);
        grafo.adicionarVertice(v2);
        grafo.adicionarVertice(v3);
        grafo.adicionarVertice(v4);

        // Adicionando arestas (vértices conectados)
        grafo.adicionarAresta(v1, v2);
        grafo.adicionarAresta(v2, v3);
        grafo.adicionarAresta(v3, v4);
        grafo.adicionarAresta(v1, v3);

        // Verificando o grau de um vértice
        System.out.println("Grau do vértice 1: " + grafo.d(v1)); // Saída: 2
        System.out.println("Grau do vértice 2: " + grafo.d(v2)); // Saída: 2
        System.out.println("Grau do vértice 3: " + grafo.d(v3)); // Saída: 3
        System.out.println("Grau do vértice 4: " + grafo.d(v4)); // Saída: 1

        // Imprimindo o grafo
        grafo.imprimirGrafo();
    }
}
