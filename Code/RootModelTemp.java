/*
 * RootModelTemp.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Jul 24 2014, 18:36 by COMSOL 4.3.0.233. */
public class RootModelTemp {

  public static void main(String[] args) {
    run();
  }

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("B:\\Temp\\Modelling\\3. GP_complete\\4. COMSOL\\2. Validation");

    model.name("GP_v1.7_mainBV_sph_43_CG3-root.mph");

    model.modelNode().create("mod1");

    model.geom().create("geom1", 3);
    model.geom("geom1").lengthUnit("mm");

    model.mesh().create("mesh1", "geom1");
    model.mesh("mesh1").feature().create("imp1", "Import");
    model.mesh("mesh1").feature("imp1").set("source", "nastran");
    model.mesh("mesh1").feature("imp1")
         .set("filename", "S:\\Meshing\\Convergence\\GP_Santi_v1.7_main-mesh3.dat");
    model.mesh("mesh1").run();

    model.selection().create("sel1", "Explicit");
    model.selection("sel1").geom("geom1", 2);
    model.selection("sel1").set(new int[]{2464});
    model.selection().create("sel2", "Explicit");
    model.selection("sel2").geom("geom1", 2);
    model.selection("sel2").set(new int[]{5101});
    model.selection().create("sel3", "Explicit");
    model.selection("sel3").geom("geom1", 2);
    model.selection("sel3").set(new int[]{14412});
    model.selection().create("sel4", "Explicit");
    model.selection("sel4").geom("geom1", 2);
    model.selection("sel4").set(new int[]{31450});
    model.selection().create("sel5", "Explicit");
    model.selection("sel5").geom("geom1", 2);
    model.selection("sel5").set(new int[]{48406});
    model.selection().create("sel6", "Explicit");
    model.selection("sel6").geom("geom1", 2);
    model.selection("sel6").set(new int[]{43569});
    model.selection().create("sel7", "Explicit");
    model.selection("sel7").geom("geom1", 2);
    model.selection("sel7").set(new int[]{24498});
    model.selection().create("sel8", "Explicit");
    model.selection("sel8").geom("geom1", 2);
    model.selection("sel8").set(new int[]{17566});
    model.selection().create("sel9", "Explicit");
    model.selection("sel9").geom("geom1", 2);
    model.selection("sel9").set(new int[]{2436});
    model.selection().create("sel10", "Explicit");
    model.selection("sel10").geom("geom1", 2);
    model.selection("sel10").set(new int[]{5069});
    model.selection().create("sel11", "Explicit");
    model.selection("sel11").geom("geom1", 2);
    model.selection("sel11").set(new int[]{14243});
    model.selection().create("sel12", "Explicit");
    model.selection("sel12").geom("geom1", 2);
    model.selection("sel12").set(new int[]{31245});
    model.selection().create("sel13", "Explicit");
    model.selection("sel13").geom("geom1", 2);
    model.selection("sel13").set(new int[]{48356});
    model.selection().create("sel14", "Explicit");
    model.selection("sel14").geom("geom1", 2);
    model.selection("sel14").set(new int[]{43476});
    model.selection().create("sel15", "Explicit");
    model.selection("sel15").geom("geom1", 2);
    model.selection("sel15").set(new int[]{24348});
    model.selection().create("sel16", "Explicit");
    model.selection("sel16").geom("geom1", 2);
    model.selection("sel16").set(new int[]{17564, 18543});
    model.selection("sel1").name("Inner 1");
    model.selection("sel2").name("Inner 2");
    model.selection("sel3").name("Inner 3");
    model.selection("sel4").name("Inner 4");
    model.selection("sel5").name("Inner 5");
    model.selection("sel6").name("Inner 6");
    model.selection("sel7").name("Inner 7");
    model.selection("sel8").name("Inner 8");
    model.selection("sel9").name("Outer 1");
    model.selection("sel10").name("Outer 2");
    model.selection("sel11").name("Outer 3");
    model.selection("sel12").name("Outer 4");
    model.selection("sel13").name("Outer 5");
    model.selection("sel14").name("Outer 6");
    model.selection("sel15").name("Outer 7");
    model.selection("sel16").name("Outer 8");

