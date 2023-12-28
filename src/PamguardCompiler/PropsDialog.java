/*
 *  PAMGUARD - Passive Acoustic Monitoring GUARDianship.
 * To assist in the Detection Classification and Localisation
 * of marine mammals (cetaceans).
 *
 * Copyright (C) 2006
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */



package PamguardCompiler;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
//import java.nio.file.Path;
//import java.nio.file.Paths;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.JTextComponent;

import getClasspathInfo.GetClasspathInfo;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;



/**
 * <p>Class to display a dialog that allows the user to change the parameters used to compile PAMGuard, wrap it in an
 * executable and create the installer.  This class is the Main class, and does not require additional parameters to run.</p>
 * <p>This code requires a number of subfolders to exist in this project's top
 * level folder:</p>
 * <p><ul>WorkingDir - the compiled PAMGuard files and related content (icon files, splash screen, README.html, etc) are
 * saved here prior to getting bundled together in the installer executable.</ul> 
 * <ul>BuilderConfigs - contains the ANT and InnoSetup scripts necessary to create the installer, as well as
 * some temporary text files created by this program to hold the parameters set by the user</ul>
 * <ul>Tools - contains a number of different helper programs</ul>
 * <ul>Extras - contains related content (icon files, splash screen, README.html, etc).  Note that in order to actually
 * be included in the final installer, the files in this folder must also be explicitly added to the ANT script
 * found in the BuilderConfigs subfolder</ul>
 * <ul>Installers - folder containing the completed executable</ul></p>
 * <p>Once the parameters have been set and the user clicks the button to create
 * the executable, parameters are saved to a text file in the /BuilderConfigs subfolder.  The ANT script found in /BuilderConfigs
 * is then run, and progress can be viewed in the console.  If the build is successful, the GUI will close.</p>
 * <p>IMPORTANT NOTE: one of the helper programs, launch4j, only works with Java 1.6.  The Java 1.6 files can be found in the
 * Tools subfolder.  In order to run this program, the build path must be configured to use the 1.6 library instead of the
 * current library.  Follow these instructions to set up Java 1.6 for this project in Eclipse:
 * <ul>Go to Windows > Preferences > Java > Installed JREs</ul>
 * <ul>If jdk1.6 isn't in the list of installed JREs, click on Add.  Select Standard VM, and click the Directory button beside
 *  JRE home.  Navigate to the Tools subfolder and select jdk1.6.0_30.  The system libraries text box should automatically fill
 *   with the jar files found in the JRE subfolder.
 * Click the Add External JARs button and navigate to the jdk1.6.0_30/lib folder.  Highlight all the jars in this folder,
 *  and select Open to add them to the libraries list.  Hit Finish.  Now the JRE is available on your system</ul>
 * <ul>If jdk1.6 was already available in the list of installed JREs, highlight and click Edit.  Make sure that the jar files
 *  found in the jdk1.6.0_30/lib folder are included in the system libraries list.</ul>
 * <ul>Hit OK to close the Preferences window</ul>
 * <ul>Right-click on the project name in the package explorer and select Build Path > Configure Build Path.  In the Libraries
 *  tab, remove any reference to any JRE that isn't 1.6.  Click the Add Library button, select JRE System Library, and then
 *   select Execution environment.  Find the jdk1.6.0_30 in the dropdown menu, and hit Finish.</ul>
 *   
 * @author SCANS
 *
 */
