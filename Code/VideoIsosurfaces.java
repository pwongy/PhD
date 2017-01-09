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
 * It exports the isosurface plotgroup.
 *  
 * @author WONG, Paul Chun Hymn
 *
 */

public class VideoIsosurfaces {
	
	public static void main(String[] args) {
		run(); // Run main thread
	}

	public static void run() {
		// Get existing (solved) model from base file
		String tag = System.getProperty("cs.currentmodel");
		Model model = ModelUtil.model(tag);
		
		// Set up directories and file name parts - XXX: Manual inputs here
		String baseDir = "C:\\Users\\pwon0600\\Desktop\\Java\\Testing";
		
		String studyInit = model.variable("var1").get("init1");
		String activeTerminal = model.variable("var2").get("termAname");
		String activeBoundary = model.variable("var4").get("gndA");
		String exportDir = baseDir + "\\" + studyInit + "\\Selected\\" 
				+ studyInit + "-" + activeTerminal + "-" + activeBoundary + "\\Isosurface";

		// Set up things for camera
		int i = 1000;
	    double targetX = 2.399;
	    double targetZ = 2.206;
	    double radius = 395.796;

	    model.view("view6").set("scenelight", "on");
	    model.view("view6").set("transparency", "on"); // Set view as transparent
	    model.view("view6").set("alpha", "0.20");
	    model.view("view6").hideEntities("hide1").set(new int[]{});

	    // Run the plot
	    model.result("pg1").run();
	    model.result("pg1").set("view", "view6");
	    
	    for (double theta = 0; theta < 360; theta = theta + (double)360/(double)240) {
	    	// Calculate X and Z camera positions
	    	double camX = targetX + radius * Math.sin(theta * Math.PI / (double) 180);
	    	double camZ = targetZ - radius * Math.cos(theta * Math.PI / (double) 180);
	    	
	    	// Change camera position
	    	model.view("view6").camera().setIndex("position", Double.toString(camX), 0); // X position
	        model.view("view6").camera().setIndex("position", Double.toString(camZ), 2); // Z position
	        
	        // Export file
		    String fileName = exportDir + "\\isosurf_" + i + ".png";
		    model.result().export("img1").set("plotgroup", "pg1");
		    model.result().export("img1").set("pngfilename", fileName);
		    model.result().export("img1").run();
		    
		    // Increment filename index number
		    i++;
		}
	}
}
