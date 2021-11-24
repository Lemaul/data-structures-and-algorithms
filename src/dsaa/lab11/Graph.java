package dsaa.lab11;

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
                    arr[i][j] = Integer.MAX_VALUE;
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

    public String DijkstraSSSP(String start) {
        if (!name2Int.containsKey(start)) return null;
        StringBuilder dj = new StringBuilder();

        int startInt = name2Int.get(start);

        HashSet<Integer> VT = new HashSet<>();
        VT.add(startInt);
        int[] dst = new int[arr.length];
        Arrays.fill(dst, Integer.MAX_VALUE);
        int[] predecessor = new int[arr.length];
        predecessor[startInt] = -1;

        dst[startInt] = 0;
        for (int u = 0; u < arr.length; u++)
            if (!VT.contains(u))
                dst[u] = arr[startInt][u];

        while (VT.size() != arr.length) {
            int u = minDst(dst, VT);
            VT.add(u);
            for (int v = 0; v < arr.length; v++) {
                if (arr[u][v] < Integer.MAX_VALUE && dst[u] != Integer.MAX_VALUE) {
                    if (dst[v] > dst[u] + arr[u][v]) {
                        dst[v] = dst[u] + arr[u][v];
                        predecessor[v] = u;
                    }
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (i == startInt)
                dj.append(start).append("=0\n");
            else if (dst[i] < Integer.MAX_VALUE) {
                dj.append(start).append("->");
                getPath(predecessor, i, dj);
                if (dj.length() > 0)
                    dj.delete(dj.length()-2, dj.length());
                dj.append("=").append(dst[i]).append("\n");
            }
            else
                dj.append("no path to ").append(int2Name.get(i)).append("\n");
        }

        return dj.toString();
    }

    private void getPath(int[] predecessors, int current, StringBuilder path) {
        if (predecessors[current] == -1 || predecessors[current] == current) return;
        getPath(predecessors, predecessors[current], path);
        path.append(int2Name.get(current)).append("->");
    }

    private int minDst(int[] dst, HashSet<Integer> VT) {
        int min = Integer.MAX_VALUE, index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (!VT.contains(i) && dst[i] < min) {
                min = dst[i];
                index = i;
            }
        }
        if (index == -1) {
            for (int i = 0; i < arr.length; i++) {
                if (!VT.contains(i) && dst[i] == Integer.MAX_VALUE) return i;
            }
        }
        return index;
    }
}
