package sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_2383 {
	static int N;
	static int[][] map = new int[11][11];
	static boolean[] visited = new boolean[11];
	static ArrayList<Person> list = new ArrayList<>();
	static Stair[] stairs = new Stair[2];

	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		StringBuffer sf = new StringBuffer();

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			map = new int[11][11];
			visited = new boolean[11];

			list.clear();

			stairs = new Stair[2];

			min = Integer.MAX_VALUE;

			int flag = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());

					if (map[i][j] == 1) {
						list.add(new Person(i, j));
					}
					if (map[i][j] > 1) {
						stairs[flag] = new Stair(i, j, map[i][j]);
						// 한 계단에 있을 수 있는 사람의 수는 3 / 계단의 수는 2로고정
						flag = 1;
					}
				}
			}
			// 완전 탐색 -> 1번 계단을 선택해서 내려가는 경우의 시간과 2번 계단을 선택해서 내려가는 경우의 시간의최소값을 구해야 한다.
			divPerson(0);

			divide();

			sf.append("#" + tc + " " + min + "\n");

		}
		System.out.println(sf.toString());
	}

	static void divPerson(int cur) {
		if (cur == list.size()) {
			divide();
			return;
		}
		visited[cur] = true;
		divPerson(cur+1);
		visited[cur] = false;
		divPerson(cur+1);
	}
	static void divide() {

		PriorityQueue<OnStair> pq = new PriorityQueue<>(new Comparator<OnStair>() {
			@Override
			public int compare(OnStair o1, OnStair o2) {
				if (o1.time > o2.time) return 1;
				else if (o1.time == o2.time) {
					if (o1.dist > o2.dist) return 1;
					else if (o1.dist == o2.dist) return 0;
					else return -1;
				}
				else return -1;
			}
		});

		int dist = 0;
		for (int i = 0; i < list.size(); i++) {
			if (visited[i]) {
				dist = range(list.get(i), stairs[0]);
				pq.add(new OnStair(0, 0, dist, 0));
			}
			else {
				dist = range(list.get(i), stairs[1]);
				pq.add(new OnStair(0, 0, dist, 1));
			}
		}
		//계단을 내려오는 경우의 수를 모두 구한다.

		calcTime(pq);

	}
	static void calcTime(PriorityQueue<OnStair> pq) {
		int time = 0;

		int[] desPersonsCnt = new int[2]; //각 계단마다 내려가는 사람의 수를 센다. 

		while(!pq.isEmpty()) {
			OnStair now = pq.poll();

			time = now.time;

			//도착해서 대기하는 시간까지 고려해야 하기 때문에 부등호를 붙여준다.
			//지금 위치한 거리가 멀어서 시간 내에 계단에 도착못함 -> 다시 큐에 넣어서 기다려줌  
			if (now.dist >= time) {
				pq.add(new OnStair(now.time+1, now.walk, now.dist, now.stair));
				continue;
			}

			//이미 내려가는 중 
			if (now.walk != 0) {
				//계단 밑에 도착함 
				if (now.walk == stairs[now.stair].height) {
					desPersonsCnt[now.stair]--;
					continue;
				}
				pq.add(new OnStair(now.time+1, now.walk+1, now.dist, now.stair));
				continue;
			}

			//대기 큐에 사람이 꽉 참 -> 3명이 정원 
			if (desPersonsCnt[now.stair] == 3) {
				pq.add(new OnStair(now.time+1,now.walk, now.dist, now.stair));

			} else {
				desPersonsCnt[now.stair]++;
				pq.add(new OnStair(now.time+1, now.walk+1, now.dist, now.stair));
			}
		}
		min = Math.min(min, time);
	}
	static int range(Person p, Stair s) {
		return Math.abs(p.x - s.x) + Math.abs(p.y - s.y);
	}

	static class Person {
		int x, y;

		public Person(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static class Stair {
		int x, y;
		int height; // 모두 내려가는 데 K분 k == height

		public Stair(int x, int y, int height) {
			super();
			this.x = x;
			this.y = y;
			this.height = height;
		}
	}

	static class OnStair {
		int time;
		int walk;
		int dist;
		int stair;

		public OnStair(int time, int walk, int dist, int stair) {
			super();
			this.time = time;
			this.walk = walk;
			this.dist = dist;
			this.stair = stair;
		}

	}
}

