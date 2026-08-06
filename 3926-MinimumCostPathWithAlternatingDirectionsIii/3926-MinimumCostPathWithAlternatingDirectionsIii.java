// Last updated: 07/08/2026, 00:12:54
import java.util.*;

class Solution {
    public long minCost(int m, int n, int[][] penalty) {
        int total = m * n;
        long INF = Long.MAX_VALUE / 4;

        long[][] dist = new long[total][2];
        for (int i = 0; i < total; i++) {
            Arrays.fill(dist[i], INF);
        }

        PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> Long.compare(a.cost, b.cost));

        dist[0][1] = 1L;
        pq.offer(new State(0, 1, 1L));

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            if (cur.cost != dist[cur.id][cur.parity]) continue;

            if (cur.id == total - 1) return cur.cost;

            int r = cur.id / n;
            int c = cur.id % n;

            int nextParity = cur.parity ^ 1;

            long waitCost = cur.cost + penalty[r][c];
            if (waitCost < dist[cur.id][nextParity]) {
                dist[cur.id][nextParity] = waitCost;
                pq.offer(new State(cur.id, nextParity, waitCost));
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;

                boolean follows;
                if (cur.parity == 1) {
                    follows = (d == 1 || d == 3); // Down or Right
                } else {
                    follows = (d == 0 || d == 2); // Up or Left
                }

                long cost = cur.cost + 1L * (nr + 1) * (nc + 1);
                if (!follows) {
                    cost += penalty[r][c];
                }

                int nextId = nr * n + nc;

                if (cost < dist[nextId][nextParity]) {
                    dist[nextId][nextParity] = cost;
                    pq.offer(new State(nextId, nextParity, cost));
                }
            }
        }

        return -1;
    }

    static class State {
        int id;
        int parity;
        long cost;

        State(int id, int parity, long cost) {
            this.id = id;
            this.parity = parity;
            this.cost = cost;
        }
    }
}