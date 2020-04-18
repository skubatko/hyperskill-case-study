package ru.skubatko.dev.hyperskill.case6453;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Case6453 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        List<String> words = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            words.add(sc.nextLine());
        }

        String result = proceed(words);
        System.out.println(result);
    }

    static String proceed(List<String> words) {
        DAG<Character> dag = new DAG<>();

        String curr = words.get(0);
        for (int i = 1; i < words.size(); i++) {
            String next = words.get(i);
            updateDag(dag, curr, next);
            curr = next;
        }

        List<Character> result = new TopologicalSort<>(dag).sort();
        return result.stream().map(String::valueOf).collect(Collectors.joining());
    }

    private static void updateDag(DAG<Character> dag, String curr, String next) {
        for (int j = 0; j < next.length(); j++) {
            char nextChar = next.charAt(j);
            if (j == curr.length()) {
                dag.addVertex(nextChar);
                break;
            }

            char currChar = curr.charAt(j);
            if (nextChar != currChar) {
                dag.addEdge(currChar, nextChar);
                break;
            }
        }
    }

    static class TopologicalSort<T> {

        private DAG<T> dag;
        private List<T> topSort;
        private Map<T, Boolean> used;

        public TopologicalSort(DAG<T> dag) {
            this.dag = dag;
        }

        public List<T> sort() {
            topSort = new ArrayList<>();
            used = new HashMap<>();

            dag.adjList().forEach((k, v) -> used.put(k, false));

            for (T vertex : dag.adjList().keySet()) {
                if (!(used.get(vertex))) {
                    dfs(vertex);
                }
            }

            Collections.reverse(topSort);
            return topSort;
        }

        private void dfs(T v) {
            used.put(v, true);
            for (T to : dag.adjList().get(v)) {
                if (!(used.get(to))) {
                    dfs(to);
                }
            }
            topSort.add(v);
        }
    }

    static class DAG<T> {
        private Map<T, List<T>> adjList;

        public DAG() {
            adjList = new HashMap<>();
        }

        public void addVertex(T v) {
            adjList.put(v, new ArrayList<>());
        }

        public void addEdge(T a, T b) {
            adjList.putIfAbsent(a, new ArrayList<>());
            adjList.putIfAbsent(b, new ArrayList<>());
            adjList.get(a).add(b);
        }

        public Map<T, List<T>> adjList() {
            return adjList;
        }
    }
}
