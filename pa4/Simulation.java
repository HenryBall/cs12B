// Henry Ball
// hfball
// 12B
// 05-13-16
// Simulation.java
// runs a simulation of n jobs and n-1 number of processors

import java.io.*;
import java.util.Scanner;
import java.io.BufferedWriter;

public class Simulation{

   // splits input file into separate jobs
   public static Job getJob(String w){
      String[] s = w.split(" ");

      int a = Integer.parseInt(s[0]);
      int d = Integer.parseInt(s[1]);
      return new Job(a,d);
   }

   // computes minimum queue
   public static int computeMinQueue(Queue[] x){
      int i;
      int minLength;
      int minIndex;
      minLength = x[1].length();
      minIndex = 1;
      for (i=2; i<x.length; i++){
         if (x[i].length() < minLength){
            minLength = x[i].length();
            minIndex = i;
         }
      }
      return minIndex;
   }

   public static void main(String[] args)
      throws IOException{

      // variable declaration
      Scanner in = null;
      //PrintWriter rpt = new PrintWriter(args[0] + ".rpt", "UTF-8");
      PrintWriter trc = new PrintWriter(args[0] + ".trc", "UTF-8");
      Job[] jobs;
      int numJobs;
      int lineCount = 0;
      int i = 0;
      int j = 0;
      int counter = 0;
      int time, nextTime;
      int indexMinQueue = 0;
      int totalWait = 0;
      int maxWait = 0;
      int avgWait = 0;
      Queue[] processorQueues;
      Queue storageQueue = new Queue();
      Queue origQueue = new Queue();

      // read in input file
      in = new Scanner(new File(args[0]));
      in.useDelimiter("\\Z");
      String s = in.next();
      String[] word = s.split("\n");
      
      jobs = new Job[word.length-1];

      for (i=0; i<jobs.length; i++) {
         jobs[i] = getJob(word[i+1]);
         origQueue.enqueue(jobs[i]);
      }

      numJobs = jobs.length;

      System.out.print("Report file: " + args[0] + ".rpt\n");
      System.out.print(numJobs + " Jobs:\n");
      System.out.print(origQueue.toString() + "\n");
      System.out.print("\n");
      System.out.print("***************************************\n");

      trc.write("Trace file: " + args[0] + ".trc\n");
      trc.write(numJobs + "Jobs:\n");
      trc.write(origQueue.toString());
      trc.write("\n");
      
      // main loop
      for (j=1; j<numJobs; j++){
         processorQueues = new Queue[j + 1];

         for (i=0; i<=j; i++){
            processorQueues[i] = new Queue();
         }

         for (i=0; i<numJobs; i++) {
            jobs[i].resetFinishTime();
            processorQueues[0].enqueue(jobs[i]);
         }

         time = 0;
         nextTime = 0;
         totalWait = 0;
         maxWait = 0;
         avgWait = 0;

         trc.write("********************************\n");
         trc.write(j + " processors\n");
         trc.write("********************************\n");

         while (true) {

            trc.write("time=" + time + "\n");
            for(i=0; i<=j; i++){
               trc.write(i + ": " + processorQueues[i].toString() + "\n");
            }
            trc.write("\n");

            if (counter < numJobs){
               nextTime = ((Job) processorQueues[0].peek()).getArrival();
               for (i=1; i<=j; i++){
                  if ((processorQueues[i].length() != 0) && (((Job) processorQueues[i].peek()).getFinish() < nextTime)){
                     nextTime = ((Job) processorQueues[i].peek()).getFinish();
                  }
               }
            }else{
               for (i=1; i<=j; i++){
                  if ((processorQueues[i].length() != 0)){
                     nextTime = ((Job) processorQueues[i].peek()).getFinish();
                  }
               }
               for (i=1; i<=j; i++){
                  if ((processorQueues[i].length() != 0) && (((Job) processorQueues[i].peek()).getFinish() < nextTime)){
                     nextTime = ((Job) processorQueues[i].peek()).getFinish();
                  }
               }
            }
            time = nextTime;

            for (i=1; i<=j; i++){
               if ((processorQueues[i].length() != 0) && (((Job) processorQueues[i].peek()).getFinish() == time)){
                  storageQueue.enqueue(processorQueues[i].peek());
                  processorQueues[0].enqueue(processorQueues[i].peek());
                  processorQueues[i].dequeue();
                  if (processorQueues[i].length() != 0){
                     ((Job) processorQueues[i].peek()).computeFinishTime(time);
                     totalWait = totalWait + ((Job) processorQueues[i].peek()).getWaitTime();
                     if (((Job) processorQueues[i].peek()).getWaitTime() > maxWait){
                        maxWait = ((Job) processorQueues[i].peek()).getWaitTime();
                     }
                  }   
               }
            }

            while ((processorQueues[0].length() != 0) && (time == ((Job) processorQueues[0].peek()).getArrival())){
               indexMinQueue = computeMinQueue(processorQueues);
               processorQueues[indexMinQueue].enqueue(processorQueues[0].peek());
               processorQueues[0].dequeue();
               counter++;
               if (processorQueues[indexMinQueue].length() == 1){
                  ((Job) processorQueues[indexMinQueue].peek()).computeFinishTime(time);
                  totalWait = totalWait + ((Job) processorQueues[indexMinQueue].peek()).getWaitTime();
                  if (((Job) processorQueues[indexMinQueue].peek()).getWaitTime() > maxWait){
                     maxWait = ((Job) processorQueues[indexMinQueue].peek()).getWaitTime();
                  }
               }
            }
            //System.out.println(storageQueue.toString());
            if (processorQueues[0].length() == numJobs){
               for (i=1; i<=j; i++){
                  if (processorQueues[i].length() == 0){
                     break;
                  }
               }
            }
         }
         System.out.print(j + " processors: " + "totalWait= " + totalWait + ", " + "maxWait= " + maxWait + ", " + "averageWait= " + (double) totalWait/numJobs + "\n");
         storageQueue.dequeueAll();
      } 
      trc.close();
   }
}
