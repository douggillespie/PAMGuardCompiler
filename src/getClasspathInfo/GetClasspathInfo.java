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



package getClasspathInfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * Copies the library information from the PAMGuard project's .classpath file, and then stores it in a
 * text file in a format the the ANT script compiling the project can read.
 * 
 * @author SCANS
 *
 */
public class GetClasspathInfo {

	File classpathFilename;
	File outputFilename;
	File pamguardPathFilename;
	
	public GetClasspathInfo(String classpathFilename, String builderFolder) {
		this.classpathFilename=new File(classpathFilename);
		this.outputFilename= new File(builderFolder, "tempclasspath.txt");
		this.pamguardPathFilename = new File(builderFolder, "tempPamguardPath.txt");
		
		ArrayList<String> libList = getXMLInfo();
		ArrayList<String> zipFileList = convertFileFormat(libList);
		writeToTextFile(zipFileList);
//		writePath(this.classpathFilename.getParent());
	}
	
	public ArrayList<String> getXMLInfo() {
		ArrayList<String> libList = new ArrayList<String>();

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(classpathFilename);
			NodeList nodeList = document.getElementsByTagName("classpathentry");
			for (int i=0; i<nodeList.getLength(); i++) {
				String kindAtt = nodeList.item(i).getAttributes().getNamedItem("kind").getNodeValue();
//				System.out.println("kindAtt = " + kindAtt);
				if (kindAtt.equals("lib")) {
					libList.add(nodeList.item(i).getAttributes().getNamedItem("path").getNodeValue());
//					System.out.println("   src = " + nodeList.item(i).getAttributes().getNamedItem("path").getNodeValue());
				}
			}
		} catch (Exception exc) {
			
		}
		return libList;
	}
	
	public ArrayList<String> convertFileFormat(ArrayList<String> libList) {
		ArrayList<String> zipFileSet = new ArrayList();
		for (String curLib : libList) {
			String properElement = "<zipfileset excludes=\"META-INF/*.SF\" src=\"${jarsDir}/"
								+ curLib.substring(9, curLib.length())
								+ "\" />\r\n"; 
			zipFileSet.add(properElement);
//			System.out.println(properElement);
		}
		return zipFileSet;
	}
	
	public void writeToTextFile(ArrayList<String> zipFileSet) {
		try {
			FileOutputStream fos = new FileOutputStream(outputFilename, false);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
			Writer writer = new BufferedWriter(osw);
			for (String curLine : zipFileSet) {
				writer.write(curLine);
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	
	public void writePath(String pamguardPath) {
		try {
			FileOutputStream fos = new FileOutputStream(pamguardPathFilename, false);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
			Writer writer = new BufferedWriter(osw);
			writer.write("<property name=\"pamguardPath\" value=\""
			+ pamguardPath
			+ "\" />");
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	@SuppressWarnings("unused")
	public static void main (String[] args) {
		GetClasspathInfo classpathinfo = new GetClasspathInfo(args[0], args[1]);
	}
}
