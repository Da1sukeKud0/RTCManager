package simulator;

import java.util.ArrayList;

public class TreeGenerator {
	public static void main(String[] args) {
		TreeGenerator tg = new TreeGenerator();
		tg.run(3, 2);
	}

	public TreeGenerator() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@SuppressWarnings("unchecked")
	public int[][] run(int depth, int fanout) {
		ArrayList<int[]> tree = new ArrayList<>();
		int node = 0; //ノード番号は0から
		ArrayList<Integer> parent = new ArrayList<>();
		ArrayList<Integer> child = new ArrayList<>();
		child.add(0);
		for (int d = 0; d < depth - 1; d++) {
			parent = (ArrayList<Integer>) child.clone();
			child.clear();
			for (int p : parent) {
				for (int f = 0; f < fanout; f++) {
					node++;
					tree.add(new int[] { p, node, 1 }); //親,子,cost=1
					child.add(node);
				}
			}
		}
		//		tree.forEach(s -> System.out.println(s[0] + "," + s[1] + "," + s[2]));
		System.out.println("#Tree topology#");
		System.out.println("Number of nodes: " + (node - 1));
		System.out.println("Number of edges: " + tree.size());
		return tree.toArray(new int[tree.size()][3]);
	}
}
