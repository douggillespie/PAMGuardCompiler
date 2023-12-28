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

import java.io.Serializable;

/**
 * Holds the properties used by the ANT script to compile PAMGuard into an executable installer.
 * 
 * @author SCANS
 *
 */
public class Props implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String versionName = "PamguardBeta";
	private String javaMinVersion = "1.7.0";
	private String javaMaxVersion = "1.8.0_300";
	private String downloadUrl = "http://java.com/en/download/manual.jsp";
	private String javaInitialHeap64 = "960";
	private String javaMaxHeap64 = "2048";
	private String javaInitialHeap32 = "384";
	private String javaMaxHeap32 = "960";
	private String makingInstallersDir = "D:/Work/EclipseWorkspace/PamguardCompiler";
	private String projectSpace = "D:/Work/EclipseWorkspace/Pamguard_SMRU";
	private String launcherNameMain = "PamguardBeta";
	private String copyrightText = "Released under the GPL Version 3";
	private boolean bundleJRE = false;
	private boolean useJava12 = false;
	private boolean decimusOption = false;
	private String jarFileName = "PamguardBeta-2.01.05b.jar";
	private boolean useMaven = false;
	private String pamDogName;
	
	
	public boolean isDecimusOption() {
		return decimusOption;
	}
	public void setDecimusOption(boolean decimusOption) {
		this.decimusOption = decimusOption;
	}
	public boolean isBundleJRE() {
		return bundleJRE;
	}
	public void setBundleJRE(boolean bundleJRE) {
		this.bundleJRE = bundleJRE;
	}
	public boolean isUseJava12() {
		return useJava12;
	}
	public void setUseJava12(boolean useJava12) {
		this.useJava12 = useJava12;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public String getJavaMinVersion() {
		return javaMinVersion;
	}
	public void setJavaMinVersion(String javaMinVersion) {
		this.javaMinVersion = javaMinVersion;
	}
	public String getJavaMaxVersion() {
		return javaMaxVersion;
	}
	public void setJavaMaxVersion(String javaMaxVersion) {
		this.javaMaxVersion = javaMaxVersion;
	}
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	public String getJavaInitialHeap64() {
		return javaInitialHeap64;
	}
	public void setJavaInitialHeap64(String javaInitialHeap) {
		this.javaInitialHeap64 = javaInitialHeap;
	}
	public String getJavaMaxHeap64() {
		return javaMaxHeap64;
	}
	public void setJavaMaxHeap64(String javaMaxHeap) {
		this.javaMaxHeap64 = javaMaxHeap;
	}
	public String getMakingInstallersDir() {
		return makingInstallersDir;
	}
	public void setMakingInstallersDir(String makingInstallersDir) {
		this.makingInstallersDir = makingInstallersDir;
	}
	public String getProjectSpace() {
		return projectSpace;
	}
	public void setProjectSpace(String projectSpace) {
		this.projectSpace = projectSpace;
	}
	public String getLauncherNameMain() {
		return launcherNameMain;
	}
	public void setLauncherNameMain(String launcherNameMain) {
		this.launcherNameMain = launcherNameMain;
	}
	public String getCopyrightText() {
		return copyrightText;
	}
	public void setCopyrightText(String copyrightText) {
		this.copyrightText = copyrightText;
	}
	public String getJavaInitialHeap32() {
		return javaInitialHeap32;
	}
	public void setJavaInitialHeap32(String javaInitialHeap32) {
		this.javaInitialHeap32 = javaInitialHeap32;
	}
	public String getJavaMaxHeap32() {
		return javaMaxHeap32;
	}
	public void setJavaMaxHeap32(String javaMaxHeap32) {
		this.javaMaxHeap32 = javaMaxHeap32;
	}
	public String getJarFileName() {
		return jarFileName;
	}
	public void setJarFileName(String jarFileName) {
		this.jarFileName = jarFileName;
	}
	public boolean isUseMaven() {
		return useMaven;
	}
	public void setUseMaven(boolean useMaven) {
		this.useMaven = useMaven;
	}
	/**
	 * @return the pamDogName
	 */
	public String getPamDogName() {
		return pamDogName;
	}
	/**
	 * @param pamDogName the pamDogName to set
	 */
	public void setPamDogName(String pamDogName) {
		this.pamDogName = pamDogName;
	}
	
	
	

}
