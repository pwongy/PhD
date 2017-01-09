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

public class SetupFromRootMS {

	public static void main(String[] args) {
		run(); // Run main thread, below
	}

	public static Model run() {
		// Get existing (unsolved) model from root file
		String tag = System.getProperty("cs.currentmodel");
		Model model = ModelUtil.model(tag);
		
		// XXX: Manual inputs
			// Directories
			String baseDir = "B:\\Temp\\Modelling\\3. GP_complete\\4. COMSOL\\2.1. Validation update";
				// model.modelPath();
				// String baseDir = model.getFilePath();
				// TODO: Verify whether this works...ANS: NO, IT DOESN'T
			String studyInit = "MS";
			final int totalMaterials = 16; // Max is 16
			
			// Static references to match COMSOL - DO NOT CHANGE!
			final String[] terminalSelects = {"sel4", "sel1", "sel7", "sel2", "sel3", "sel5", "sel6", "sel8"};
			final String[] boundaryTags = {"hemi_gnd", "hemi_1V", "sph_gnd", "nrvD_gnd", "caud_gnd", "inf_gnd"};
			
			// Only list entities of interest
			// (These are the tags from COMSOL)
			String[] terminalTags = {"term4"};//, "term1", "term7", "term2", "term3", "term5", "term6", "term8"};
			String[] boundaries = {"gnd1"};//, "term9", "gnd2", "gnd3", "gnd4", "gnd5"}; 
			
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
		
		model.variable().create("var5");		// Material tested
		model.variable("var5").model("mod1");
		
		model.variable().create("var6");		// Resistivity value
		model.variable("var6").model("mod1");
		
		// Set up directories and file name parts
		model.variable("var1").set("study_init", studyInit);

		// Loop for each electrode terminal
		for (int j = 0; j < terminalTags.length; j++) {
			// Turn on active terminal
			String activeTerminal = terminalTags[j];
			model.physics("ec").feature(activeTerminal).active(true);
			
			// Save references to active terminal
			model.variable("var2").set("termA_tag", terminalTags[j]);
			model.variable("var3").set("termA_sel", terminalSelects[j]);

			// Activate boundary condition
			String activeBC = boundaries[0];
		    model.physics("ec").feature(activeBC).active(true);

		    // Save reference to active ground in created variable
			model.variable("var4").set("bcA", boundaryTags[0]);
				
			// Loop: For each material
			for (int matNum = 1; matNum < totalMaterials+1; matNum++) {
				String matTag = "mat" + Integer.toString(matNum);
				String[] properties = getMaterialProps(matNum);
				String conductivity;

				if (properties != null) {
				    // ...then for each material's conductivity range
					// Starting with 4th string in properties[] (i.e. low value)
					// Can have as many values in string[] as required, not just high/low
					for (int i = 3; i < properties.length; i++) {
						conductivity = properties[i];
				 
						if (conductivity.equals("")) {
							// Do nothing
						} else {
							model.material(matTag).propertyGroup("def")
								.set("electricconductivity", conductivity);
							model.variable("var5").set("material", properties[1]);
							model.variable("var6").set("resistivity", conductivity.substring(2));

							// Save activated model to new file
							try {
								String saveFileName = baseDir + "\\" + studyInit + "\\" 
										+ studyInit + "-" + activeTerminal
										+ "-" + properties[1] 				// Material name
										+ "-" + conductivity.substring(2); 	// Resistivity value
								model.save(saveFileName);
							} catch (IOException e) {
								e.printStackTrace();
							}	
						}
				    	
					}
					
					// Reset stuff
					conductivity = properties[2];	// Base conductivity
					model.material(matTag).propertyGroup("def")
			    		.set("electricconductivity", conductivity);
				}
			}

			// Reset everything for next simulation after new file saved
			model.physics("ec").feature(activeTerminal).active(false);	// Deactivate electrode
			model.physics("ec").feature(activeBC).active(false);		// Deactivate ground
		}
		
		return model;
	}

	/**
	 * Gets the low, middle (base) and high conductivity values for the material.
	 * 
	 * @param matNum The COMSOL tag for the material to be tested.
	 * @return An array of values formatted as {tag, name, middle/base, low resistivity, high resistivity}
	 */
	private static String[] getMaterialProps(int matNum) {
		// Set material number with tag, default values, and test values
		// To 5 significant figures
		String[] silicone = {"mat1", "silicone", "1/(1e7)", "", ""};
		String[] platinum = {"mat2", "Pt", "1/(1.06e-7)", "", ""};
		
		String[] blood = {"mat3", "blood", "1/1.5", "1/0.75", "1/3", "1/0.61", "1/5.56"};
		String[] tempBone = {"mat4", "tempBone", "1/13.73", "1/6.865"}; //, "1/27.46", "1/62.5"};
		String[] otic = {"mat5", "otic", "1/6.41", "1/350"}; //"1/3.205", "1/12.82", "1/1.5", "1/350"};
		String[] modBone = {"mat6", "modBone", "1/11.7", ""}; // "1/5.85", "1/23.4", ""};
		String[] csf = {"mat7", "CSF", "1/0.56", ""}; // "1/0.28", "1/1.12", ""};
		String[] perilymph = {"mat8", "peri", "1/0.7", "1/1.4"}; // "1/0.35", "1/1.4"};
		String[] endolymph = {"mat9", "endo", "1/0.6", ""}; // "1/0.3", "1/1.2"};
		String[] nerve = {"mat10", "nerve", "1/3", "1/7.14"}; // "1/1.5", "1/6", "1/1", "1/7.14"};
		String[] reissners = {"mat11", "RM", "1/10204", ""}; // "1/5102", "1/20408", "1/1020.4"};
		String[] basilar = {"mat12", "BM", "1/80", ""}; // "1/40", "1/160"};
		String[] roundWin = {"mat3", "RW", "1/1000", ""}; // "1/100", "1/10000"};
		String[] corti = {"mat14", "corti", "1/83.333", ""}; // "1/41.666", "1/166.66", "1/200"};
		String[] ligament = {"mat15", "SL", "1/0.6", "1/2.5"}; // "1/0.3", "1/1.2", "1/2.5"};
		String[] stria = {"mat16", "stria", "1/188.68", ""}; // "1/94.34", "1/377.36", "1/3"};
		
		// Array of all materials for loop
		String[][] materials = {silicone, platinum, blood, tempBone, otic, modBone, csf, perilymph,
				endolymph, nerve, reissners, basilar, roundWin, corti, ligament, stria};
		
		// Return material based on material number in model 
		String[] desiredMaterial = materials[matNum-1];
		return desiredMaterial;
	}
	
}