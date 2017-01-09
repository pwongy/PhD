/*
 * ExportStreamlineRotation.java
 */

// Import external classes
import java.io.*;

import com.comsol.model.*;
import com.comsol.model.util.*;

/** 
 * This java class is (optionally) run after the corresponding .mph file has been solved.
 * 
 * It exports the streamline plotgroup, both individual images and image series for
 * subsequent video creation.
 *  
 * @author WONG, Paul Chun Hymn
 *
 */

public class VideoStreamlines {
	
	public static void main(String[] args) {
		run(); // Run main thread
	}

	public static void run() {
		// Get existing (solved) model from base file
		String tag = System.getProperty("cs.currentmodel");
		Model model = ModelUtil.model(tag);
		
		// XXX: Manual inputs here
			// Set up directories and file name parts
//			String baseDir = "C:\\Users\\pwon0600\\Desktop\\Java\\Testing";
//			String studyInit = model.variable("var1").get("init1");
//			String activeTerminal = model.variable("var2").get("termAname");
//			String activeBoundary = model.variable("var4").get("gndA");
//			String exportDir = baseDir + "\\" + studyInit + "\\Selected\\" 
//					+ studyInit + "-" + activeTerminal + "-" + activeBoundary + "\\Streamlines";
			
			String exportDir = "S:\\Videos";
			
			String plotView = "view6";
		    String plotGroup = "pg1";
		    String imageExport = "img1";
		    String windowTag = "window1";
	
		    // Video settings
			int vidLengthInSecs = 15;
			int frameRate = 30;
			int totalFrames = vidLengthInSecs*frameRate;
			
			// Set up things for camera
			int i = 1000;
		    double targetX = 2.399;
		    double targetZ = 2.206;
		    double radius = 125;

	    model.view(plotView).set("scenelight", "on");
	    model.view(plotView).set("transparency", "on");
	    model.view(plotView).set("alpha", "0.4");
	    model.view(plotView).hideEntities("hide1").set(new int[]{});
	    
//	    model.result("pg2").feature("str1").set("selnumber", "64"); // CHANGED IN EXPORT FILE
	    
	    // Replace nerve volume with solid colour
//	    model.result("pg2").feature("vol1").set("color", "custom");
//	    model.result("pg2").feature("vol1")
//	    	.set("customcolor", new double[]{1, 0.875, 0});

//	    // Add cochlear tissue volumes
//	    model.result("pg2").feature().create("vol2", "Volume");
//	    model.result("pg2").feature("vol2").set("filteractive", "on");
//	    model.result("pg2").feature("vol2")
//	         .set("logfilterexpr", "(ec.sigmaxx==1/0.7)||(ec.sigmaxx==1/0.6)");
//	    model.result("pg2").feature("vol2").set("coloring", "uniform");
//	    model.result("pg2").feature("vol2").set("color", "gray");
//
//	    // Add electrode array
//	    model.result("pg2").feature().create("vol3", "Volume");
//	    model.result("pg2").feature("vol3").set("filteractive", "on");
//	    model.result("pg2").feature("vol3")
//	         .set("logfilterexpr", "ec.sigmaxx==1/(1e7)");
//	    model.result("pg2").feature("vol3").set("color", "custom");
//	    model.result("pg2").feature("vol3")
//        	.set("customcolor", new double[]{0.25, 0.25, 0.25});
   
	    // Run the plot
	    model.result(plotGroup).set("view", plotView);
	    model.result(plotGroup).run();
	    
//	    double alpha = 1;
	    
	    for (double theta = 0; theta < 360; theta = theta + (double) 360/(double) totalFrames) {
	    	// Calculate X and Z camera positions
	    	double camX = targetX + radius * Math.sin(theta * Math.PI / (double) 180);
	    	double camZ = targetZ - radius * Math.cos(theta * Math.PI / (double) 180);
	    	
	    	// Change camera to new position
	    	model.view(plotView).camera().setIndex("position", Double.toString(camX), 0); // X position
	        model.view(plotView).camera().setIndex("position", Double.toString(camZ), 2); // Z position
	        
	        // Change alpha (transparency)
//	        model.view("view6").set("alpha", Double.toString(alpha));
//	        alpha = alpha - 0.1;

		    // Set plot window
	        model.result(plotGroup).set("window", windowTag);

	        // Export file
		    String fileName = exportDir + "\\streams_" + i + ".png";
		    model.result().export(imageExport).set("pngfilename", fileName);
		    model.result().export(imageExport).set("plotgroup", plotGroup);
		    model.result().export(imageExport).run();
		    
		    // Close plot window
		    ModelUtil.closeWindow(windowTag);
		    
		    // Increment filename index number
		    i++;
		}
	}
}