public class PropsDialog extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Props props;
	private static PropsDialog frame;
	private JTextField versionName;
	private JTextField javaMinVersion;
	private JTextField javaMaxVersion;
	private JTextField downloadUrl;
	private JTextField javaInitialHeap64;
	private JTextField javaMaxHeap64;
	private JTextField javaInitialHeap32;
	private JTextField javaMaxHeap32;
	private JTextField installersDirTxt;
	private JTextField projectSpaceTxt;
	private JTextField launcherNameMain;
	private JTextField copyrightText;
	private JButton projectSpaceButton;
	private JButton installersDirButton;
	private JButton saveAndCompileButton;
	private JButton cancelButton;
	@Deprecated
	private String outputFilename = "tempProperties.txt";
	/*
	 * Save props object in a serialized data file, easier than a text file. 
	 */
	private String serDataFileName = "PAMGuardCompilerSettings.ser";
	private String antSettingsFilename = "antSettings.txt";
	private JCheckBox bundleJRE;
	private JCheckBox useJava12;
	private JCheckBox decimusOption;
	private JCheckBox mavenOption;
	private JButton jarFilenameButton;
	private JTextField jarFilenameTxt;
	private JButton pamDogJarButon;
	private JTextField pamDogFilenameTxt;
	private JCheckBox useMaven;
	 
	public PropsDialog(String name) {
		super(name);
		setResizable(false);
		this.props = new Props();
		this.loadParamsFromFile();
	}

	public void addComponentsToPane(final Container pane) {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		// Pamguard-specific parameters
		JPanel pamguardSubPanel = new JPanel();
		pamguardSubPanel.setBorder(new TitledBorder("Pamguard Source Parameters"));
        GroupLayout pamguardsubPanelLayout = new GroupLayout(pamguardSubPanel);
        pamguardSubPanel.setLayout(pamguardsubPanelLayout);

		JLabel versionLbl = new JLabel("Version Name");
		versionName = new JTextField(20);
		versionName.setToolTipText("Name used for installation folder, registry settings, Windows property info, etc.");
		projectSpaceButton = new JButton("Folder containing PAMGuard Project");
		projectSpaceButton.addActionListener(this);
		projectSpaceTxt = new JTextField(20);
		projectSpaceTxt.setEditable(false);
		projectSpaceTxt.setToolTipText("Specify the top level of the project folder.  Subfolders will include src, bin, etc.");
		jarFilenameButton = new JButton("Compiled JAR file (Maven builds only)");
		jarFilenameButton.addActionListener(this);
		jarFilenameTxt = new JTextField(20);
		jarFilenameTxt.setEditable(false);
		jarFilenameTxt.setToolTipText("<html>In a Maven project, the jar file needs to be created with the Maven shade plugin before running<br>"
				+ "PamguardCompiler.  Point to the compiled jar file here.  And MAKE SURE that the version number<br>"
				+ "in the pom.xml file matches the version number in PamguardVersionInfo!</html>");
		pamDogJarButon = new JButton("PAMDog JAR file");
		pamDogJarButon.addActionListener(this);
		pamDogFilenameTxt = new JTextField(20);
		pamDogFilenameTxt.setEditable(false);
		String dogTip = "PAMDog jar should be copied to same folder as main PAMGUard jar file for MAven build";
		pamDogJarButon.setToolTipText(dogTip);
		pamDogFilenameTxt.setToolTipText(dogTip);
		JLabel launcherNameMainLbl = new JLabel("Launcher Name Template");
		launcherNameMain = new JTextField(20);
		launcherNameMain.setToolTipText("Name used for the executable and shortcut.  _MixedMode and _ViewerMode will be automatically appended to this");
		JLabel copyrightTextLbl = new JLabel("Copyright Text");
		copyrightText = new JTextField(20);
		installersDirButton = new JButton("Folder containing Pamguard Compiler project");
		installersDirButton.addActionListener(this);
		installersDirTxt = new JTextField(20);
		installersDirTxt.setEditable(false);
		installersDirTxt.setToolTipText("Specify the top level of the project folder.  Subfolders will include src, bin, etc.");

        pamguardsubPanelLayout.setAutoCreateGaps(true);
        pamguardsubPanelLayout.setAutoCreateContainerGaps(true);
        pamguardsubPanelLayout.setHorizontalGroup(
        		pamguardsubPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        		.addGroup(pamguardsubPanelLayout.createSequentialGroup()
        				.addComponent(versionLbl)
        				.addComponent(versionName))
        		.addGroup(pamguardsubPanelLayout.createSequentialGroup()
        				.addComponent(projectSpaceButton)
        				.addComponent(projectSpaceTxt))
        		.addGroup(pamguardsubPanelLayout.createSequentialGroup()
        				.addComponent(jarFilenameButton)
        				.addComponent(jarFilenameTxt))
        		.addGroup(pamguardsubPanelLayout.createSequentialGroup()
        				.addComponent(pamDogJarButon)
        				.addComponent(pamDogFilenameTxt))
        		.addGroup(pamguardsubPanelLayout.createSequentialGroup()
        				.addComponent(launcherNameMainLbl)
        				.addComponent(launcherNameMain))
        		.addGroup(pamguardsubPanelLayout.createSequentialGroup()
        				.addComponent(copyrightTextLbl)
        				.addComponent(copyrightText))
        		.addGroup(pamguardsubPanelLayout.createSequentialGroup()
        				.addComponent(installersDirButton)
        				.addComponent(installersDirTxt))
        		);
        pamguardsubPanelLayout.setVerticalGroup(
        		pamguardsubPanelLayout.createSequentialGroup()
        		.addGroup(pamguardsubPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        				.addComponent(versionLbl)
        				.addComponent(versionName))
        		.addGroup(pamguardsubPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        				.addComponent(projectSpaceButton)
        				.addComponent(projectSpaceTxt))
        		.addGroup(pamguardsubPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        				.addComponent(jarFilenameButton)
        				.addComponent(jarFilenameTxt))
        		.addGroup(pamguardsubPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        				.addComponent(pamDogJarButon)
        				.addComponent(pamDogFilenameTxt))
        		.addGroup(pamguardsubPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        				.addComponent(launcherNameMainLbl)
        				.addComponent(launcherNameMain))
        		.addGroup(pamguardsubPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        				.addComponent(copyrightTextLbl)
        				.addComponent(copyrightText))
        		.addGroup(pamguardsubPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        				.addComponent(installersDirButton)
        				.addComponent(installersDirTxt))
        		);
        pamguardsubPanelLayout.linkSize(SwingConstants.HORIZONTAL, versionLbl,
        		projectSpaceButton,
        		jarFilenameButton,
        		pamDogJarButon,
        		launcherNameMainLbl,
        		copyrightTextLbl,
        		installersDirButton);

		// Java-specific parameters
		JPanel javaSubPanel = new JPanel();
		javaSubPanel.setBorder(new TitledBorder("Java Parameters"));
        GroupLayout javaSubPanelLayout = new GroupLayout(javaSubPanel);
        javaSubPanel.setLayout(javaSubPanelLayout);

		JLabel javaMinVersionLbl = new JLabel("Minimum Java Version");
		javaMinVersion = new JTextField(30);
		JLabel javaMaxVersionLbl = new JLabel("Maximum Java Version");
		javaMaxVersion = new JTextField(30);
		JLabel downloadUrlLbl = new JLabel("Java download URL");
		downloadUrl = new JTextField(30);
		JLabel javaInitialHeapLbl32 = new JLabel("Java Initial Heap Size (32 bit)");
		javaInitialHeap32 = new JTextField(30);
		JLabel javaMaxHeapLbl32 = new JLabel("Java Max Heap Size (32 bit)");
		javaMaxHeap32= new JTextField(30);
		JLabel javaInitialHeapLbl64 = new JLabel("Java Initial Heap Size (64 bit)");
		javaInitialHeap64 = new JTextField(30);
		JLabel javaMaxHeapLbl64 = new JLabel("Java Max Heap Size (64 bit)");
		javaMaxHeap64= new JTextField(30);
		JLabel bundleJRELbl = new JLabel("Bundle JRE 1.8.0_201 with Installer");
		bundleJRELbl.setToolTipText("If checked, will bundle 32 and 64 bit versions of Java JRE 1.8.0_201 with the installer");
		bundleJRE = new JCheckBox();
		bundleJRE.setToolTipText("If checked, will bundle 32 and 64 bit versions of Java JRE 1.8.0_201 with the installer");
		bundleJRE.addActionListener(this);
		JLabel useJava12Lbl = new JLabel("Bundle JRE 12+ with Installer");
		useJava12Lbl.setToolTipText("If checked, will bundle 64 bit version of Java JRE 12 (or newer) with the installer");
		useJava12 = new JCheckBox();
		useJava12.setToolTipText("If checked, will bundle 64 bit version of Java JRE 12 (or newer) with the installer");
		useJava12.addActionListener(this);
		JLabel useMavenLbl = new JLabel("This is a Maven project");
		useMavenLbl.setToolTipText("If checked, will bundle the jar file specified above instead of trying to compile a new jar");
		useMaven = new JCheckBox();
		useMaven.setToolTipText("If checked, will bundle the jar file specified above instead of trying to compile a new jar");
		useMaven.addActionListener(this);
		JLabel decimusOptionLbl = new JLabel("Add shortcut for Decimus System");
		decimusOptionLbl.setToolTipText("If checked, an additional shortcut will be created for the Decimus system");
		decimusOption = new JCheckBox();
		decimusOption.setToolTipText("If checked, an additional shortcut will be created for the Decimus system");
		
        javaSubPanelLayout.setAutoCreateGaps(true);
        javaSubPanelLayout.setAutoCreateContainerGaps(true);
        javaSubPanelLayout.setHorizontalGroup(
        		javaSubPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        		.addGroup(javaSubPanelLayout.createSequentialGroup()
        				.addComponent(javaMinVersionLbl)
        				.addComponent(javaMinVersion))
        		.addGroup(javaSubPanelLayout.createSequentialGroup()
        				.addComponent(javaMaxVersionLbl)
        				.addComponent(javaMaxVersion))
        		.addGroup(javaSubPanelLayout.createSequentialGroup()
        				.addComponent(downloadUrlLbl)
        				.addComponent(downloadUrl))
        		.addGroup(javaSubPanelLayout.createSequentialGroup()
        				.addComponent(javaInitialHeapLbl32)
        				.addComponent(javaInitialHeap32))
        		.addGroup(javaSubPanelLayout.createSequentialGroup()
        				.addComponent(javaMaxHeapLbl32)
        				.addComponent(javaMaxHeap32))
        		.addGroup(javaSubPanelLayout.createSequentialGroup()
        				.addComponent(javaInitialHeapLbl64)
        				.addComponent(javaInitialHeap64))
        		.addGroup(javaSubPanelLayout.createSequentialGroup()
        				.addComponent(javaMaxHeapLbl64)
        				.addComponent(javaMaxHeap64))
        		.addGroup(javaSubPanelLayout.createSequentialGroup()
        				.addComponent(bundleJRELbl)
        				.addComponent(bundleJRE))
        		.addGroup(javaSubPanelLayout.createSequentialGroup()
        				.addComponent(useJava12Lbl)
        				.addComponent(useJava12))
        		.addGroup(javaSubPanelLayout.createSequentialGroup()
        				.addComponent(useMavenLbl)
        				.addComponent(useMaven))
        		.addGroup(javaSubPanelLayout.createSequentialGroup()
        				.addComponent(decimusOptionLbl)
        				.addComponent(decimusOption))
        		);
        javaSubPanelLayout.setVerticalGroup(
        		javaSubPanelLayout.createSequentialGroup()
        		.addGroup(javaSubPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        				.addComponent(javaMinVersionLbl)
        				.addComponent(javaMinVersion))
        		.addGroup(javaSubPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        				.addComponent(javaMaxVersionLbl)
        				.addComponent(javaMaxVersion))
        		.addGroup(javaSubPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        				.addComponent(downloadUrlLbl)
        				.addComponent(downloadUrl))
        		.addGroup(javaSubPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        				.addComponent(javaInitialHeapLbl32)
        				.addComponent(javaInitialHeap32))
        		.addGroup(javaSubPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        				.addComponent(javaMaxHeapLbl32)
        				.addComponent(javaMaxHeap32))
        		.addGroup(javaSubPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        				.addComponent(javaInitialHeapLbl64)
        				.addComponent(javaInitialHeap64))
        		.addGroup(javaSubPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        				.addComponent(javaMaxHeapLbl64)
        				.addComponent(javaMaxHeap64))
        		.addGroup(javaSubPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        				.addComponent(bundleJRELbl)
        				.addComponent(bundleJRE))
        		.addGroup(javaSubPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        				.addComponent(useJava12Lbl)
        				.addComponent(useJava12))
        		.addGroup(javaSubPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        				.addComponent(useMavenLbl)
        				.addComponent(useMaven))
        		.addGroup(javaSubPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        				.addComponent(decimusOptionLbl)
        				.addComponent(decimusOption))
        		);
        javaSubPanelLayout.linkSize(SwingConstants.HORIZONTAL, javaMinVersionLbl,
        		javaMaxVersionLbl, 
        		downloadUrlLbl,
        		javaInitialHeapLbl64,
        		javaMaxHeapLbl64,
        		javaInitialHeapLbl32,
        		javaMaxHeapLbl32,
        		bundleJRELbl,
        		decimusOptionLbl);

		// add subframes to main frame
		mainPanel.add(pamguardSubPanel);
		mainPanel.add(javaSubPanel);
		saveAndCompileButton = new JButton("<html><center>Save Parameters and Create PAMGuard Executable.  See console window for progress.<br>" +
				"Window will close upon successfull completion of ANT script</center></html>");
		saveAndCompileButton.addActionListener(this);
		saveAndCompileButton.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(saveAndCompileButton);
		cancelButton = new JButton("Cancel Operation and Exit");
		cancelButton.addActionListener(this);
		cancelButton.setAlignmentX(CENTER_ALIGNMENT);
		cancelButton.setSize(saveAndCompileButton.getWidth(), saveAndCompileButton.getHeight());
		mainPanel.add(cancelButton);
		pane.add(mainPanel);
		
		// set the params to the latest from the Props object
		this.setParams();
    }
     	  
	  
    /**
     * Create the GUI and show it.  For thread safety,
     * this method is invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
    	frame = new PropsDialog("Pamguard Compiler and Executable Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    
    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
    	try {		
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == projectSpaceButton) {
            projectSpaceTxt.setText(selectDirectory(projectSpaceTxt));
        } else if  (e.getSource() == jarFilenameButton) {
        	jarFilenameTxt.setText(selectFile(projectSpaceTxt));
        } else if  (e.getSource() == pamDogJarButon) {
        	pamDogFilenameTxt.setText(selectFile(projectSpaceTxt));
        } else if  (e.getSource() == installersDirButton) {
        	installersDirTxt.setText(selectDirectory(installersDirTxt));
        } else if (e.getSource() == saveAndCompileButton) {
        	this.exitProcedure();
        } else if (e.getSource() == cancelButton) {
        	frame.dispose();
        	System.exit(0);
        } else if (e.getSource() == bundleJRE) {
        	if (bundleJRE.isSelected()) {
        		useJava12.setSelected(false);
        	}
        } else if (e.getSource() == useJava12) {
        	if (useJava12.isSelected()) {
        		bundleJRE.setSelected(false);
        	}
        } else if (e.getSource() == useMaven) {
        	jarFilenameButton.setEnabled(useMaven.isSelected());
        	jarFilenameTxt.setEnabled(useMaven.isSelected());
        }
	}
    
	/**
	 * Let the user select a folder, and return the absolute path
	 * 
	 * @param fieldToSet Which field the user is currently setting
	 * @return the absolute path to the folder selected by the user
	 */
	protected String selectDirectory(JTextField fieldToSet) {
		String currDir = fieldToSet.getText();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select folder...");
		fileChooser.setFileHidingEnabled(true);
		fileChooser.setApproveButtonText("Select");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		if (currDir != null) fileChooser.setSelectedFile(new File(currDir));
		int state = fileChooser.showOpenDialog(fieldToSet);
		if (state == JFileChooser.APPROVE_OPTION) {
			currDir = fileChooser.getSelectedFile().getAbsolutePath();
		}
		return currDir;
	}
	
	protected String selectFile(JTextField projectDir) {
		String currDir = projectDir.getText() + File.separator + "target";
		String jarFile = null;
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select jar file...");
		fileChooser.setFileHidingEnabled(true);
		fileChooser.setApproveButtonText("Select");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Java executable JAR files", "jar");
		fileChooser.addChoosableFileFilter(filter);

		if (currDir != null) fileChooser.setSelectedFile(new File(currDir));
		int state = fileChooser.showOpenDialog(projectDir);
		if (state == JFileChooser.APPROVE_OPTION) {
			jarFile = fileChooser.getSelectedFile().getName();
		}
		return jarFile;
	}
    	
	
	/**
     * sets up the labels to be shown in the dialog box, based on the current
     * Props object
     */
	public void setParams() {
		versionName.setText(props.getVersionName());
		javaMinVersion.setText(props.getJavaMinVersion());
		javaMaxVersion.setText(props.getJavaMaxVersion());
		downloadUrl.setText(props.getDownloadUrl());
		javaInitialHeap32.setText(props.getJavaInitialHeap32());
		javaMaxHeap32.setText(props.getJavaMaxHeap32());
		javaInitialHeap64.setText(props.getJavaInitialHeap64());
		javaMaxHeap64.setText(props.getJavaMaxHeap64());
		installersDirTxt.setText(props.getMakingInstallersDir());
		projectSpaceTxt.setText(props.getProjectSpace());
		launcherNameMain.setText(props.getLauncherNameMain());
		copyrightText.setText(props.getCopyrightText());
		bundleJRE.setSelected(props.isBundleJRE());
		useJava12.setSelected(props.isUseJava12());
		useMaven.setSelected(props.isUseMaven());
		decimusOption.setSelected(props.isDecimusOption());
		jarFilenameTxt.setText(props.getJarFileName());
		jarFilenameTxt.setEnabled(props.isUseMaven());
		jarFilenameButton.setEnabled(props.isUseMaven());
		pamDogFilenameTxt.setText(props.getPamDogName());
	}
	
	/**
	 * Gets the parameters from the labels and sets the Props object fields
	 */
	public void getParams() {
		props.setVersionName(versionName.getText());
		props.setJavaMinVersion(javaMinVersion.getText());
		props.setJavaMaxVersion(javaMaxVersion.getText());
		props.setDownloadUrl(downloadUrl.getText());
		props.setJavaInitialHeap32(javaInitialHeap32.getText());
		props.setJavaMaxHeap32(javaMaxHeap32.getText());
		props.setJavaInitialHeap64(javaInitialHeap64.getText());
		props.setJavaMaxHeap64(javaMaxHeap64.getText());
		props.setMakingInstallersDir(installersDirTxt.getText());
		props.setProjectSpace(projectSpaceTxt.getText());
		props.setLauncherNameMain(launcherNameMain.getText());
		props.setCopyrightText(copyrightText.getText());
		props.setBundleJRE(bundleJRE.isSelected());
		props.setUseJava12(useJava12.isSelected());
		props.setUseMaven(useMaven.isSelected());
		props.setDecimusOption(decimusOption.isSelected());
		props.setJarFileName(jarFilenameTxt.getText());
		props.setPamDogName(pamDogFilenameTxt.getText());
	}
	
	public void saveParamsToFile() {
		try {
//			FileOutputStream fos = new FileOutputStream(new File(props.getMakingInstallersDir(), outputFilename));
			FileOutputStream fos = new FileOutputStream(new File(System.getProperty("user.dir"), serDataFileName));
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(props);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * Save the current parameters to the temp file
	 */
	@Deprecated
	public void saveParamsToFileTxt() {
		try {
//			FileOutputStream fos = new FileOutputStream(new File(props.getMakingInstallersDir(), outputFilename));
			FileOutputStream fos = new FileOutputStream(new File(System.getProperty("user.dir"), outputFilename));
			OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
			Writer writer = new BufferedWriter(osw);
			writer.write(props.getVersionName() + "\r\n");
			writer.write(props.getJavaMinVersion() + "\r\n");
			writer.write(props.getJavaMaxVersion() + "\r\n");
			writer.write(props.getDownloadUrl() + "\r\n");
			writer.write(props.getJavaInitialHeap32() + "\r\n");
			writer.write(props.getJavaMaxHeap32() + "\r\n");
			writer.write(props.getJavaInitialHeap64() + "\r\n");
			writer.write(props.getJavaMaxHeap64() + "\r\n");
			writer.write(props.getMakingInstallersDir() + "\r\n");
			writer.write(props.getProjectSpace() + "\r\n");
			writer.write(props.getLauncherNameMain() + "\r\n");
			writer.write(props.getCopyrightText() + "\r\n");
			if (props.isBundleJRE()) {
				writer.write("1\r\n");
			} else {
				writer.write("0\r\n");
			}
			if (props.isUseJava12()) {
				writer.write("1\r\n");
			} else {
				writer.write("0\r\n");
			}
			if (props.isUseMaven()) {
				writer.write("1\r\n");
			} else {
				writer.write("0\r\n");
			}
			if (props.isDecimusOption()) {
				writer.write("1\r\n");
			} else {
				writer.write("0\r\n");
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public boolean loadParamsFromFile() {
//		File outputFile = new File(props.getMakingInstallersDir(), outputFilename);
		File outputFile = new File(System.getProperty("user.dir"), serDataFileName);
		if (!outputFile.exists()) {
			return false;
		}
		try {
			FileInputStream fis = new FileInputStream(outputFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			props = (Props) ois.readObject();
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Load parameters from the temp file
	 * 
	 * @return true if successful
	 */
	@Deprecated
	public boolean loadParamsFromTxtFile() {
//		File outputFile = new File(props.getMakingInstallersDir(), outputFilename);
		File outputFile = new File(System.getProperty("user.dir"), outputFilename);
		if (!outputFile.exists()) {
			return false;
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(outputFile));
			props.setVersionName(br.readLine());
			props.setJavaMinVersion(br.readLine());
			props.setJavaMaxVersion(br.readLine());
			props.setDownloadUrl(br.readLine());
			props.setJavaInitialHeap32(br.readLine());
			props.setJavaMaxHeap32(br.readLine());
			props.setJavaInitialHeap64(br.readLine());
			props.setJavaMaxHeap64(br.readLine());
			props.setMakingInstallersDir(br.readLine());
			props.setProjectSpace(br.readLine());
			props.setLauncherNameMain(br.readLine());
			props.setCopyrightText(br.readLine());
			String bundle = br.readLine();
			if (bundle == null || bundle.equals("0")) {
				props.setBundleJRE(false);
			} else {
				props.setBundleJRE(true);
			}
			bundle = br.readLine();
			if (bundle == null || bundle.equals("0")) {
				props.setUseJava12(false);
			} else {
				props.setUseJava12(true);
			}
			bundle = br.readLine();
			if (bundle == null || bundle.equals("0")) {
				props.setUseMaven(false);
			} else {
				props.setUseMaven(true);
			}
			bundle = br.readLine();
			if (bundle == null || bundle.equals("0")) {
				props.setDecimusOption(false);
			} else {
				props.setDecimusOption(true);
			}
			br.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Generate the settings file that the ANT build will read in as properties.
	 */
	public void generateSettingsFile() {
		try {
			File builderDir = new File(props.getMakingInstallersDir(), "BuilderConfigs");
//			Path p = Paths.get(props.getMakingInstallersDir(), "BuilderConfigs");
			FileOutputStream fos = new FileOutputStream(new File(builderDir, antSettingsFilename));
			OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
			Writer writer = new BufferedWriter(osw);
			writer.write("<property name=\"versionName\" value=\"" + props.getVersionName() + "\" />\r\n");
			writer.write("<property name=\"javaMinVersion\" value=\"" + props.getJavaMinVersion() + "\" />\r\n");
			writer.write("<property name=\"javaMaxVersion\" value=\"" + props.getJavaMaxVersion() + "\" />\r\n");
			writer.write("<property name=\"downloadUrl\" value=\"" + props.getDownloadUrl() + "\" />\r\n");
			writer.write("<property name=\"javaInitialHeap32\" value=\"" + props.getJavaInitialHeap32() + "\" />\r\n");
			writer.write("<property name=\"javaMaxHeap32\" value=\"" + props.getJavaMaxHeap32() + "\" />\r\n");
			writer.write("<property name=\"javaInitialHeap64\" value=\"" + props.getJavaInitialHeap64() + "\" />\r\n");
			writer.write("<property name=\"javaMaxHeap64\" value=\"" + props.getJavaMaxHeap64() + "\" />\r\n");
			writer.write("<property name=\"makingInstallersDir\" value=\"" + props.getMakingInstallersDir() + "\" />\r\n");
			writer.write("<property name=\"projectSpace\" value=\"" + props.getProjectSpace() + "\" />\r\n");
			if (props.isUseJava12()) {
				writer.write("<property name=\"launcherNameMain\" value=\"" + props.getLauncherNameMain() + "\" />\r\n");
			} else {
				writer.write("<property name=\"launcherNameMain\" value=\"" + props.getLauncherNameMain() + "64\" />\r\n");
			}
			if (props.isUseMaven()) {
				writer.write("<property name=\"jarFileName\" value=\"" + jarFilenameTxt.getText() + "\" />\r\n");
			}
			writer.write("<property name=\"dogFileName\" value=\"" + pamDogFilenameTxt.getText() + "\" />\r\n");
			writer.write("<property name=\"launcherNameMain32\" value=\"" + props.getLauncherNameMain() + "32\" />\r\n");
			writer.write("<property name=\"copyrightText\" value=\"" + props.getCopyrightText() + "\" />\r\n");
			String decimusSelection="0";
			if (props.isDecimusOption()) decimusSelection="1";
			writer.write("<property name=\"decimusOption\" value=\"" + decimusSelection + "\" />\r\n");
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
    /**
     * To execute a target specified in the Ant build.xml file
     * 
     * @param buildXmlFileFullPath
     * @param target
     */
    public boolean executeAntTask(String buildXmlFileFullPath, String target) {
        boolean success = false;
        DefaultLogger consoleLogger = getConsoleLogger();
 
        // Prepare Ant project
        Project project = new Project();
        File buildFile = new File(buildXmlFileFullPath);
        project.setUserProperty("ant.file", buildFile.getAbsolutePath());
        project.addBuildListener(consoleLogger);
 
        // Capture event for Ant script build start / stop / failure
        try {
            project.fireBuildStarted();
            project.init();
            ProjectHelper projectHelper = ProjectHelper.getProjectHelper();
            project.addReference("ant.projectHelper", projectHelper);
            projectHelper.parse(project, buildFile);
             
            // If no target specified then default target will be executed.
            String targetToExecute = (target != null && target.trim().length() > 0) ? target.trim() : project.getDefaultTarget();
            project.executeTarget(targetToExecute);
            project.fireBuildFinished(null);
            success = true;
        } catch (BuildException buildException) {
            project.fireBuildFinished(buildException);
            throw new RuntimeException("!!! Problem running the ANT script", buildException);
        }
         
        return success;
    }
     
    /**
     * Logger to log output generated while executing ant script in console
     * 
     * @return
     */
    private static DefaultLogger getConsoleLogger() {
        DefaultLogger consoleLogger = new DefaultLogger();
        consoleLogger.setErrorPrintStream(System.err);
        consoleLogger.setOutputPrintStream(System.out);
        consoleLogger.setMessageOutputLevel(Project.MSG_INFO);
         
        return consoleLogger;
    }
     
    /**
     * Save all parameters, run the ANT file and exit
     */
	@SuppressWarnings("unused")
	public void exitProcedure() {
		
		// save the current parameters to the temp file
		this.getParams();
		this.saveParamsToFile();
		
		// generate the settings file that the ANT build will need
		this.generateSettingsFile();
		
		// generate the classpath info that the ANT build will need
		String classpathFilename = (new File(props.getProjectSpace(), ".classpath")).getAbsolutePath();
//		Path p = Paths.get(props.getMakingInstallersDir(), "BuilderConfigs");
		File builderDir = new File(props.getMakingInstallersDir(), "BuilderConfigs");
		GetClasspathInfo gci = new GetClasspathInfo(classpathFilename, builderDir.toString());
		
		// run the ANT build
//		Path antBuilder = Paths.get(p.toString(), "Create_Pamguard_Executable.xml");
		File antBuilder;
		if (useMaven.isSelected()) {
			antBuilder = new File(builderDir, "Create_Pamguard_Executable_withMaven.xml");
		} else if (useJava12.isSelected()) {
				antBuilder = new File(builderDir, "Create_Pamguard_Executable_withJDK12.xml");
		} else if (bundleJRE.isSelected()) {
			antBuilder = new File(builderDir, "Create_Pamguard_Executable_withJRE.xml");
		} else {
			antBuilder = new File(builderDir, "Create_Pamguard_Executable.xml");
		}
		this.executeAntTask(antBuilder.toString(), "clean");
		this.executeAntTask(antBuilder.toString(), "make_installer");
		
		// exit the GUI
		frame.dispose();
		System.exit(0);
	}
}
