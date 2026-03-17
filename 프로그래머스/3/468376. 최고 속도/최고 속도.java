import java.util.*;

class Solution {
    static class Point {
        long x, y;
        int city;        // 도시 번호 (없으면 0)
        int camera;      // 카메라 제한속도 (없으면 INF)

        Point(long x, long y) {
            this.x = x;
            this.y = y;
            this.city = 0;
            this.camera = INF;
        }
    }

    static class Road {
        long x1, y1, x2, y2;
        boolean horizontal;
        long cx, cy; // 카메라 위치
        int limit;
        ArrayList<Integer> points = new ArrayList<>();

        Road(int[] r) {
            x1 = r[0];
            y1 = r[1];
            x2 = r[2];
            y2 = r[3];
            limit = r[4];
            horizontal = (y1 == y2);
            cx = (x1 + x2) / 2;
            cy = (y1 + y2) / 2;
        }
    }

    static class Edge {
        int to, limit;

        Edge(int to, int limit) {
            this.to = to;
            this.limit = limit;
        }
    }

    static class State {
        int node, speed;

        State(int node, int speed) {
            this.node = node;
            this.speed = speed;
        }
    }

    static final int INF = 1_000_000_001;

    static ArrayList<Point> pList;
    static ArrayList<Edge>[] graph;
    static HashMap<String, Integer> map;

    public int[] solution(int[][] city, int[][] road) {
        int n = city.length;
        int m = road.length;

        pList = new ArrayList<>();
        map = new HashMap<>();

        Road[] roads = new Road[m];
        for (int i = 0; i < m; i++) {
            roads[i] = new Road(road[i]);
        }

        int[] cityPoint = new int[n + 1];

        // 1. 도로 끝점, 카메라 추가
        for (int i = 0; i < m; i++) {
            int a = addPoint(roads[i].x1, roads[i].y1);
            int b = addPoint(roads[i].x2, roads[i].y2);
            int c = addPoint(roads[i].cx, roads[i].cy);

            roads[i].points.add(a);
            roads[i].points.add(b);
            roads[i].points.add(c);

            if (pList.get(c).camera > roads[i].limit) {
                pList.get(c).camera = roads[i].limit;
            }
        }

        // 2. 도시 추가
        for (int i = 0; i < n; i++) {
            long x = city[i][0];
            long y = city[i][1];
            int idx = addPoint(x, y);
            pList.get(idx).city = i + 1;
            cityPoint[i + 1] = idx;

            for (int j = 0; j < m; j++) {
                if (onRoad(x, y, roads[j])) {
                    roads[j].points.add(idx);
                }
            }
        }

        // 3. 교차점 추가
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                long[] inter = intersect(roads[i], roads[j]);
                if (inter == null) continue;

                int idx = addPoint(inter[0], inter[1]);
                roads[i].points.add(idx);
                roads[j].points.add(idx);
            }
        }

        // 4. 그래프 생성
        graph = new ArrayList[pList.size()];
        for (int i = 0; i < pList.size(); i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            ArrayList<Integer> list = roads[i].points;

            if (roads[i].horizontal) {
                Collections.sort(list, new Comparator<Integer>() {
                    public int compare(Integer a, Integer b) {
                        Point p1 = pList.get(a);
                        Point p2 = pList.get(b);
                        if (p1.x < p2.x) return -1;
                        if (p1.x > p2.x) return 1;
                        return 0;
                    }
                });
            } else {
                Collections.sort(list, new Comparator<Integer>() {
                    public int compare(Integer a, Integer b) {
                        Point p1 = pList.get(a);
                        Point p2 = pList.get(b);
                        if (p1.y < p2.y) return -1;
                        if (p1.y > p2.y) return 1;
                        return 0;
                    }
                });
            }

            ArrayList<Integer> unique = new ArrayList<>();
            for (int j = 0; j < list.size(); j++) {
                if (j == 0 || !samePoint(list.get(j), list.get(j - 1))) {
                    unique.add(list.get(j));
                }
            }

            for (int j = 0; j < unique.size() - 1; j++) {
                int u = unique.get(j);
                int v = unique.get(j + 1);

                int limit = Math.min(pList.get(u).camera, pList.get(v).camera);

                graph[u].add(new Edge(v, limit));
                graph[v].add(new Edge(u, limit));
            }
        }

        // 5. widest path
        int[] best = new int[pList.size()];
        PriorityQueue<State> pq = new PriorityQueue<>(new Comparator<State>() {
            public int compare(State a, State b) {
                return b.speed - a.speed;
            }
        });

        int start = cityPoint[1];
        best[start] = INF;
        pq.add(new State(start, INF));

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            if (best[cur.node] != cur.speed) continue;

            for (int i = 0; i < graph[cur.node].size(); i++) {
                Edge next = graph[cur.node].get(i);
                int ns = Math.min(cur.speed, next.limit);

                if (best[next.to] < ns) {
                    best[next.to] = ns;
                    pq.add(new State(next.to, ns));
                }
            }
        }

        int[] answer = new int[n - 1];
        for (int i = 2; i <= n; i++) {
            int v = best[cityPoint[i]];
            answer[i - 2] = (v == INF ? 0 : v);
        }

        return answer;
    }

    static int addPoint(long x, long y) {
        String key = x + "," + y;
        Integer idx = map.get(key);

        if (idx != null) return idx;

        idx = pList.size();
        pList.add(new Point(x, y));
        map.put(key, idx);
        return idx;
    }

    static boolean samePoint(int a, int b) {
        Point p1 = pList.get(a);
        Point p2 = pList.get(b);
        return p1.x == p2.x && p1.y == p2.y;
    }

    static boolean onRoad(long x, long y, Road r) {
        if (r.horizontal) {
            return y == r.y1 && r.x1 <= x && x <= r.x2;
        } else {
            return x == r.x1 && r.y1 <= y && y <= r.y2;
        }
    }

    static long[] intersect(Road a, Road b) {
        if (a.horizontal && b.horizontal) {
            if (a.y1 != b.y1) return null;

            long left = Math.max(a.x1, b.x1);
            long right = Math.min(a.x2, b.x2);

            if (left == right) return new long[] {left, a.y1};
            return null;
        }

        if (!a.horizontal && !b.horizontal) {
            if (a.x1 != b.x1) return null;

            long down = Math.max(a.y1, b.y1);
            long up = Math.min(a.y2, b.y2);

            if (down == up) return new long[] {a.x1, down};
            return null;
        }

        Road h = a.horizontal ? a : b;
        Road v = a.horizontal ? b : a;

        if (h.x1 <= v.x1 && v.x1 <= h.x2 && v.y1 <= h.y1 && h.y1 <= v.y2) {
            return new long[] {v.x1, h.y1};
        }

        return null;
    }
}