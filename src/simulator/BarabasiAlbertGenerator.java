package simulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * BAモデルの生成を行うクラス
 */
public class BarabasiAlbertGenerator {
	//private final Random rnd = new Random();
	private final int num0;
	private final int num;
	private final int cplx;
	private ArrayList<int[]> edges;
	private ArrayList<Integer> nodes;

	/**
	 * BAモデルの生成パラメータの設定とチェック
	 * 
	 * @param num ノード数
	 * @param cplx 複雑度(新規生成するノードから既存ノードへ貼るリンクの本数)
	 */
	public BarabasiAlbertGenerator(int num, int cplx) {
		if (cplx <= 0) {
			throw new IllegalArgumentException("複雑度cは1以上である必要がある");
		}
		this.cplx = cplx;
		this.num0 = cplx;
		if (num < 2) {
			throw new IllegalArgumentException("最終ノード数は初期ノード数(1)より1以上大きい必要がある");
		}
		this.num = num;
	}

	/**
	 * グラフの生成
	 * @return 辺情報( int[3] )を格納したリスト
	 */
	public int[][] generateGraph() {
		this.edges = new ArrayList<>();
		this.nodes = new ArrayList<>();
		//nodesに初期ノードを追加
		this.nodes.add(0);
		for (int n = 1; n <= num0; n++) {
			addEdge(n - 1, n);
			this.nodes.add(n);
		}
		for (int n = this.num0 + 1; n < this.num; n++) {
			addNode(n);
		}
		return this.edges.toArray(new int[this.edges.size()][3]);
	}

	/**
	 * べき法則に従いノードから辺を生成
	 * @param src BAモデルにおける新規に生成されたノード
	 */
	@SuppressWarnings("unchecked")
	private void addNode(int src) {
		//シャッフルされたnodesを複製
		ArrayList<Integer> tmp = (ArrayList<Integer>) this.nodes.clone();
		for (int c = 0; c < this.cplx; c++) {
			int dst = tmp.remove(0); //1つdst候補を取り出し
			Iterator<Integer> it = tmp.iterator();
			while (it.hasNext()) {
				int i = it.next();
				if (i == dst)
					it.remove(); //tmp内の同値のノードを全削除
			}
			addEdge(src, dst);
			this.nodes.add(src);
			this.nodes.add(dst);
		}
		Collections.shuffle(this.nodes); //nodesをシャッフル
	}

	/**
	 * 辺情報(ノード対とコスト)を生成しthis.edgesに格納
	 * @param v0 source
	 * @param v1 destination
	 */
	public void addEdge(int v0, int v1) {
		int[] e = new int[3];
		e[0] = v0;
		e[1] = v1;
		e[2] = 1;
		this.edges.add(e);
	}

	//for debug
	public static void main(String[] args) {
		int[][] result = new BarabasiAlbertGenerator(10, 3).generateGraph();
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i][0] + " , " + result[i][1] + " , " + result[i][2]);
		}
	}
}