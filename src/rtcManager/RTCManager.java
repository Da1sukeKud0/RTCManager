package rtcManager;

import java.util.ArrayList;
import java.util.Collections;

import dijkstra.Dijkstra;
import simulator.BarabasiAlbertGenerator;

public class RTCManager {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//グラフ作成
		//int[][] edges = new TreeGenerator().run(8, 2);
		int[][] edges = new BarabasiAlbertGenerator(100, 2).generateGraph();
		Dijkstra dijk = new Dijkstra(edges);
		//雑に5回分実行
		for (int i = 0; i < 20; i++) {
			int[] c = getRandomCase(dijk.calcNumOfNode());
			long start = System.nanoTime();
			try {
				dijk.getShortestPath(c[0], c[1]);
			} catch (RuntimeException re) {
				System.out.println("path is none.");
			}
			long end = System.nanoTime();
			System.out.println("During time: " + (end - start) / (long) 1000000.0 + " ms");
		}
	}

	/**
	 * @param max
	 * @return
	 */
	private static int[] getRandomCase(int max) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i <= max; i++) {
			list.add(i);
		}
		Collections.shuffle(list);
		return new int[] { list.remove(0), list.remove(1) };
	}
}
