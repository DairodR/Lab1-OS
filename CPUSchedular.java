package Algorithm;

import java.util.PriorityQueue;
import java.util.Queue;

public abstract class CPUSchedular {
	public double gemtat, gemNtat, gemwaittime;
	
	

	public abstract Queue<CreatingProcesses.Process> NonPreemptive(Queue<CreatingProcesses.Process> q);
	public abstract Queue<CreatingProcesses.Process> Preemptive(Queue<CreatingProcesses.Process> q, int timeSlice);
	
	

}
