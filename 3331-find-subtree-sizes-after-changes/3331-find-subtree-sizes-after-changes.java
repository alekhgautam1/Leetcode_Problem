import java.util.*;

class Solution {
    private List<Integer>[] adj;
    private int[] newParent;
    private int[] lastSeen;
    private String s;

  
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.s = s;
        this.newParent = Arrays.copyOf(parent, n);
        this.lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            adj[parent[i]].add(i);
        }

       findNewParents(0);

        for (int i = 0; i < n; i++) {
            adj[i].clear();
        }
        for (int i = 1; i < n; i++) {
            adj[newParent[i]].add(i);
        }

       int[] answer = new int[n];
        computeSizes(0, answer);
        return answer;
    }

    private void findNewParents(int u) {
        int charIdx = s.charAt(u) - 'a';
        int prevAncestor = lastSeen[charIdx];

        if (prevAncestor != -1) {
            newParent[u] = prevAncestor;
        }

        lastSeen[charIdx] = u;
        for (int v : adj[u]) {
            findNewParents(v);
        }
       lastSeen[charIdx] = prevAncestor;
    }

    private int computeSizes(int u, int[] answer) {
        int size = 1;
        for (int v : adj[u]) {
            size += computeSizes(v, answer);
        }
        answer[u] = size;
        return size;
    }
}