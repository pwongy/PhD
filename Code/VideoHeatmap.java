/*
 * ExportHeatmap.java
 */

// Import external classes
import java.io.*;

import com.comsol.model.*;
import com.comsol.model.util.*;

/** 
 * This java class is (optionally) run after the corresponding .mph file has 
 * been solved.
 * 
 * It exports the heatmap plotgroup slice by slice through the cochlea volume.
 *  
 * @author WONG, Paul Chun Hymn
 *
 */

public class VideoHeatmap {
	
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
//					+ studyInit + "-" + activeTerminal + "-" + activeBoundary + "\\Heatmap";
			
			String exportDir = "S:\\Videos";
			
			String plotView = "view6";
		    String plotGroup = "pg2";
		    String imageExport = "img3";
		    String windowTag = "window1";
		    
			// Video settings
			int vidLengthInSecs = 15;
			int frameRate = 30;
			int totalFrames = vidLengthInSecs*frameRate;
		
			// Prepare for screenshot loop
			double zMin = -0.0026;
			double zMax = 0.0019;
			double zIncrement = (zMax - zMin)/(double) totalFrames;

		int i = 1000;
		model.result(plotGroup).run();
		
		for (double z = zMin; z < zMax; z = z + zIncrement) {
			// Set shift distance
			String shift = Double.toString(z);
		    model.result(plotGroup).feature("slc1").set("shift", shift);
		    	
		    // Set plot window
	        model.result(plotGroup).set("window", windowTag);
		    
		    // Export file
		    String fileName = exportDir + "\\heatmap_" + i + ".png";
		    model.result().export(imageExport).set("plotgroup", plotGroup);
		    model.result().export(imageExport).set("pngfilename", fileName);
		    model.result().export(imageExport).run();
		    
		    // Close plot window
		    ModelUtil.closeWindow(windowTag); // NOTE: Does not exist in v4.3 API
		    
		    // Increment filename index number
		    i++;
		}
	    
	}
}
