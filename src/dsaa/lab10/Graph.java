package dsaa.lab10;

import java.util.*;

public class Graph {
    int[][] arr;
    // Collection to map Document to index of vertex
    HashMap<String, Integer> name2Int;
    HashMap<Integer, String> int2Name;

    // The argument type depend on a selected collection in the Main class
    public Graph(SortedMap<String,Document> internet){
        int size = internet.size();
        arr = new int[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (i != j)
                    arr[i][j] = -1;
                else
                    arr[j][j] = 0;

        name2Int = new HashMap<>();
        int2Name = new HashMap<>();

        int index = 0;
        for (String doc : internet.keySet()) {
            name2Int.put(doc, index);
            int2Name.put(index++, doc);
        }

        for (Document doc : internet.values())
            for (Link l : doc.link.values())
                if (internet.containsKey(l.ref))
                    if (!name2Int.get(doc.name).equals(name2Int.get(l.ref)))
                        arr[name2Int.get(doc.name)][name2Int.get(l.ref)] = l.weight;
    }

    // it uses colors like this:
    // 0 - white, 1 - gray, 2 - black
    public String bfs(String start) {
        if (!name2Int.containsKey(start)) return null;
        String bfs = "";
        int[] colors = new int[arr.length];
        Arrays.fill(colors, 0);
        Queue<Integer> queue = new LinkedList<>();
        colors[name2Int.get(start)] = 1;
        queue.add(name2Int.get(start));

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int i = 0; i < arr.length; i++)
                if (arr[current][i] > 0)
                    if (colors[i] == 0) {
                        queue.add(i);
                        colors[i] = 1;
                    }
            colors[current] = 2;
            bfs += int2Name.get(current) + ", ";
        }

        return bfs.substring(0, bfs.length()-2);
    }

    public String dfs(String start) {
        if (!name2Int.containsKey(start)) return null;
        StringBuilder dfs = new StringBuilder();
        boolean[] visited = new boolean[arr.length];

        dfsVisit(name2Int.get(start), visited, dfs);

        return dfs.substring(0, dfs.length()-2);
    }

    private void dfsVisit(int u, boolean[] visited, StringBuilder dfs) {
        if (!visited[u]) {
            visited[u] = true;
            dfs.append(int2Name.get(u)).append(", ");
            for (int i = 0; i < arr.length; i++)
                if (arr[u][i] >= 0 && !visited[i])
                    dfsVisit(i, visited, dfs);
        }
    }

    public int connectedComponents() {
        if (arr.length == 0) return 0;
        DisjointSetForest forest = new DisjointSetForest(arr.length);
        for (int i = 0; i < arr.length; i++)
            for (int j = 0; j < arr.length; j++)
                if (arr[i][j] > 0)
                    forest.union(i, j);
        return forest.countSets();
    }
}