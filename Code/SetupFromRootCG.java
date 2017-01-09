/*
 * SetupFromRoot.java
 */

import java.io.*;

import com.comsol.model.*;
import com.comsol.model.util.*;

/** 
 * This class spawns a bunch of child .mph files based on a root template.
 * It is designed for validation against Shefin George's in vivo voltage 
 * tomography data.
 * 
 * Generated file names for the new .mph files are based on the parameters 
 * being changed (active terminal, material properties, boundary condition).
 * 
 * Examples:
 *  - Convergence with quadratic discretisation, direct solver [CG-2-dir.mph]
 *  - Boundary condition from basal electrode to 2x sphere [BC-term1-sph_2x.mph]
 *  - Material sensitivity for comparison with base case, changing bone resistivity [MS-bone_6.mph]
 *  
 * @author WONG, Paul Chun Hymn
 *
 */

public class SetupFromRootCG {

	public static void main(String[] args) {
		run(); // Run main thread, below
	}

	public static Model run() {
		// Get existing (unsolved) model from root file
		String tag = System.getProperty("cs.currentmodel");
		Model model = ModelUtil.model(tag);
		
		// XXX: Manual inputs
			// Directories
			String baseDir = "B:\\Temp\\Modelling\\3. GP_complete\\4. COMSOL\\2. Validation";
				// model.modelPath();
				// String baseDir = model.getFilePath();
				// TODO: Verify whether this works...ANS: NO, IT DOESN'T
			String studyInit = "CG";
			
			// Static references to match COMSOL - DO NOT CHANGE!
			final String[] terminalSelects = {"sel4", "sel1", "sel7", "sel2", "sel3", "sel5", "sel6", "sel8"};
			final String[] boundaryTags = {"hemi_gnd", "hemi_1V", "sph_gnd", "nrvD_gnd"};
			
			// Only list entities of interest
			// (These are the tags from COMSOL)
			String[] terminalTags = {"term4"};//, "term1", "term7", "term2", "term3", "term5", "term6", "term8"};
			String[] boundaries = {"gnd1"};//, "term9", "gnd2", "gnd3"}; 
			
			// Set discretisation - Last string argument is corresponding power
			// (e.g. 2 is quadratic)
			String order = "2";
		    model.physics("ec").prop("ShapeProperty")
	        		.set("order_electricpotential", 1, order);
		
		// Create variables
		    // DO NOT attempt to put base directory into a variable again
		    // COMSOL fails to parse it correctly
		    
		model.variable().create("var1");		// Study initial
		model.variable("var1").model("mod1");
		
		model.variable().create("var2");		// Active terminal name
		model.variable("var2").model("mod1");
		
		model.variable().create("var3");		// Active terminal selection
		model.variable("var3").model("mod1");
		
		model.variable().create("var4");		// Boundary condition
		model.variable("var4").model("mod1");
		
		model.variable().create("var5");		// Base directory
		model.variable("var5").model("mod1");
		
		// Set up directories and file name parts
		model.variable("var1").set("study_init", studyInit);

		// Loop: For each electrode terminal
		for (int j = 0; j < terminalTags.length; j++) {
		
			// ...and for each boundary condition
			for (int i = 0; i < boundaries.length; i++) {
			
				// Turn on active terminal
				String activeTerminal = terminalTags[j];
				model.physics("ec").feature(activeTerminal).active(true);
				
				// Save references to active terminal
				model.variable("var2").set("termA_tag", terminalTags[j]);
				model.variable("var3").set("termA_sel", terminalSelects[j]);

				// Activate boundary condition
				String activeBC = boundaries[i];
			    model.physics("ec").feature(activeBC).active(true);
	
			    // Save reference to active ground in created variable
				model.variable("var4").set("bcA", boundaryTags[i]);
		 
		    	// Save activated model to new file
				try {
					String saveFileName = baseDir + "\\" + studyInit + "\\" 
							+ studyInit;
					model.save(saveFileName);
				} catch (IOException e) {
					e.printStackTrace();
				}

				// Reset everything for next simulation after new file saved
				model.physics("ec").feature(activeTerminal).active(false); // Deactivate electrode
			    model.physics("ec").feature(activeBC).active(false); // Deactivate ground
			}
		}
		
		return model;
	}
	
}