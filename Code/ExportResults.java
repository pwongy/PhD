/*
 * ExportResults.java
 */

import com.comsol.model.Model;
import com.comsol.model.util.ModelUtil;

/** 
 * This java class exports numerical and graphical results from the solved
 * .mph file. It must be run after the .mph file has been solved.
 * 
 * The following results are exported:
 *  - Current density (norm) profile along Rosenthal's canal
 *  - Current exiting via the otic capsule and the nerve (proximal)
 *  - Maximum current density in the spiral ganglion/nerve
 *  - Average voltage at each electrode
 *  - Isosurface plots
 *  - Streamline plots
 *  - Current density heatmaps
 *  
 * @author WONG, Paul Chun Hymn
 *
 */

public class ExportResults {

	public static void main(String[] args) {
		run(); // Run main thread
	}

	public static Model run() {
		// Declare variables
		String fileName;
		String[] seedCoords;
		
		// Get existing (solved) model
		String tag = System.getProperty("cs.currentmodel");
		Model model = ModelUtil.model(tag);
		
		/**
		 * Get variables for use in file names.
		 */
		String studyInit = model.variable("var1").get("study_init");
		String activeTermTag = model.variable("var2").get("termA_tag");
		String activeBC = model.variable("var4").get("bcA");
		
		// Comment these out if not doing MS
//		String material = model.variable("var5").get("material"); 
//		String resistivity = model.variable("var6").get("resistivity");
		
		// For BV study only
//		String vessels = model.variable("var5").get("vessels");			
		
		// Set for this particular study
		String tag1 = activeTermTag;
		String tag2 = activeBC;

		/**
		 * These things require manual setup, so need to check before each run.
		 */
		// XXX: Manual inputs
			// Set up export directories
		    String studyDir = "B:\\Temp\\Modelling\\3. GP_complete\\4. COMSOL\\2.1. Validation update" 
		    		+ "\\" + studyInit;
		    String exportDir = studyDir + "\\Tables";		// For numerical data
		    String screensDir = studyDir + "\\Images";		// For plots
		    
		    String cutPoints = "B:\\Temp\\Modelling\\3. GP_complete\\4. COMSOL\\Rosenthal's canal cutpoints - GP_complete.txt";
		    String cutpoints1 = "B:\\Temp\\Modelling\\3. GP_complete\\5. MATLAB\\cutpoints_PP_RC.csv";
		    String cutpoints2 = "B:\\Temp\\Modelling\\3. GP_complete\\5. MATLAB\\cutpoints_RC_AN.csv";

	    // Set up export classes for videos
	    model.batch().create("b3", "Batch"); // Heatmap
	    model.batch("b3").study("std1");
	    model.batch("b3").feature().create("cl1", "Class");
	    model.batch("b3").feature("cl1")
	         .set("filename", "B:\\Temp\\Modelling\\3. GP_complete\\4. COMSOL\\2. Validation\\Java\\bin\\VideoHeatmap.class");
		
	    model.batch().create("b4", "Batch"); // Isosurfaces
	    model.batch("b4").study("std1");
	    model.batch("b4").feature().create("cl1", "Class");
	    model.batch("b4").feature("cl1")
	         .set("filename", "B:\\Temp\\Modelling\\3. GP_complete\\4. COMSOL\\2. Validation\\Java\\bin\\VideoIsosurfaces.class");
		
	    model.batch().create("b5", "Batch"); // Streamlines
	    model.batch("b5").study("std1");
	    model.batch("b5").feature().create("cl1", "Class");
	    model.batch("b5").feature("cl1")
	         .set("filename", "B:\\Temp\\Modelling\\3. GP_complete\\4. COMSOL\\2. Validation\\Java\\bin\\VideoStreamlines.class");
		
	    // Create outputs
	    model.result().table().create("tbl1", "Table");
	    model.result().dataset().create("cpt1", "CutPoint3D");
	    model.result().numerical().create("av1", "AvSurface");
	    model.result().numerical().create("av2", "AvSurface");
	    model.result().numerical().create("av3", "AvSurface");
	    model.result().numerical().create("av4", "AvSurface");
	    model.result().numerical().create("av5", "AvSurface");
	    model.result().numerical().create("av6", "AvSurface");
	    model.result().numerical().create("av7", "AvSurface");
	    model.result().numerical().create("av8", "AvSurface");

	    model.result().create("pg1", "PlotGroup3D");
	    model.result().create("pg2", "PlotGroup3D");
	    model.result().create("pg3", "PlotGroup3D");
		
		// Results table
	    model.result().table("tbl1").name("Table 1 - Terminal voltages");
	    model.result().table("tbl1").comments("For comparing terminal voltages predicted by the model with those obtained in vivo.");

	    // Cut points for Rosenthal's canal
	    model.result().dataset("cpt1").name("Cut Point 3D 1 - Rosenthal's canal");
	    model.result().dataset("cpt1").set("method", "file");
	    model.result().dataset("cpt1").set("filename", cutPoints);
	    
	    // Derived values
	    model.result().numerical("av1").selection().named("sel9");	// For loop?
	    model.result().numerical("av1").set("probetag", "none");
	    model.result().numerical("av1").name("Surface Average 1 - V_term1");
	    model.result().numerical("av1").set("table", "tbl1");
	    model.result().numerical("av1").set("descractive", true);
	    model.result().numerical("av1").set("descr", "Pad 1");
	    
	    model.result().numerical("av2").selection().named("sel10");
	    model.result().numerical("av2").set("probetag", "none");
	    model.result().numerical("av2").name("Surface Average 2 - V_term2");
	    model.result().numerical("av2").set("table", "tbl1");
	    model.result().numerical("av2").set("descractive", true);
	    model.result().numerical("av2").set("descr", "Pad 2");
	    
	    model.result().numerical("av3").selection().named("sel11");
	    model.result().numerical("av3").set("probetag", "none");
	    model.result().numerical("av3").name("Surface Average 3 - V_term3");
	    model.result().numerical("av3").set("table", "tbl1");
	    model.result().numerical("av3").set("descractive", true);
	    model.result().numerical("av3").set("descr", "Pad 3");
	    
	    model.result().numerical("av4").selection().named("sel12");
	    model.result().numerical("av4").set("probetag", "none");
	    model.result().numerical("av4").name("Surface Average 4 - V_term4");
	    model.result().numerical("av4").set("table", "tbl1");
	    model.result().numerical("av4").set("descractive", true);
	    model.result().numerical("av4").set("descr", "Pad 4");
	    
	    model.result().numerical("av5").selection().named("sel13");
	    model.result().numerical("av5").set("probetag", "none");
	    model.result().numerical("av5").name("Surface Average 5 - V_term5");
	    model.result().numerical("av5").set("table", "tbl1");
	    model.result().numerical("av5").set("descractive", true);
	    model.result().numerical("av5").set("descr", "Pad 5");
	    
	    model.result().numerical("av6").selection().named("sel14");
	    model.result().numerical("av6").set("probetag", "none");
	    model.result().numerical("av6").name("Surface Average 6 - V_term6");
	    model.result().numerical("av6").set("table", "tbl1");
	    model.result().numerical("av6").set("descractive", true);
	    model.result().numerical("av6").set("descr", "Pad 6");
	    
	    model.result().numerical("av7").selection().named("sel15");
	    model.result().numerical("av7").set("probetag", "none");
	    model.result().numerical("av7").name("Surface Average 7 - V_term7");
	    model.result().numerical("av7").set("table", "tbl1");
	    model.result().numerical("av7").set("descractive", true);
	    model.result().numerical("av7").set("descr", "Pad 7");
	    
	    model.result().numerical("av8").selection().named("sel16");
	    model.result().numerical("av8").set("probetag", "none");
	    model.result().numerical("av8").name("Surface Average 8 - V_term8");
	    model.result().numerical("av8").set("table", "tbl1");
	    model.result().numerical("av8").set("descractive", true);
	    model.result().numerical("av8").set("descr", "Pad 8");
	    
	    model.result().numerical("av1").setResult();
	    model.result().numerical("av2").appendResult();
	    model.result().numerical("av3").appendResult();
	    model.result().numerical("av4").appendResult();
	    model.result().numerical("av5").appendResult();
	    model.result().numerical("av6").appendResult();
	    model.result().numerical("av7").appendResult();
	    model.result().numerical("av8").appendResult();
	    
	    // Plot group 1 - Streamlines
	    model.result("pg1").name("Current paths");
	    model.result("pg1").set("view", "view1");
	    model.result("pg1").set("edges", "off");
	    
	    model.result("pg1").feature().create("str1", "Streamline");
	    model.result("pg1").feature("str1").set("posmethod", "start");
	    model.result("pg1").feature("str1").set("startmethod", "coord");
	    
	    seedCoords = getStreamSeedCoords(activeTermTag);
	    model.result("pg1").feature("str1")
	         .set("xcoord", seedCoords[0]);
	    model.result("pg1").feature("str1")
	         .set("ycoord", seedCoords[1]);
	    model.result("pg1").feature("str1")
	         .set("zcoord", seedCoords[2]);
	    
	    model.result("pg1").feature("str1").set("linetype", "tube");
	    model.result("pg1").feature("str1").set("tuberadiusscale", "0.015");
	    model.result("pg1").feature("str1").set("tuberadiusscaleactive", true);
	    
	    model.result("pg1").feature("str1").feature().create("col1", "Color");
	    model.result("pg1").feature("str1").feature("col1")
	         .set("expr", "ec.normJ");
	    model.result("pg1").feature("str1").feature("col1").set("unit", "A/m^2");
	    model.result("pg1").feature("str1").feature("col1")
	         .set("descr", "Current density norm");
	    model.result("pg1").feature("str1").feature("col1")
	         .set("rangecoloractive", "on");
	    model.result("pg1").feature("str1").feature("col1")
	         .set("rangecolormax", "1000");
	    model.result("pg1").feature("str1").feature("col1")
	         .set("colorlegend", "off");
	    
	    model.result("pg1").feature().create("vol1", "Volume");		// Nerve tissue 
	    model.result("pg1").feature("vol1").set("filteractive", "on");
	    model.result("pg1").feature("vol1")
    		.set("logfilterexpr", "ec.sigmaxx==1/3");
	    model.result("pg1").feature("vol1").set("expr", "ec.normJ");
	    model.result("pg1").feature("vol1").set("unit", "A/m^2");
	    model.result("pg1").feature("vol1").set("descr", "Current density norm");
	    model.result("pg1").feature("vol1").set("rangecoloractive", "on");
	    model.result("pg1").feature("vol1").set("rangecolormax", "1000");
	    model.result("pg1").feature("vol1").set("colortable", "Rainbow");

	    model.result("pg1").feature().create("vol2", "Volume");		// Other tissues
	    model.result("pg1").feature("vol2").set("filteractive", "on");
	    model.result("pg1").feature("vol2")
    		.set("logfilterexpr", "(ec.sigmaxx==1/(1e7))||(ec.sigmaxx==1/0.7)||(ec.sigmaxx==1/0.6)");
	    model.result("pg1").feature("vol2").set("coloring", "uniform");
	    model.result("pg1").feature("vol2").set("color", "gray");
	    
	    model.result("pg1").set("window", "window1");
	    model.result("pg1").set("windowtitle", "");
	    
	    // Plot group 2 - Heatmap
	    model.result("pg2").name("Current density heatmap");
	    model.result("pg2").set("view", "view2");
	    model.result("pg2").set("edges", "off");
	    
	    model.result("pg2").feature().create("slc1", "Slice");
	    model.result("pg2").feature("slc1").set("expr", "ec.normJ");
	    model.result("pg2").feature("slc1").set("unit", "A/m^2");
	    model.result("pg2").feature("slc1").set("descr", "Current density norm");
	    model.result("pg2").feature("slc1").set("quickzmethod", "coord");
	    model.result("pg2").feature("slc1").set("quickz", "2.55");
	    model.result("pg2").feature("slc1").set("interactive", "on");
	    model.result("pg2").feature("slc1").set("rangecoloractive", "on");
	    model.result("pg2").feature("slc1").set("rangecolormax", "1000");
	    model.result("pg2").feature("slc1").set("colortable", "Thermal");
	    model.result("pg2").feature("slc1").feature().create("filt1", "Filter");
	    model.result("pg2").feature("slc1").feature("filt1")
	    	.set("expr", "ec.sigmaxx!=1/13.73");
	    model.result("pg2").feature("slc1").feature("filt1").active(false);

	    
	    model.result("pg2").set("window", "window1");
	    model.result("pg2").set("windowtitle", "");
	    
	    // Plot group 3 - Voltage isosurface
	    model.result("pg3").name("Electric potential");
	    model.result("pg3").set("view", "view1");
	    model.result("pg3").set("showhiddenobjects", true);

	    model.result("pg3").feature().create("iso1", "Isosurface");
	    model.result("pg3").feature("iso1").set("levelmethod", "levels");
	    model.result("pg3").feature("iso1").set("levels", "range(0,0.1,1.4)");
	    model.result("pg3").feature("iso1").set("interactive", "on");
	    model.result("pg3").feature("iso1").set("shift", "0.1");
	    
	    model.result("pg3").set("window", "window1");
	    model.result("pg3").set("windowtitle", "");

	    // Create exports
	    model.result().export().create("data1", "Data");
	    model.result().export().create("tbl1", "tbl1", "Table");
	    model.result().export().create("img1", "Image3D");
	    model.result().export().create("img2", "Image3D");
	    model.result().export().create("img3", "Image3D");
	    
	    // Export JRC profile
	    model.result().export("data1").name("JRC profile");
	    model.result().export("data1").set("data", "cpt1");
	    model.result().export("data1")
	         .set("expr", new String[]{"dom", "ec.normJ"});
	    model.result().export("data1").set("unit", new String[]{"", "A/m^2"});
	    model.result().export("data1")
	         .set("descr", new String[]{"Domain index", "Current density norm"});
	    
	    fileName = makeFileName(exportDir, "JRC", tag1, tag2 ,"csv");
	    model.result().export("data1").set("filename", fileName);
	    model.result().export("data1").run();
	    
	    // Export terminal voltages
	    model.result().export("tbl1").name("Terminal voltages");
	    
	    fileName = makeFileName(exportDir, "Vt", tag1, tag2, "csv");
	    model.result().export("tbl1").set("filename", fileName);
	    model.result().export("tbl1").run();
	    
	    // Export images - Requires "-graphics" option in command line
	    fileName = makeFileName(screensDir, "streamlines", tag1, tag2, "png");
	    model.view("view1").set("transparency", "on"); 		// Set view as transparent
	    model.view("view1").set("alpha", "0.25");
	    model.result("pg1").set("view", "view1");
	    
	    model.result().export("img1").set("plotgroup", "pg1");
	    model.result().export("img1").set("pngfilename", fileName);
	    model.result().export("img1").run();
	    
	    fileName = makeFileName(screensDir, "isosurfaces", tag1, tag2, "png");
	    model.result("pg3").set("view", "view1");
	    
	    model.result().export("img3").set("plotgroup", "pg3");
	    model.result().export("img3").set("pngfilename", fileName);
	    model.result().export("img3").run();

	    model.view("view1").set("transparency", "off"); 	// Reset view transparency
	    
//	    int[] boneDoms = model.material("mat1").selection().inputEntities();
//	    model.view("view1").hideEntities("hide1").set(boneDoms);	// Hide surrounding bone
	    model.view("view6").set("scenelight", "off");
	    model.view("view6").set("transparency", "off");
	    model.result("pg2").set("edges", "off");
	    model.result("pg2").set("view", "view6");

	    fileName = makeFileName(screensDir, "heatmap", tag1, tag2, "png");
	    model.result().export("img2").set("plotgroup", "pg2");
	    model.result().export("img2").set("pngfilename", fileName);
	    model.result().export("img2").run();
	    
	    // Updated RC results for activation function
	    model.result().dataset().create("cpt2", "CutPoint3D");
		model.result().export().create("data2", "cpt2", "Data");
	    model.result().dataset().create("cpt3", "CutPoint3D");
	    model.result().export().create("data3", "cpt3", "Data");
		    
		    // PP to RC
		    model.result().dataset("cpt2").name("Cut Point 3D 2 - PP to RC");
		    model.result().dataset("cpt2").set("method", "file");
		    model.result().dataset("cpt2").set("filename", cutpoints1);
		    model.result().dataset("cpt2").run();
	
		    fileName = makeFileName(exportDir, "VRC_PP_RC", tag1, tag2, "csv");
		    model.result().export("data2").name("VRC_PP_RC for AF");
		    model.result().export("data2").set("expr", new String[]{"dom", "V"});
		    model.result().export("data2").set("descr", new String[]{"Domain index", "Electric potential"});
		    model.result().export("data2").set("filename", fileName);
		    model.result().export("data2").run();
		    
		    // RC to AN
		    model.result().dataset("cpt3").name("Cut Point 3D 3 - RC to AN");
		    model.result().dataset("cpt3").set("method", "file");
		    model.result().dataset("cpt3").set("filename", cutpoints2);
		    model.result().dataset("cpt3").run();
	
		    fileName = makeFileName(exportDir, "VRC_RC_AN", tag1, tag2, "csv");
		    model.result().export("data3").name("VRC_RC_AN for AF");
		    model.result().export("data3").set("expr", new String[]{"dom", "V"});
		    model.result().export("data3").set("descr", new String[]{"Domain index", "Electric potential"});
		    model.result().export("data3").set("filename", fileName);
		    model.result().export("data3").run();
	    
	    // Return model
	    return model;
	}
	
