
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import Algorithm.CPUSchedular;
import Algorithm.FCFS;
import Algorithm.HRRN;
import Algorithm.MLFB;
import Algorithm.RoundRobin;
import CreatingProcesses.XMLReaderDOM;
import CreatingProcesses.Process;
import Graph.DrawLineGraphWait;
import Graph.DrawLineGraphnTAT;

public class Main {

	public static void main(String[] args) {
		String filePath = "processen10000.xml";
		XMLReaderDOM reader = new XMLReaderDOM();
		Queue<Process> processen = new LinkedList<>();
		
		
		
		
	    
	    
	    processen =reader.leesProcessen(filePath);
		System.out.println(processen.size());
		
		DrawLineGraphWait demoW = new DrawLineGraphWait("Wachttijden van: " + processen.size() + " processen", "Wachtijden", processen);
		demoW.pack();    
	    RefineryUtilities.centerFrameOnScreen( demoW );    
	    demoW.setVisible( true );
	    
	    DrawLineGraphnTAT demonTAT = new DrawLineGraphnTAT("Genormaliseerde TAT van: " + processen.size() + " processen", "nTAT", processen);
		demonTAT.pack();    
	    RefineryUtilities.centerFrameOnScreen( demonTAT );    
	    demonTAT.setVisible( true );
	    
	    filePath = "processen20000.xml";
	    processen =reader.leesProcessen(filePath);
		System.out.println(processen.size());
	    
		DrawLineGraphWait demoW2 = new  DrawLineGraphWait("Wachttijden van: " + processen.size() + " processen", "Wachtijden", processen);
		demoW2.pack();    
	    RefineryUtilities.centerFrameOnScreen( demoW2 );    
	    demoW2.setVisible( true );
	    
	    DrawLineGraphnTAT demonTAT2 = new DrawLineGraphnTAT("Genormaliseerde TAT van: " + processen.size() + " processen", "nTAT", processen);
		demonTAT2.pack();    
	    RefineryUtilities.centerFrameOnScreen( demonTAT2 );    
	    demonTAT2.setVisible( true );
	    
	    filePath = "processen50000.xml";
	    processen =reader.leesProcessen(filePath);
		System.out.println(processen.size());
	    
		DrawLineGraphWait demoW5 = new  DrawLineGraphWait("Wachttijden van: " + processen.size() + " processen", "Wachtijden", processen);
		demoW5.pack();    
	    RefineryUtilities.centerFrameOnScreen( demoW5 );    
	    demoW5.setVisible( true );
	    
	    DrawLineGraphnTAT demonTAT5 = new DrawLineGraphnTAT("Genormaliseerde TAT van: " + processen.size() + " processen", "nTAT", processen);
		demonTAT5.pack();    
	    RefineryUtilities.centerFrameOnScreen( demonTAT5 );    
	    demonTAT5.setVisible( true );
	    
		
//		CPUSchedular test = new FCFS();
//		Queue<Process> fcfsQue = test.NonPreemptive(processen);
//		System.out.println(fcfsQue);
//		
//		CPUSchedular testRR = new RoundRobin();
//		Queue<Process> RR = testRR.Preemptive(processen, 2);
//		System.out.println(RR);
//		System.out.println(RR.size());
//		
//		CPUSchedular testHRRN = new HRRN();
//		Queue<Process> HRRN = testHRRN.NonPreemptive(processen);
//		System.out.println(HRRN);
//		System.out.println(HRRN.size());
//		
//		CPUSchedular testMLFB = new MLFB();
//		Queue<Process> MLFB = testMLFB.Preemptive(processen, 1);
//		System.out.println(MLFB);
//		System.out.println(MLFB.size());
		
	}

}