    model.view().create("view2", "geom1");
    model.view("view2").hideEntities().create("hide1");
    model.view("view2").hideEntities("hide1").set(new int[]{4});
    model.view().create("view3", "geom1");
    model.view("view3").hideEntities().create("hide1");
    model.view("view3").hideEntities("hide1")
         .set(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15, 19, 21, 25});
    model.view().create("view4", "geom1");
    model.view("view4").hideEntities().create("hide1");
    model.view("view4").hideEntities("hide1")
         .set(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 21, 23, 24, 25, 26, 27, 28, 29});
    model.view().create("view5", "geom1");
    model.view("view5").hideEntities().create("hide1");
    model.view("view5").hideEntities("hide1")
         .set(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 25, 30});
    model.view().create("view6", "geom1");
    model.view("view6").hideEntities().create("hide1");
    model.view("view6").hideEntities("hide1").set(new int[]{4});

    model.material().create("mat1");
    model.material("mat1").selection().set(new int[]{10});
    model.material().create("mat2");
    model.material("mat2").selection()
         .set(new int[]{17, 18, 23, 24, 26, 27, 28, 29});
    model.material().create("mat3");
    model.material("mat3").selection().set(new int[]{19, 25});
    model.material().create("mat4");
    model.material("mat4").selection().set(new int[]{4});
    model.material().create("mat5");
    model.material("mat5").selection().set(new int[]{6, 11});
    model.material().create("mat6");
    model.material("mat6").selection().set(new int[]{15});
    model.material().create("mat7");
    model.material("mat7").selection().set(new int[]{21});
    model.material().create("mat8");
    model.material("mat8").selection().set(new int[]{1, 8});
    model.material().create("mat9");
    model.material("mat9").selection().set(new int[]{2, 5});
    model.material().create("mat10");
    model.material("mat10").selection().set(new int[]{16, 20, 22, 30});
    model.material().create("mat11");
    model.material("mat11").selection().set(new int[]{3});
    model.material().create("mat12");
    model.material("mat12").selection().set(new int[]{13});
    model.material().create("mat13");
    model.material("mat13").selection().set(new int[]{7});
    model.material().create("mat14");
    model.material("mat14").selection().set(new int[]{14});
    model.material().create("mat15");
    model.material("mat15").selection().set(new int[]{9});
    model.material().create("mat16");
    model.material("mat16").selection().set(new int[]{12});

    model.physics().create("ec", "ConductiveMedia", "geom1");
    model.physics("ec").feature().create("ein2", "ElectricInsulation", 2);
    model.physics("ec").feature("ein2").selection().set(new int[]{24});
    model.physics("ec").feature().create("term1", "Terminal", 2);
    model.physics("ec").feature("term1").selection().named("sel1");
    model.physics("ec").feature().create("term2", "Terminal", 2);
    model.physics("ec").feature("term2").selection().named("sel2");
    model.physics("ec").feature().create("term3", "Terminal", 2);
    model.physics("ec").feature("term3").selection().named("sel3");
    model.physics("ec").feature().create("term4", "Terminal", 2);
    model.physics("ec").feature("term4").selection().named("sel4");
    model.physics("ec").feature().create("term5", "Terminal", 2);
    model.physics("ec").feature("term5").selection().named("sel5");
    model.physics("ec").feature().create("term6", "Terminal", 2);
    model.physics("ec").feature("term6").selection().named("sel6");
    model.physics("ec").feature().create("term7", "Terminal", 2);
    model.physics("ec").feature("term7").selection().named("sel7");
    model.physics("ec").feature().create("term8", "Terminal", 2);
    model.physics("ec").feature("term8").selection().named("sel8");
    model.physics("ec").feature().create("gnd1", "Ground", 2);
    model.physics("ec").feature("gnd1").selection()
         .set(new int[]{32195, 32196});
    model.physics("ec").feature().create("term9", "Terminal", 2);
    model.physics("ec").feature("term9").selection()
         .set(new int[]{32195, 32196});
    model.physics("ec").feature().create("gnd2", "Ground", 2);
    model.physics("ec").feature("gnd2").selection()
         .set(new int[]{32193, 32194, 32195, 32196});
    model.physics("ec").feature().create("gnd3", "Ground", 2);
    model.physics("ec").feature("gnd3").selection().set(new int[]{56525});

    model.view("view1").name("All");
    model.view("view1").set("showgrid", false);
    model.view("view1").set("locked", true);
    model.view("view2").name("Cochlea");
    model.view("view2").set("showgrid", false);
    model.view("view2").set("locked", true);
    model.view("view3").name("Nerves with array");
    model.view("view3").set("showgrid", false);
    model.view("view3").set("locked", true);
    model.view("view4").name("Nerves only");
    model.view("view4").set("showgrid", false);
    model.view("view4").set("locked", true);
    model.view("view5").name("Array only");
    model.view("view5").set("showgrid", false);
    model.view("view5").set("locked", true);
    model.view("view6").name("XY plane");
    model.view("view6").set("showgrid", false);
    model.view("view6").set("scenelight", "off");

    model.material("mat1").name("Silicone");
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/(1e7)", "0", "0", "0", "1/(1e7)", "0", "0", "0", "1/(1e7)"});
    model.material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat2").name("Platinum");
    model.material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/(1.06e-7)", "0", "0", "0", "1/(1.06e-7)", "0", "0", "0", "1/(1.06e-7)"});
    model.material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat3").name("Blood");
    model.material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/1.5", "0", "0", "0", "1/1.5", "0", "0", "0", "1/1.5"});
    model.material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat4").name("Surrounding bone");
    model.material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/6.41", "0", "0", "0", "1/6.41", "0", "0", "0", "1/6.41"});
    model.material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat5").name("Otic capsule");
    model.material("mat5").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/70", "0", "0", "0", "1/70", "0", "0", "0", "1/70"});
    model.material("mat5").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat6").name("Modiolar bone");
    model.material("mat6").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/5", "0", "0", "0", "1/5", "0", "0", "0", "1/5"});
    model.material("mat6").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat7").name("CSF");
    model.material("mat7").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/0.56", "0", "0", "0", "1/0.56", "0", "0", "0", "1/0.56"});
    model.material("mat7").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat8").name("Perilymph");
    model.material("mat8").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/0.7", "0", "0", "0", "1/0.7", "0", "0", "0", "1/0.7"});
    model.material("mat8").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat9").name("Endolymph");
    model.material("mat9").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/0.6", "0", "0", "0", "1/0.6", "0", "0", "0", "1/0.6"});
    model.material("mat9").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat10").name("Nerve");
    model.material("mat10").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/3", "0", "0", "0", "1/3", "0", "0", "0", "1/3"});
    model.material("mat10").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat11").name("Reissner's membrane");
    model.material("mat11").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/10204", "0", "0", "0", "1/10204", "0", "0", "0", "1/10204"});
    model.material("mat11").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat12").name("Basilar membrane");
    model.material("mat12").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/80", "0", "0", "0", "1/80", "0", "0", "0", "1/80"});
    model.material("mat12").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat13").name("Round window membrane");
    model.material("mat13").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/1000", "0", "0", "0", "1/1000", "0", "0", "0", "1/1000"});
    model.material("mat13").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat14").name("Organ of Corti");
    model.material("mat14").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/83.333", "0", "0", "0", "1/83.333", "0", "0", "0", "1/83.333"});
    model.material("mat14").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat15").name("Spiral ligament");
    model.material("mat15").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/0.6", "0", "0", "0", "1/0.6", "0", "0", "0", "1/0.6"});
    model.material("mat15").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat16").name("Stria vascularis");
    model.material("mat16").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/188.685", "0", "0", "0", "1/188.685", "0", "0", "0", "1/188.685"});
    model.material("mat16").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});

    model.physics("ec").prop("ShapeProperty")
         .set("order_electricpotential", "1");
    model.physics("ec").feature("ein2").name("Round window insulation");
    model.physics("ec").feature("term1").selection().active(false);
    model.physics("ec").feature("term1").set("TerminalName", "Pad_1");
    model.physics("ec").feature("term1").set("I0", "0.001");
    model.physics("ec").feature("term1").active(false);
    model.physics("ec").feature("term2").selection().active(false);
    model.physics("ec").feature("term2").set("TerminalName", "Pad_2");
    model.physics("ec").feature("term2").set("I0", "0.001");
    model.physics("ec").feature("term2").active(false);
    model.physics("ec").feature("term3").selection().active(false);
    model.physics("ec").feature("term3").set("TerminalName", "Pad_3");
    model.physics("ec").feature("term3").set("I0", "0.001");
    model.physics("ec").feature("term3").active(false);
    model.physics("ec").feature("term4").selection().active(false);
    model.physics("ec").feature("term4").set("TerminalName", "Pad_4");
    model.physics("ec").feature("term4").set("I0", "0.001");
    model.physics("ec").feature("term4").active(false);
    model.physics("ec").feature("term5").selection().active(false);
    model.physics("ec").feature("term5").set("TerminalName", "Pad_5");
    model.physics("ec").feature("term5").set("I0", "0.001");
    model.physics("ec").feature("term5").active(false);
    model.physics("ec").feature("term6").selection().active(false);
    model.physics("ec").feature("term6").set("TerminalName", "Pad_6");
    model.physics("ec").feature("term6").set("I0", "0.001");
    model.physics("ec").feature("term6").active(false);
    model.physics("ec").feature("term7").selection().active(false);
    model.physics("ec").feature("term7").set("TerminalName", "Pad_7");
    model.physics("ec").feature("term7").set("I0", "0.001");
    model.physics("ec").feature("term7").active(false);
    model.physics("ec").feature("term8").selection().active(false);
    model.physics("ec").feature("term8").set("TerminalName", "Pad_8");
    model.physics("ec").feature("term8").set("I0", "0.001");
    model.physics("ec").feature("term8").active(false);
    model.physics("ec").feature("gnd1").selection().active(false);
    model.physics("ec").feature("gnd1").active(false);
    model.physics("ec").feature("gnd1").name("Temporal bone ground");
    model.physics("ec").feature("term9").selection().active(false);
    model.physics("ec").feature("term9").set("TerminalName", "Temporal_bone");
    model.physics("ec").feature("term9").set("TerminalType", "Voltage");
    model.physics("ec").feature("term9").set("I0", "-0.001");
    model.physics("ec").feature("term9").active(false);
    model.physics("ec").feature("term9").name("Temporal bone (voltage)");
    model.physics("ec").feature("gnd2").selection().active(false);
    model.physics("ec").feature("gnd2").active(false);
    model.physics("ec").feature("gnd2").name("All ground");
    model.physics("ec").feature("gnd3").selection().active(false);
    model.physics("ec").feature("gnd3").active(false);
    model.physics("ec").feature("gnd3").name("Distal nerve ground");

    model.mesh("mesh1").run();

    model.frame("material1").sorder(1);

    model.study().create("std1");
    model.study("std1").feature().create("stat", "Stationary");

    model.sol().create("sol1");
    model.sol("sol1").study("std1");
    model.sol("sol1").attach("std1");
    model.sol("sol1").feature().create("st1", "StudyStep");
    model.sol("sol1").feature().create("v1", "Variables");
    model.sol("sol1").feature().create("s1", "Stationary");
    model.sol("sol1").feature("s1").feature().create("fc1", "FullyCoupled");
    model.sol("sol1").feature("s1").feature().create("i1", "Iterative");
    model.sol("sol1").feature("s1").feature("i1").feature()
         .create("mg1", "Multigrid");
    model.sol("sol1").feature("s1").feature().remove("fcDef");

    model.batch().create("b1", "Batch");
    model.batch("b1").feature().create("cl1", "Class");
    model.batch().create("b2", "Batch");
    model.batch("b2").feature().create("cl1", "Class");
    model.batch("b1").study("std1");
    model.batch("b2").study("std1");

    model.sol("sol1").attach("std1");
    model.sol("sol1").feature("st1")
         .name("Compile Equations: Stationary {stat}");
    model.sol("sol1").feature("st1").set("studystep", "stat");
    model.sol("sol1").feature("v1").set("control", "stat");
    model.sol("sol1").feature("s1").set("control", "stat");
    model.sol("sol1").feature("s1").feature("dDef")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("i1").set("linsolver", "cg");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1")
         .set("prefun", "amg");

    model.batch("b1").feature("cl1")
         .set("filename", "B:\\Temp\\Modelling\\3. GP_complete\\4. COMSOL\\2. Validation\\Java\\bin\\SetupFromRootCG.class");
    model.batch("b1").attach("std1");
    model.batch("b1").run();
    model.batch("b2").feature("cl1")
         .set("filename", "B:\\Temp\\Modelling\\3. GP_complete\\4. COMSOL\\2. Validation\\Java\\bin\\ExportResults.class");
    model.batch("b2").run();

    model.name("GP_v1.7_mainBV_sph_43_CG3-root.mph");

    model.sol("sol1").feature("s1").feature("dDef").set("pardmtsolve", "on");
    model.sol("sol1").feature("s1").feature("dDef").active(true);

    model.name("GP_v1.7_mainBV_sph_43_CG3-root.mph");

    model.sol("sol1").feature("s1").feature("i1").active(true);

    return model;
  }

}
