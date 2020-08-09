package hanoi;

import java.util.ArrayList;

public class HanoiSolver {
	private int from,to;
	public HanoiSolver(int from,int to) {
		this.from = from;
		this.to = to;
	}
	public int getFrom() {
		return from;
	}

	public int getTo() {
		return to;
	}
	public static ArrayList<HanoiSolver> solve(int n,int from,int to,int temp) {
		ArrayList<HanoiSolver> answer = new ArrayList<>();
		solveRecursion(answer, n, from, to, temp);
		return answer;
	}
	private static void solveRecursion(ArrayList<HanoiSolver> answer,int n,int from,int to,int temp) {
		if(n==1) {
			answer.add(new HanoiSolver(from, to));
			return;
		}
		solveRecursion(answer,n-1, from, temp, to);
		answer.add(new HanoiSolver(from, to));
		solveRecursion(answer,n-1, temp, to, from);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("from:%s to:%s\n", from,to);
	}
}
