import java.util.*;

class Solution {

    static class State {
        int v, speed;
        State(int v, int speed) {
            this.v = v;
            this.speed = speed;
        }
    }

    static final int INF = 1_000_000_001;
    static final long OFFSET = 1_000_000_000L;
    static final long BASE = 2_000_000_001L;

    static ArrayList<long[]> points;
    static Map<Long, Integer> idMap;
    static ArrayList<Integer>[] graph;

    public int[] solution(int[][] city, int[][] road) {
        int n = city.length;
        int m = road.length;

        points = new ArrayList<>();
        idMap = new HashMap<>();

        ArrayList<Integer>[] roadPoints = new ArrayList[m];
        HashSet<Long>[] roadSeen = new HashSet[m];
        for (int i = 0; i < m; i++) {
            roadPoints[i] = new ArrayList<>();
            roadSeen[i] = new HashSet<>();
        }

        HashMap<Long, Integer> camAt = new HashMap<>();

        // 1. 각 도로의 카메라 점 추가
        for (int i = 0; i < m; i++) {
            long cx = ((long) road[i][0] + road[i][2]) / 2;
            long cy = ((long) road[i][1] + road[i][3]) / 2;
            int id = getId(cx, cy);
            addToRoad(roadPoints[i], roadSeen[i], id);

            long key = makeKey(cx, cy);
            if (!camAt.containsKey(key) || camAt.get(key) > road[i][4]) {
                camAt.put(key, road[i][4]);
            }
        }

        // 2. 도시 점 추가
        for (int i = 0; i < n; i++) {
            long x = city[i][0];
            long y = city[i][1];
            int id = getId(x, y);

            for (int j = 0; j < m; j++) {
                if (onRoad(road[j], x, y)) {
                    addToRoad(roadPoints[j], roadSeen[j], id);
                }
            }
        }

        // 3. 교차점 추가
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                long[] cross = getCross(road[i], road[j]);
                if (cross == null) continue;

                int id = getId(cross[0], cross[1]);
                addToRoad(roadPoints[i], roadSeen[i], id);
                addToRoad(roadPoints[j], roadSeen[j], id);
            }
        }

        // 4. 그래프 생성
        graph = new ArrayList[points.size()];
        for (int i = 0; i < points.size(); i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            final boolean vertical = road[i][0] == road[i][2];

            Collections.sort(roadPoints[i], new Comparator<Integer>() {
                public int compare(Integer a, Integer b) {
                    long[] p1 = points.get(a);
                    long[] p2 = points.get(b);

                    if (vertical) {
                        if (p1[1] < p2[1]) return -1;
                        if (p1[1] > p2[1]) return 1;
                        return 0;
                    } else {
                        if (p1[0] < p2[0]) return -1;
                        if (p1[0] > p2[0]) return 1;
                        return 0;
                    }
                }
            });

            for (int j = 0; j < roadPoints[i].size() - 1; j++) {
                int a = roadPoints[i].get(j);
                int b = roadPoints[i].get(j + 1);
                graph[a].add(b);
                graph[b].add(a);
            }
        }

        // 5. 각 정점의 카메라 제한값
        int[] camLimit = new int[points.size()];
        Arrays.fill(camLimit, INF);

        for (Map.Entry<Long, Integer> entry : camAt.entrySet()) {
            int id = idMap.get(entry.getKey());
            camLimit[id] = entry.getValue();
        }

        // 6. maximin 다익스트라
        int start = getId(city[0][0], city[0][1]);
        int[] dist = new int[points.size()];
        Arrays.fill(dist, -1);

        PriorityQueue<State> pq = new PriorityQueue<>(new Comparator<State>() {
            public int compare(State a, State b) {
                return b.speed - a.speed;
            }
        });

        dist[start] = INF;
        pq.add(new State(start, INF));

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            if (dist[cur.v] != cur.speed) continue;

            for (int i = 0; i < graph[cur.v].size(); i++) {
                int next = graph[cur.v].get(i);
                int nextSpeed = Math.min(cur.speed, camLimit[next]);

                if (dist[next] < nextSpeed) {
                    dist[next] = nextSpeed;
                    pq.add(new State(next, nextSpeed));
                }
            }
        }

        // 7. 정답
        int[] answer = new int[n - 1];
        for (int i = 1; i < n; i++) {
            int id = getId(city[i][0], city[i][1]);
            answer[i - 1] = dist[id] == INF ? 0 : dist[id];
        }

        return answer;
    }

    static void addToRoad(ArrayList<Integer> list, HashSet<Long> seen, int id) {
        long[] p = points.get(id);
        long key = makeKey(p[0], p[1]);
        if (seen.contains(key)) return;
        seen.add(key);
        list.add(id);
    }

    static int getId(long x, long y) {
        long key = makeKey(x, y);
        if (idMap.containsKey(key)) return idMap.get(key);

        int id = points.size();
        idMap.put(key, id);
        points.add(new long[] {x, y});
        return id;
    }

    static long makeKey(long x, long y) {
        return (x + OFFSET) * BASE + (y + OFFSET);
    }

    static boolean onRoad(int[] r, long x, long y) {
        long x1 = r[0], y1 = r[1];
        long x2 = r[2], y2 = r[3];

        return Math.min(x1, x2) <= x && x <= Math.max(x1, x2)
            && Math.min(y1, y2) <= y && y <= Math.max(y1, y2);
    }

    static long[] getCross(int[] a, int[] b) {
        long x1 = a[0], y1 = a[1], x2 = a[2], y2 = a[3];
        long x3 = b[0], y3 = b[1], x4 = b[2], y4 = b[3];

        boolean h1 = y1 == y2;
        boolean h2 = y3 == y4;

        // 수평 - 수직
        if (h1 && !h2) {
            if (Math.min(x1, x2) <= x3 && x3 <= Math.max(x1, x2)
             && Math.min(y3, y4) <= y1 && y1 <= Math.max(y3, y4)) {
                return new long[] {x3, y1};
            }
            return null;
        }

        // 수직 - 수평
        if (!h1 && h2) {
            if (Math.min(x3, x4) <= x1 && x1 <= Math.max(x3, x4)
             && Math.min(y1, y2) <= y3 && y3 <= Math.max(y1, y2)) {
                return new long[] {x1, y3};
            }
            return null;
        }

        // 수평 - 수평
        if (h1 && h2) {
            if (y1 != y3) return null;
            long left = Math.max(Math.min(x1, x2), Math.min(x3, x4));
            long right = Math.min(Math.max(x1, x2), Math.max(x3, x4));
            if (left == right) return new long[] {left, y1};
            return null;
        }

        // 수직 - 수직
        if (x1 != x3) return null;
        long down = Math.max(Math.min(y1, y2), Math.min(y3, y4));
        long up = Math.min(Math.max(y1, y2), Math.max(y3, y4));
        if (down == up) return new long[] {x1, down};
        return null;
    }
}