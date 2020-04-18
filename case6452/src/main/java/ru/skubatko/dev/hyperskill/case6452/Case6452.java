package ru.skubatko.dev.hyperskill.case6452;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Case6452 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();

        String[] variables = new String[n];
        for (int i = 0; i < n; i++) {
            variables[i] = sc.nextLine();
        }

        String[] constraints = new String[m];
        for (int i = 0; i < m; i++) {
            constraints[i] = sc.nextLine();
        }

        int result = proceed(variables, constraints);
        if (result == 0) {
            System.out.println("NO");
        } else {
            System.out.println(result);
        }
    }

    static int proceed(String[] variables, String[] constraints) {
        DAG<String> dag = new DAG<>();

        for (String variable : variables) {
            dag.addVertex(variable);
        }

        for (String constraint : constraints) {
            if (constraint.contains("<")) {
                String[] buf = constraint.split("<");
                dag.addEdge(buf[0], buf[1]);
            } else {
                String[] buf = constraint.split(">");
                dag.addEdge(buf[1], buf[0]);
            }
        }

        boolean dagCycled = dag.vertexSet()
                                    .stream()
                                    .noneMatch(v -> dag.adjList().get(v).isEmpty());
        if (dagCycled) {
            return 0;
        }

        return new MinCalc<>(dag).get();
    }

    static class MinCalc<T> {
        private DAG<T> dag;
        private Map<T, Boolean> used;

        public MinCalc(DAG<T> dag) {
            this.dag = dag;
        }

        public int get() {
            used = new HashMap<>();
            dag.adjList().forEach((k, v) -> used.put(k, false));

            int result = 0;
            for (T vertex : dag.vertexSet()) {
                if (!(used.get(vertex))) {
                    dfs(vertex);
                    result++;
                }
            }

            return result;
        }

        private void dfs(T v) {
            used.put(v, true);

            for (T to : dag.adjList().get(v)) {
                if (!(used.get(to))) {
                    dfs(to);
                }
            }
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
            adjList.get(a).add(b);
        }

        public Map<T, List<T>> adjList() {
            return adjList;
        }

        public Set<T> vertexSet() {
            return adjList.keySet();
        }
    }
}
