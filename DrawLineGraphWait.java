package Graph;

import java.awt.Color;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.awt.BasicStroke; 


import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities;

import Algorithm.CPUSchedular;
import Algorithm.FCFS;
import Algorithm.HRRN;
import Algorithm.MLFB;
import Algorithm.MLFBQ1;
import Algorithm.RoundRobin;
import Algorithm.ShortestProcessNext;
import Algorithm.ShortestRemainingTime;
import CreatingProcesses.*;
import CreatingProcesses.Process;

import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class DrawLineGraphWait extends ApplicationFrame {


	public DrawLineGraphWait( String applicationTitle , String chartTitle, Queue<Process> q ) {
		super(applicationTitle);
		JFreeChart xylineChart = ChartFactory.createXYLineChart(
				chartTitle,
				"ServiceTime","Wachttijden",
				createDataset(q),
				PlotOrientation.VERTICAL,
				true,true,false);

		ChartPanel chartPanel = new ChartPanel( xylineChart );
		chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
		final XYPlot plot = xylineChart.getXYPlot( );

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
		renderer.setSeriesPaint( 0 , Color.RED );
		renderer.setSeriesPaint( 1 , Color.GREEN );
		renderer.setSeriesPaint( 2 , Color.YELLOW );
		renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
		renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
		renderer.setSeriesStroke( 2 , new BasicStroke( 2.0f ) );
		plot.setRenderer( renderer ); 
		setContentPane( chartPanel );
	}

	private XYDataset createDataset(Queue<Process> q) {
		
//		//FCFS 
//		CPUSchedular test = new FCFS();
//		Queue<Process> fcfsQue = test.NonPreemptive(q);
//		PriorityQueue<Process> fcfs = new PriorityQueue<Process>(Comparator.comparing(Process::getServicetimeneeded));
//		for (Process process : fcfsQue) {
//			fcfs.add(process);
//		}
//		final XYSeries FCFS = new XYSeries("FCFS");
//		berekenPercentielen(fcfs, FCFS);
		
		//RR met q = 2
		CPUSchedular testRR = new RoundRobin();
		Queue<Process> rr2Que = testRR.Preemptive(q, 2);
		PriorityQueue<Process> rr2 = new PriorityQueue<Process>(Comparator.comparing(Process::getServicetimeneeded));
		for (Process process : rr2Que) {
			rr2.add(process);
		}
		final XYSeries RR2 = new XYSeries("RR q=2");
		berekenPercentielen(rr2, RR2);
		
		//RR met q = 4
		Queue<Process> rr4Que = testRR.Preemptive(q, 4);
		PriorityQueue<Process> rr4 = new PriorityQueue<Process>(Comparator.comparing(Process::getServicetimeneeded));
		for (Process process : rr4Que) {
			rr4.add(process);
		}
		final XYSeries RR4 = new XYSeries("RR q=4");
		berekenPercentielen(rr4, RR4);
		
		//RR met q = 8
		Queue<Process> rr8Que = testRR.Preemptive(q, 8);
		PriorityQueue<Process> rr8 = new PriorityQueue<Process>(Comparator.comparing(Process::getServicetimeneeded));
		for (Process process : rr8Que) {
			rr8.add(process);
		}
		final XYSeries RR8 = new XYSeries("RR q=8");
		berekenPercentielen(rr8, RR8);
		
//		//SPN
//		CPUSchedular testSPN = new ShortestProcessNext();
//		Queue<Process> spnQue = testSPN.NonPreemptive(q);
//		PriorityQueue<Process> spn = new PriorityQueue<Process>(Comparator.comparing(Process::getServicetimeneeded));
//		for (Process process : spnQue) {
//			spn.add(process);
//		}
//		final XYSeries SPN = new XYSeries("SPN");
//		berekenPercentielen(spn, SPN);
//		
//		//SRT
//		CPUSchedular testSRT = new ShortestRemainingTime();
//		Queue<Process> srtQue = testSRT.NonPreemptive(q);
//		PriorityQueue<Process> srt = new PriorityQueue<Process>(Comparator.comparing(Process::getServicetimeneeded));
//		for (Process process : srtQue) {
//			srt.add(process);
//		}
//		final XYSeries SRT = new XYSeries("SRT");
//		berekenPercentielen(srt, SRT);
//		
//		//HRRN
//		CPUSchedular testHHRN = new HRRN();
//		Queue<Process> hrrnQue = testHHRN.NonPreemptive(q);
//		PriorityQueue<Process> hrrn = new PriorityQueue<Process>(Comparator.comparing(Process::getServicetimeneeded));
//		for (Process process : hrrnQue) {
//			hrrn.add(process);
//		}
//		final XYSeries HRRN = new XYSeries("HHRN");
//		berekenPercentielen(hrrn, HRRN);
//		
//		//MLFB met stijgende timeslices per queue
//		CPUSchedular testMLFB = new MLFB();
//		Queue<Process> mlfbQue = testMLFB.Preemptive(q, 1);
//		PriorityQueue<Process> mlfb = new PriorityQueue<Process>(Comparator.comparing(Process::getServicetimeneeded));
//		for (Process process : mlfbQue) {
//			mlfb.add(process);
//		}
//		final XYSeries MLFB = new XYSeries("MLFB q=2^i");
//		berekenPercentielen(mlfb, MLFB);
//		
//		//MLFB met cte timeSlices
//		CPUSchedular testMLFBQ1 = new MLFBQ1();
//		Queue<Process> mlfbq1Que = testMLFBQ1.Preemptive(q, 1);
//		PriorityQueue<Process> mlfbq1 = new PriorityQueue<Process>(Comparator.comparing(Process::getServicetimeneeded));
//		for (Process process : mlfbq1Que) {
//			mlfbq1.add(process);
//		}
//		final XYSeries MLFBQ1 = new XYSeries("MLFB q=i^0");
//		berekenPercentielen(mlfbq1, MLFBQ1);
		
		// totale dataset
		final XYSeriesCollection dataset = new XYSeriesCollection();
//		dataset.addSeries(FCFS);
		dataset.addSeries(RR2);
		dataset.addSeries(RR4);
		dataset.addSeries(RR8);
//		dataset.addSeries(SPN);
//		dataset.addSeries(SRT);
//		dataset.addSeries(HRRN);
//		dataset.addSeries(MLFB);
//		dataset.addSeries(MLFBQ1);
		


		return dataset;
	}
	public void berekenPercentielen (Queue<Process> q, XYSeries Alg) {
		Process p;
		int aantal = 0;
		int percentiel = q.size()/100;
		double gemWaitT =0;
		double serviceTneeded = 0;

		while(!q.isEmpty()) {
			p=q.poll();
			gemWaitT = gemWaitT + p.getWaittime();
			serviceTneeded = serviceTneeded + p.getServicetimeneeded();
			if(aantal % percentiel ==0 && aantal !=0) {
				gemWaitT = gemWaitT/percentiel;
				serviceTneeded = serviceTneeded/percentiel;
				Alg.add(serviceTneeded, gemWaitT);
				gemWaitT =0;
				serviceTneeded=0;
			}
			aantal++;
		}
	}
}

