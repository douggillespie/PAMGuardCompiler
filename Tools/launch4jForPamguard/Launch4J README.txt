Changes to Launch4J for Pamguard
- comparing the Launch4J_for_Pamguard files from 2012 with the latest 2017 files in SourceForge, looking for differences to see what might have been added for Pamguard
- there are a lot of diffs, but most are due to the files being updated with generics now.  Also, author seems to have gotten rid of list iterators in favor of for loops (?)

ant\Launch4jTask.java - added Strings productName, dumpFileName and dumpFileDirPath (and associated methods - pretty sure this was added for Pamguard, b/c ant file sets this property)
binding\Validator.java - added checkElementsNotNullUnique and checkElementsUnique (????? not sure about this one - not added back in yet)
config\ClassPath.java - added classPathFromIni variable and methods isClassPathFromIni and setClassPathFromIni (not sure if req'd, but can't hurt)
config\Config.java:
-added CUSTOM_PROC_NAME global variable and customProcName variable (need these)
-added jarFromIni boolean (need this)
-added methods isCustomProcName and setCustomProcName (need these)
-added methods isJarFromIni and setJarFromIni (need these)
config\ConfigPersister.java:
- added setCustomProcName call to loadVersion1 method
- it looks like when the code converts old formats to new in the convertToCurrent method it sets customProcName to blank.  Commented this line out, since we want to use this property
config\Jre.java - newer code seems to have replaced archRequirement with runtimeBits; have updated Create_Pamguard_Executable.xml to reflect this...



****************************
STOP!!!!! Realized that newest version of Launch4j dropped customProcName because of probs w/ Windows and UAC.  But we like this feature (forces Windows to display as Pamguard in the Processes list instead of javac) therefore need to just make changes to the old code and not upgrade to the new...
****************************

How to compile Launch4J in Eclipse after changing code...
1) Right-click on build.xml file, found in folder PamguardCompiler\Tools\launch4jForPamguard.  Select 'run-as' and pick the second Ant Build option
2) You are given a list of potential targets, and the execution order.  Check the boxes so they are in this order: clean, init, compile, jar
3) hit Run.  The code will be compiled and a new jar file saved in the launch4jForPamguard folder.  The next time PamguardCompiler is run, it will use this jar to create the executable wrappers