	/**
	 * Returns a string representing the desired filename for the export.
	 * 
	 * @param dir The export directory.
	 * @param description The type of information being exported.
	 * @param tag1 Description for one of the changed input parameters.
	 * @param tag2 Description for the second input parameters.
	 * @param extension Desired file extension.
	 * @return The absolute path with filename.
	 */
	private static String makeFileName(String dir, String description, String tag1,
			String tag2, String extension) {
		 String fileName = dir + "\\" + description
				 	+ "-" + tag1 + "-" + tag2 + "." + extension;
		 return fileName;
	}

	/**
	 * This class returns a string array of XYZ coordinates for grid spaced streamline seeding
	 * along the active electrode pad.
	 * 
	 * The coordinate lists are obtained via export from a quad mesh of the array and manually 
	 * copied below. See "Modelling > Streamline seeding" in Evernote for details.
	 * 
	 * @param activeTerminal The active terminal from which the streamlines should originate.
	 * @return A string array with three entries, corresponding to the X, Y and Z coordinates
	 *     for input into "model.result("pg").feature("str").set("coord, arg").
	 */
	public static String[] getStreamSeedCoords(String activeTerminal) {
		String[] desiredCoords = new String[3];
		
		// Pad 1
		final String pad1X = "0.9941077,1.007496,1.020044,1.033135,1.072275,1.114794,1.128992,1.119932,1.092686,1.041493,1.05428,1.040418,1.029044,1.02077,1.108706,1.15842,1.183006,1.188315,1.17237,1.132579,0.9784812,1.017861,1.047752,1.072312,1.052261,1.100153,1.128549,1.136257,1.120092,1.078956,1.064382,1.111144,1.139271,1.146019,1.128827,1.087881,1.077469,1.124109,1.151778,1.157849,1.140271,1.0991,1.092044,1.139389,1.166267,1.171897,1.154632,1.113712";
		final String pad1Y = "4.151317,4.132047,4.112759,4.093518,4.405208,4.361773,4.308659,4.259127,4.216246,4.181063,4.342875,4.360188,4.377529,4.396214,4.087742,4.125874,4.17075,4.218812,4.267275,4.306087,4.170801,4.422238,4.074624,4.323009,4.162794,4.196212,4.241734,4.293571,4.344537,4.383431,4.144256,4.177681,4.224213,4.276692,4.326787,4.364253,4.125445,4.159477,4.206384,4.258715,4.308411,4.345866,4.106525,4.141726,4.188561,4.23965,4.288864,4.326865";
		final String pad1Z = "2.059681,2.114664,2.170233,2.225899,2.066402,2.041566,2.01967,2.004251,1.995253,1.994338,2.306562,2.257991,2.209489,2.15731,2.271498,2.273784,2.284081,2.299823,2.320472,2.343069,2.004853,2.084604,2.280545,2.362545,2.051072,2.052525,2.062684,2.079955,2.103055,2.129585,2.106742,2.108455,2.119058,2.136911,2.160062,2.185618,2.161956,2.16361,2.174171,2.191813,2.214264,2.238179,2.216825,2.218423,2.228917,2.245848,2.267348,2.289882";
		// Pad 2
		final String pad2X = "1.293057,1.326991,1.362887,1.400175,1.349921,1.379546,1.387492,1.378817,1.352725,1.310594,1.451917,1.412806,1.374832,1.338648,1.489035,1.534004,1.560334,1.568217,1.561513,1.53575,1.261835,1.438099,1.491157,1.3049,1.342339,1.384251,1.411288,1.420771,1.411912,1.383155,1.375954,1.417846,1.445253,1.455081,1.446149,1.418163,1.411686,1.453843,1.481132,1.490951,1.482393,1.4552,1.449345,1.492131,1.519168,1.528497,1.520597,1.494184";
		final String pad2Y = "3.891993,3.87413,3.856098,3.838164,4.145182,4.106968,4.061559,4.012155,3.962927,3.924746,4.08581,4.103872,4.122123,4.140286,3.834495,3.874583,3.925135,3.971433,4.014355,4.051816,3.90928,3.820653,4.068346,4.15805,3.906278,3.942833,3.991805,4.043644,4.091089,4.127095,3.888054,3.92418,3.973139,4.025674,4.073657,4.108871,3.869977,3.906533,3.955435,4.007605,4.055257,4.090388,3.852129,3.88974,3.939117,3.989602,4.035972,4.0716";
		final String pad2Z = "2.795852,2.842616,2.888729,2.933394,2.77231,2.732787,2.708084,2.695354,2.69692,2.716017,2.991846,2.949218,2.904873,2.859581,2.939682,2.919032,2.917803,2.929934,2.952625,2.988693,2.749638,2.975732,3.031744,2.814243,2.763374,2.744694,2.742622,2.755762,2.781902,2.819303,2.809502,2.790435,2.788015,2.801213,2.82778,2.864619,2.854388,2.834668,2.832106,2.845174,2.871526,2.908284,2.897732,2.877449,2.875112,2.887872,2.913238,2.949842";
		// Pad 3
		final String pad3X = "1.86412,1.916152,1.969115,2.02224,1.905362,1.910799,1.900367,1.881255,1.846475,2.097239,2.043319,1.989167,1.935653,2.105567,2.138437,2.161703,2.17236,2.169424,1.81444,2.074521,2.149315,1.883658,1.897208,1.929794,1.952289,1.962702,1.957445,1.948014,1.979636,2.003212,2.014133,2.009686,1.999655,2.030699,2.054443,2.065883,2.062419,2.052049,2.083128,2.106812,2.118432,2.115644";
		final String pad3Y = "3.652531,3.634022,3.615417,3.596949,3.899306,3.852791,3.795523,3.744833,3.690632,3.840185,3.858806,3.877688,3.896548,3.595079,3.641783,3.698961,3.749227,3.802794,3.670461,3.578929,3.822369,3.915107,3.671632,3.719769,3.777186,3.835061,3.87992,3.652444,3.698588,3.757599,3.815962,3.860691,3.633346,3.678799,3.737828,3.796037,3.841352,3.614318,3.659696,3.717952,3.774586,3.821931";
		final String pad3Z = "3.303709,3.329815,3.353949,3.375767,3.250951,3.199862,3.177245,3.180464,3.217644,3.403647,3.384879,3.363619,3.340199,3.33512,3.296257,3.288612,3.304932,3.355947,3.276389,3.39485,3.419365,3.315013,3.245257,3.209733,3.204568,3.22776,3.276614,3.271121,3.235321,3.228843,3.251733,3.299957,3.294722,3.25819,3.250747,3.272773,3.320786,3.315946,3.278545,3.270554,3.290779,3.339138";
		// Pad 4
		final String pad4X = "2.520034,2.577525,2.63571,2.692426,2.520033,2.490759,2.467999,2.455641,2.453735,2.764358,2.711162,2.655293,2.598786,2.728457,2.726707,2.735318,2.754517,2.785933,2.466918,2.74466,2.812155,2.544208,2.576049,2.54926,2.526946,2.513259,2.510316,2.630456,2.603375,2.581283,2.567979,2.566038,2.683309,2.655101,2.633313,2.621121,2.62107,2.734664,2.705276,2.684323,2.673619,2.675163";
		final String pad4Y = "3.430637,3.412247,3.393705,3.375639,3.677707,3.632679,3.575339,3.523886,3.468839,3.616527,3.634392,3.653112,3.672053,3.373689,3.41893,3.465327,3.522475,3.579399,3.447748,3.358956,3.600334,3.690311,3.656043,3.611445,3.554529,3.498238,3.450408,3.635668,3.590531,3.533463,3.476717,3.431117,3.616007,3.569745,3.512431,3.456342,3.411757,3.597057,3.548008,3.490484,3.436648,3.392712";
		final String pad4Z = "3.451049,3.441492,3.42671,3.406928,3.38681,3.341006,3.328318,3.342288,3.392803,3.375792,3.401503,3.422898,3.439043,3.317638,3.265698,3.245138,3.246829,3.287154,3.455894,3.383696,3.348095,3.449867,3.374986,3.333275,3.32171,3.338937,3.385449,3.35972,3.320283,3.310381,3.329402,3.375563,3.339707,3.301977,3.293993,3.314426,3.361114,3.315166,3.277995,3.272408,3.293843,3.341671";
		// Pad 5
		final String pad5X = "3.109396,3.133609,3.15352,3.168211,3.043745,2.992422,2.972454,2.97971,3.022861,3.177383,3.167018,3.151601,3.13138,3.114739,3.070588,3.057824,3.069117,3.118672,3.082952,3.177494,3.182757,3.107722,3.111729,3.101742,3.087022,3.067382,3.066202,3.056768,3.041647,3.020586,3.051291,3.039915,3.023472,3.001344,3.064083,3.051499,3.033695,3.010214,3.104977,3.090991,3.072196,3.048934";
		final String pad5Y = "3.228129,3.215716,3.203538,3.191956,3.472065,3.425381,3.366885,3.315004,3.260145,3.436744,3.448247,3.460533,3.473376,3.197192,3.243689,3.291217,3.349163,3.406142,3.240151,3.181467,3.426411,3.486206,3.417087,3.429351,3.442385,3.456393,3.367557,3.382381,3.396443,3.410753,3.309276,3.324302,3.338608,3.353022,3.254498,3.267397,3.281148,3.296317,3.209628,3.222038,3.234836,3.247829";
		final String pad5Z = "3.017053,2.961775,2.90351,2.844392,3.006528,3.000753,3.006906,3.019902,3.044355,2.794422,2.853903,2.913144,2.97015,2.765663,2.742649,2.728897,2.720239,2.723336,3.066663,2.787887,2.738466,3.022901,2.782719,2.840351,2.896654,2.951736,2.780922,2.837657,2.892415,2.94628,2.788858,2.845065,2.899184,2.952527,2.803772,2.860448,2.914728,2.967551,2.824399,2.881546,2.93707,2.990946";
		// Pad 6
		final String pad6X = "3.087017,3.054863,3.017638,2.976619,3.039143,3.007699,3.001448,3.015552,3.060339,2.934081,2.978734,3.020642,3.057425,2.88429,2.839489,2.815598,2.818003,2.847409,3.112938,2.934381,2.89064,3.087835,3.007301,2.978327,2.973214,2.990457,3.031597,2.972089,2.945,2.941481,2.96048,3.000647,2.933103,2.907663,2.905524,2.925857,2.965676,2.891002,2.865856,2.864199,2.885862,2.926588";
		final String pad6Y = "3.093271,3.080395,3.066545,3.052114,3.331654,3.286501,3.239648,3.18207,3.125222,3.292269,3.306911,3.321133,3.334332,3.050455,3.093652,3.156182,3.210929,3.260421,3.104638,3.037803,3.278301,3.346156,3.317415,3.27308,3.219512,3.162251,3.113273,3.303199,3.258302,3.202602,3.145675,3.099511,3.288634,3.243176,3.186393,3.129356,3.084651,3.274029,3.227908,3.170925,3.112559,3.068625";
		final String pad6Z = "2.315996,2.264735,2.216177,2.172208,2.360769,2.400694,2.419924,2.425226,2.40449,2.132291,2.172324,2.217823,2.266535,2.172769,2.197587,2.199045,2.180696,2.141193,2.366367,2.134391,2.099483,2.315471,2.310319,2.346492,2.366464,2.369402,2.35144,2.263229,2.299274,2.319034,2.320535,2.301837,2.219746,2.256586,2.276191,2.276355,2.255834,2.179691,2.21721,2.23662,2.235825,2.213343";
		// Pad 7
		final String pad7X = "2.533736,2.475818,2.417305,2.359853,2.511922,2.534159,2.552968,2.571897,2.586391,2.271083,2.326469,2.385193,2.444168,2.309247,2.29922,2.281272,2.262366,2.237537,2.587472,2.306535,2.223033,2.49956,2.454733,2.474845,2.49587,2.515105,2.529091,2.398522,2.419631,2.441482,2.460535,2.473287,2.343489,2.366361,2.388693,2.407208,2.418229,2.290094,2.314188,2.335977,2.35392,2.3636";
		final String pad7Y = "2.904089,2.883223,2.861295,2.838767,3.138179,3.093294,3.047693,2.994822,2.943474,3.06531,3.087827,3.110379,3.13198,2.82806,2.869413,2.922737,2.970735,3.023839,2.922865,2.816803,3.044511,3.151495,3.11614,3.073021,3.021274,2.96779,2.922935,3.093644,3.050316,2.996857,2.943572,2.901024,3.070436,3.026356,2.972642,2.919742,2.878221,3.046794,3.000672,2.94778,2.89519,2.854187";
		final String pad7Z = "1.971923,1.969126,1.97138,1.978862,2.034939,2.083262,2.097345,2.087047,2.041049,1.998067,1.982298,1.971852,1.96703,2.055576,2.107858,2.128824,2.122249,2.078773,1.978754,1.990995,2.017071,1.967262,2.033807,2.077825,2.091941,2.078189,2.035292,2.037549,2.079514,2.092079,2.076075,2.032959,2.046696,2.087027,2.097664,2.079807,2.035518,2.061011,2.100621,2.109286,2.089764,2.043274";
		// Pad 8
		final String pad8X = "1.97249,1.95542,1.94558,2.026153,2.078743,2.100906,2.095935,2.054336,1.937379,1.94024,1.948038,2.006035,2.051398,2.061609,2.046369,1.998418,1.937913,1.94089,1.994625,1.962346,2.011943,2.058768,2.079534,2.072798,2.034856,2.003073,2.047926,2.067248,2.058693,2.019825,1.999034,2.043895,2.061646,2.05163,2.010618";
		final String pad8Y = "2.618428,2.594463,2.57308,2.85824,2.818138,2.765456,2.717397,2.663018,2.798903,2.819242,2.842177,2.568782,2.61766,2.669298,2.720009,2.766957,2.783405,2.552578,2.641409,2.867973,2.829844,2.791068,2.73971,2.686846,2.63919,2.805679,2.766477,2.714898,2.660996,2.614909,2.784747,2.743594,2.691613,2.637778,2.591745";
		final String pad8Z = "2.309808,2.372727,2.431557,2.334063,2.326679,2.310914,2.293276,2.268669,2.51919,2.457632,2.394327,2.503602,2.52636,2.545494,2.561062,2.571455,2.570267,2.489708,2.253309,2.329258,2.395514,2.38637,2.370181,2.35006,2.327489,2.455476,2.44466,2.427745,2.407232,2.386281,2.514275,2.502577,2.485591,2.465277,2.444582";

		// Consolidate each pad's coordinates into a single array for the pad
		String[] pad1 = {pad1X, pad1Y, pad1Z};
		String[] pad2 = {pad2X, pad2Y, pad2Z};
		String[] pad3 = {pad3X, pad3Y, pad3Z};
		String[] pad4 = {pad4X, pad4Y, pad4Z};
		String[] pad5 = {pad5X, pad5Y, pad5Z};
		String[] pad6 = {pad6X, pad6Y, pad6Z};
		String[] pad7 = {pad7X, pad7Y, pad7Z};
		String[] pad8 = {pad8X, pad8Y, pad8Z};
		
		// Consolidate all pads along the electrode
		String[][] array = {pad1, pad2, pad3, pad4, pad5, pad6, pad7, pad8};
		
		// Parse desired pad number from argument
		int activePadNumber = Integer.parseInt(activeTerminal.substring(activeTerminal.length() - 1));
		desiredCoords = array[activePadNumber-1];
		
		return desiredCoords;
	}

}