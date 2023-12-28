; Script generated by the Inno Script Studio Wizard.
; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!


; Help files for Inno Setup (what each section does and parameter conventions, etc) found here:
;     http://www.jrsoftware.org/ishelp/index.php

; Help file for Inno Setup Preprocessor (the #define params below, and how to use them) found here:
;     http://www.jrsoftware.org/ispphelp/index.php 
; The preprocessor runs first and 'changes' the script, below, based on the preprocessor variables that
; have been passed in from the Ant script.  Anywhere it finds a reference to a defined variable that
; starts with '{#' (e.g. {#launcherNameMain} ) it will replace that reference with the actual variable
; value (e.g. 'PamguardBeta64').  One other important thing you can do is surround parts of the script
; with the #if #endif directive, to include/exclude sections of the script.  For example:
; #if decimus == "1"
;    (do something here)
; #endif 
; The code between the #if and #endif is only included in the final compiled version of the script
; if the flag decimus = 1.
; Once the preprocessor has finished it's mods to the script, it compiles it and then Inno Setup
; runs and actually performs the operations.
; This is only currently used in the [Icons] section, because I only learned about it now.  #decimus and
; #bundleJRE are used inline in a bunch of places, but all of those could be replaced with #if #endif
; statements if you have nothing better to do.

; the following parameters are passed into the script from the command line.  If
; you want to run this script interactively in Inno Script Studio, uncomment
; these lines first (or figure out how to pass command line parameters within Inn Script Studio)
;#define MyAppName "PamguardBeta"
;#define MyAppVersion "2_00_16"
;#define ISSI_Splash "C:\Users\mo55\workspace\PamguardCompiler\Extras\PamguardInstallerSplash.bmp"
;#define ISSI_IncludePath "C:\Users\mo55\workspace\PamguardCompiler\Tools\Inno_Setup_5\ISSI"
;#define majorVersion "2"
;#define MyVersionInfo "2.00.16"
;#define launcherNameMain "PamguardBeta64";#define launcherNameMixed "PamguardBeta64m"
;#define launcherNameViewer "PamguardBeta64v"
;#define launcherNameDecimus "PamguardBeta64d"
;#define launcherNameMain32 "PamguardBeta32"
;#define launcherNameMixed32 "PamguardBeta32m"
;#define launcherNameViewer32 "PamguardBeta32v"
;#define launcherNameDecimus32 "PamguardBeta32d"
;#define launcherNamePamDog "Pamguard_WatchDog"
;#define sourceDir "C:\Users\mo55\workspace\PamguardCompiler\WorkingDir\PamguardBeta";#define outputDir "C:\Users\mo55\workspace\PamguardCompiler\InnoScript-created"
;#define installerName "PamguardBeta_Setup"
;#define licenseFilename "C:\Users\mo55\workspace\PamguardCompiler\WorkingDir\PamguardBeta\License_GNU_GPL3.txt"
;#define setupIcon "C:\Users\mo55\workspace\PamguardCompiler\Extras\PAMGUARDlogoNew2.ico"
;#define jarFilename "C:\Program Files\PamguardBeta\PamguardBeta_2_00_16.jar"
;#define javaMinVersion "1.7.0"
;#define bundleJRE = "2"
;#define decimus = "0"


; don't comment these out
#define MyAppPublisher "SMRU"
#define MyAppURL "http://www.pamguard.org"
#define ISSI_Splash_T 2
#define ISSI_Splash_X 747
#define ISSI_Splash_Y 181
#define ISSI_UseMyInitializeSetup

[Setup]
; NOTE: The value of AppId uniquely identifies this application.
; Do not use the same AppId value in installers for other applications.
; (To generate a new GUID, click Tools | Generate GUID inside the IDE.)
AppId={{D8F2D0E5-B7E8-4B24-9F40-0ED8BE73C4F1}
AppName={#MyAppName}
AppVersion={#MyAppVersion}
AppPublisher={#MyAppPublisher}
AppPublisherURL={#MyAppURL}
AppSupportURL={#MyAppURL}
AppUpdatesURL={#MyAppURL}
DefaultDirName={pf}\{#MyAppName}
DefaultGroupName={#MyAppName}
LicenseFile={#licenseFilename}
OutputDir={#outputDir}
OutputBaseFilename={#installerName}
SetupIconFile={#setupIcon}
Compression=lzma
SolidCompression=yes
UninstallDisplayName={#MyAppName}-{#MyAppVersion}
UsePreviousAppDir=False
UsePreviousGroup=False
ArchitecturesInstallIn64BitMode=x64 ia64
; VersionInfoVersion={#MyVersionInfo} - removed - never used anywhere anyway, and had problems when letters were included in the version number
VersionInfoCompany={#MyAppPublisher}
VersionInfoDescription=Passive Acoustic Monitoring Software Suite
VersionInfoTextVersion={#MyAppVersion}
VersionInfoCopyright=2018
VersionInfoProductName={#MyAppName}
; VersionInfoProductVersion={#MyVersionInfo} - removed - never used anywhere anyway, and had problems when letters were included in the version number
VersionInfoProductTextVersion={#MyAppVersion}
ChangesAssociations=True
SetupLogging=True
DisableProgramGroupPage=no

[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"

[Files]
Source: "{#sourceDir}\*"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs
; NOTE: Don't use "Flags: ignoreversion" on any shared system files

[Icons]
;Name: "{group}\Pamguard"; Filename: "{app}\{#launcherNameMain32}.exe"; WorkingDir: "{app}"; IconFilename: "{app}\PAMGUARDlogoNew2.ico"
;Name: "{group}\Pamguard Mixed Mode"; Filename: "{app}\{#launcherNameMixed32}.exe"; WorkingDir: "{app}"; IconFilename: "{app}\pamguardIconMixedMode2.ico"
;Name: "{group}\Pamguard Viewer Mode"; Filename: "{app}\{#launcherNameViewer32}.exe"; WorkingDir: "{app}"; IconFilename: "{app}\pamguardIconViewerMode2.ico"
Name: "{group}\{code:WhichNameToUse}"; Filename: "{code:WhichMainExecToUse}"; WorkingDir: "{app}"; IconFilename: "{app}\PAMGUARDlogoNew2.ico"
Name: "{group}\{code:WhichNameToUse} Mixed Mode"; Filename: "{code:WhichMixedExecToUse}"; WorkingDir: "{app}"; IconFilename: "{app}\pamguardIconMixedMode2.ico"
Name: "{group}\{code:WhichNameToUse} Viewer Mode"; Filename: "{code:WhichViewerExecToUse}"; WorkingDir: "{app}"; IconFilename: "{app}\pamguardIconViewerMode2.ico"
#if decimus == "1"
   Name: "{group}\{code:WhichNameToUse} Decimus"; Filename: "{code:WhichDecimusExecToUse}"; WorkingDir: "{app}"; IconFilename: "{app}\PAMGUARDnetwork.ico"; Flags: createonlyiffileexists
   Name: "{group}\{code:WhichNameToUse} Network Monitor"; Filename: "{code:WhichNMExecToUse}"; WorkingDir: "{app}"; IconFilename: "{app}\NetworkMonitor.ico"; Flags: createonlyiffileexists
#endif
Name: "{group}\{cm:ProgramOnTheWeb,{#MyAppName}}"; Filename: "{#MyAppURL}"; IconFilename: "{app}\PAMGUARDlogoNew2.ico"
Name: "{group}\{cm:UninstallProgram,{#MyAppName}}"; Filename: "{uninstallexe}"

[Dirs]
Name: "{app}\plugins"; Flags: uninsneveruninstall

[Run]
Filename: "{app}\README.html"; Flags: postinstall shellexec skipifdoesntexist skipifsilent

[Messages]
ConfirmUninstall=This will uninstall %1 and delete any files and subfolders.  This will include any saved *.psf files in the folder, but will not delete the plugins folder or any plugin files.  Continue?

[Registry]
; don't run anything here - all registry oeprations are performed in the updateINI() function instead
; Root: "HKCR"; Subkey: ".psf"; ValueType: string; ValueData: "{#MyAppName}Settings"; Flags: uninsdeletekey
; Root: "HKCR"; Subkey: "{#MyAppName}Settings"; ValueType: string; ValueData: "Pamguard Settings File"; Flags: uninsdeletekey
; Root: "HKCR"; Subkey: "{#MyAppName}Settings\DefaultIcon"; ValueType: string; ValueData: "{app}\{#launcherNameMain}.exe,0"
; Root: "HKCR"; Subkey: "{#MyAppName}Settings\shell\open\command"; ValueType: string; ValueData: """{app}\{#launcherNameMain}.exe"" ""-psf"" ""%1"""

[Code]
// define global variables
var
  UsagePage: TInputOptionWizardPage;
  CmdLineParamsPage: TInputQueryWizardPage;

// update the 3 INI files to point to the correct library (based on 32/64bit installation) and
// jar file location.  Delete the ones that are not needed.  Set the registry values
// to allow double-clicking on a psf file.  Also, if we are bundling the JRE in with the
// application, delete the version (32 or 64 bit) that is not required  
function updateINI():boolean;
var
  regLine: string;
  regLineDog: string;
begin
  result := true;
  try

    // set the library location, based on the value selected on the UsagePage
    // also set the full path to the jar file
    // UsagePage=0 means 32bit, UsagePage=1 means 64bit
    regLine := ExpandConstant('-jar "{app}\{#jarFilename}"') + #13#10 + CmdLineParamsPage.Values[0];
    regLineDog := ExpandConstant('-jar "{app}\{#dogFilename}"') + #13#10;
    if (UsagePage.SelectedValueIndex = 0) then
      begin
        SaveStringToFile(ExpandConstant('{app}\{#launcherNameMain32}.l4j.ini'), ExpandConstant('-Djava.library.path="{app}\lib"'+ #13#10), True);
        SaveStringToFile(ExpandConstant('{app}\{#launcherNameMixed32}.l4j.ini'), ExpandConstant('-Djava.library.path="{app}\lib"'+ #13#10), True);
        SaveStringToFile(ExpandConstant('{app}\{#launcherNameViewer32}.l4j.ini'), ExpandConstant('-Djava.library.path="{app}\lib"'+ #13#10), True);
        SaveStringToFile(ExpandConstant('{app}\{#launcherNameMain32}.l4j.ini'), regLine, True);
        SaveStringToFile(ExpandConstant('{app}\{#launcherNameMixed32}.l4j.ini'), regLine + #13#10 + '-m', True);
        SaveStringToFile(ExpandConstant('{app}\{#launcherNameViewer32}.l4j.ini'), regLine + #13#10 + '-v', True);
        DeleteFile(ExpandConstant('{app}\{#launcherNameMain}.l4j.ini'));
        DeleteFile(ExpandConstant('{app}\{#launcherNameMixed}.l4j.ini'));
        DeleteFile(ExpandConstant('{app}\{#launcherNameViewer}.l4j.ini'));
        DeleteFile(ExpandConstant('{app}\{#launcherNamePamDog}.l4j.ini'));
        DeleteFile(ExpandConstant('{app}\{#launcherNameMain}.exe'));
        DeleteFile(ExpandConstant('{app}\{#launcherNameMixed}.exe'));
        DeleteFile(ExpandConstant('{app}\{#launcherNameViewer}.exe'));
        DeleteFile(ExpandConstant('{app}\{#launcherNameDecimus}.l4j.ini'));
        DeleteFile(ExpandConstant('{app}\{#launcherNameDecimus}.exe'));
        RegWriteStringValue(HKCR, '.psf', '', '{#MyAppName}Settings');
        RegWriteStringValue(HKCR, '{#MyAppName}Settings','', 'Pamguard Settings File');
        RegWriteStringValue(HKCR, '{#MyAppName}Settings\DefaultIcon', '', ExpandConstant('{app}\{#launcherNameMain32}.exe,0'));
        RegWriteStringValue(HKCR, '{#MyAppName}Settings\shell\open\command', '', ExpandConstant('"{app}\{#launcherNameMain32}.exe" "-psf" "%1"'));
        // if this is the beta branch (version 2.xx.xx) then also register .psfx files
        if ({#majorVersion} > 1) then
          begin
            RegWriteStringValue(HKCR, '.psfx', '', '{#MyAppName}Settings');
          end;
        // if we are bundling the JRE, get rid of the 64 bit folder
        if ({#bundleJRE} > 0) then
          begin
            DelTree(ExpandConstant('{app}\jre64'), True, True, True);
          end;

        // if we don't want the decimus option, then delete the ini and exe files
        if ({#decimus} = 0) then
          begin
            DeleteFile(ExpandConstant('{app}\{#launcherNameDecimus32}.l4j.ini'));
            DeleteFile(ExpandConstant('{app}\{#launcherNameDecimus32}.exe'));
            DeleteFile(ExpandConstant('{app}\buoynet.exe'));
            DeleteFile(ExpandConstant('{app}\buoyNetConfiguration.xml'));
            DeleteFile(ExpandConstant('{app}\libgcc_s_dw2-1.dll'));
            DeleteFile(ExpandConstant('{app}\libstdc++-6.dll'));
            DeleteFile(ExpandConstant('{app}\NetworkMonitor.exe'));
            DeleteFile(ExpandConstant('{app}\NetworkMonitor.jar'));
          end

        // if we want the decimus option, make the changes required
        else
          begin
            SaveStringToFile(ExpandConstant('{app}\{#launcherNameDecimus32}.l4j.ini'), ExpandConstant('-Djava.library.path="{app}\lib"'+ #13#10), True);
            SaveStringToFile(ExpandConstant('{app}\{#launcherNameDecimus32}.l4j.ini'), regLine + #13#10 + '-nr -decimus', True);
          end;

      end
    else
      begin
        SaveStringToFile(ExpandConstant('{app}\{#launcherNameMain}.l4j.ini'), ExpandConstant('-Djava.library.path="{app}\lib64"'+ #13#10), True);
        SaveStringToFile(ExpandConstant('{app}\{#launcherNameMixed}.l4j.ini'), ExpandConstant('-Djava.library.path="{app}\lib64"'+ #13#10), True);
        SaveStringToFile(ExpandConstant('{app}\{#launcherNameViewer}.l4j.ini'), ExpandConstant('-Djava.library.path="{app}\lib64"'+ #13#10), True);
        SaveStringToFile(ExpandConstant('{app}\{#launcherNameMain}.l4j.ini'), regLine, True);
        SaveStringToFile(ExpandConstant('{app}\{#launcherNameMixed}.l4j.ini'), regLine + #13#10 + '-m', True);
        SaveStringToFile(ExpandConstant('{app}\{#launcherNameViewer}.l4j.ini'), regLine + #13#10 + '-v', True);
        SaveStringToFile(ExpandConstant('{app}\{#launcherNamePamDog}.l4j.ini'), regLineDog, True);
        DeleteFile(ExpandConstant('{app}\{#launcherNameMain32}.l4j.ini'));
        DeleteFile(ExpandConstant('{app}\{#launcherNameMixed32}.l4j.ini'));
        DeleteFile(ExpandConstant('{app}\{#launcherNameViewer32}.l4j.ini'));
        DeleteFile(ExpandConstant('{app}\{#launcherNameDecimus32}.l4j.ini'));
        DeleteFile(ExpandConstant('{app}\{#launcherNameMain32}.exe'));
        DeleteFile(ExpandConstant('{app}\{#launcherNameMixed32}.exe'));
        DeleteFile(ExpandConstant('{app}\{#launcherNameViewer32}.exe'));
        DeleteFile(ExpandConstant('{app}\{#launcherNameDecimus32}.exe'));
        RegWriteStringValue(HKCR, '.psf', '', '{#MyAppName}Settings');
        RegWriteStringValue(HKCR, '{#MyAppName}Settings','', 'Pamguard Settings File');
        RegWriteStringValue(HKCR, '{#MyAppName}Settings\DefaultIcon', '', ExpandConstant('{app}\{#launcherNameMain}.exe,0'));
        RegWriteStringValue(HKCR, '{#MyAppName}Settings\shell\open\command', '', ExpandConstant('"{app}\{#launcherNameMain}.exe" "-psf" "%1"'));
        RegWriteStringValue(HKCR, '.sqlite3', '', '{#MyAppName}Viewer');
        RegWriteStringValue(HKCR, '{#MyAppName}Viewer\DefaultIcon', '', ExpandConstant('{app}\{#launcherNameViewer}.exe,0'));
        RegWriteStringValue(HKCR, '{#MyAppName}Viewer\shell\open\command', '', ExpandConstant('"{app}\{#launcherNameViewer}.exe" "-databasefile" "%1"'));
        // if this is the beta branch (version 2.xx.xx) then also register .psfx files
        if ({#majorVersion} > 1) then
          begin
            RegWriteStringValue(HKCR, '.psfx', '', '{#MyAppName}Settings');
            RegWriteStringValue(HKCR, '.sqlite3', '', '{#MyAppName}Viewer');
          end;
        // if we are bundling the JRE, get rid of the 32 bit folder
        if ({#bundleJRE} > 0) then
          begin
            DelTree(ExpandConstant('{app}\jre32'), True, True, True);
          end;

        // if we don't want the decimus option, then delete the ini and exe files
        if ({#decimus} = 0) then
          begin
            DeleteFile(ExpandConstant('{app}\{#launcherNameDecimus}.l4j.ini'));
            DeleteFile(ExpandConstant('{app}\{#launcherNameDecimus}.exe'));
            DeleteFile(ExpandConstant('{app}\buoynet.exe'));
            DeleteFile(ExpandConstant('{app}\buoyNetConfiguration.xml'));
            DeleteFile(ExpandConstant('{app}\libgcc_s_dw2-1.dll'));
            DeleteFile(ExpandConstant('{app}\libstdc++-6.dll'));
            DeleteFile(ExpandConstant('{app}\NetworkMonitor.exe'));
            DeleteFile(ExpandConstant('{app}\NetworkMonitor.jar'));
          end

        // if we want the decimus option, make the changes required
        else
          begin
            SaveStringToFile(ExpandConstant('{app}\{#launcherNameDecimus}.l4j.ini'), ExpandConstant('-Djava.library.path="{app}\lib64"'+ #13#10), True);
            SaveStringToFile(ExpandConstant('{app}\{#launcherNameDecimus}.l4j.ini'), regLine + #13#10 + '-nr -decimus', True);
          end;

      end;
  except
    result := false;
  end;
  DelTree(ExpandConstant('{app}\L4J*.xml'), False, True, False);
end;


// change the ini files after the installation has finished
procedure CurStepChanged(CurStep: TSetupStep);
begin
  if  CurStep=ssPostInstall then
    begin
      updateINI();
    end;
end;


// used in the [Icons] section to specify the correct program name to use, based on 32/64 bit
// UsagePage=0 means 32bit, UsagePage=1 means 64bit
function WhichNameToUse(Param: String): String;
begin
    if (UsagePage.SelectedValueIndex = 0) then
      begin
//        Result := 'Pamguard32'
        Result := ExpandConstant('{#launcherNameMain32}')
      end
    else
      begin
//        Result := 'Pamguard64'
        Result := ExpandConstant('{#launcherNameMain}')
      end;
end;

// used in the [Icons] section to specify the correct main executable to use, based on 32/64 bit
// UsagePage=0 means 32bit, UsagePage=1 means 64bit
function WhichMainExecToUse(Param: String): String;
begin
    if (UsagePage.SelectedValueIndex = 0) then
      begin
        Result := ExpandConstant('{app}\{#launcherNameMain32}.exe')
      end
    else
      begin
        Result := ExpandConstant('{app}\{#launcherNameMain}.exe')
      end;
end;

// used in the [Icons] section to specify the correct viewer executable to use, based on 32/64 bit
// UsagePage=0 means 32bit, UsagePage=1 means 64bit
function WhichViewerExecToUse(Param: String): String;
begin
    if (UsagePage.SelectedValueIndex = 0) then
      begin
        Result := ExpandConstant('{app}\{#launcherNameViewer32}.exe')
      end
    else
      begin
        Result := ExpandConstant('{app}\{#launcherNameViewer}.exe')
      end;
end;

// used in the [Icons] section to specify the correct mixed executable to use, based on 32/64 bit
// UsagePage=0 means 32bit, UsagePage=1 means 64bit
function WhichMixedExecToUse(Param: String): String;
begin
    if (UsagePage.SelectedValueIndex = 0) then
      begin
        Result := ExpandConstant('{app}\{#launcherNameMixed32}.exe')
      end
    else
      begin
        Result := ExpandConstant('{app}\{#launcherNameMixed}.exe')
      end;
end;

// used in the [Icons] section to specify the correct decimus executable to use, based on 32/64 bit
// UsagePage=0 means 32bit, UsagePage=1 means 64bit
function WhichDecimusExecToUse(Param: String): String;
begin
    if (UsagePage.SelectedValueIndex = 0) then
      begin
        Result := ExpandConstant('{app}\{#launcherNameDecimus32}.exe')
      end
    else
      begin
        Result := ExpandConstant('{app}\{#launcherNameDecimus}.exe')
      end;
end;

// used in the [Icons] section to specify the correct NetworkMonitor executable to use
function WhichNMExecToUse(Param: String): String;
begin
    Result := ExpandConstant('{app}\NetworkMonitor.exe')
end;

// create the custom 32/64bit selection page, and show it after the license
procedure InitializeWizard;
begin
  { Create the 32/64bit option page }
    UsagePage := CreateInputOptionPage(wpLicense,
    'Installation Type', 'Please select the type of Installation',
    'Please specify the installation type, then click Next.',
    True, False);
  UsagePage.Add('32 bit version');
  UsagePage.Add('64 bit version');

  case GetPreviousData('UsageMode', '') of
    '32': UsagePage.SelectedValueIndex := 0;
    '64': UsagePage.SelectedValueIndex := 1;
  else
    UsagePage.SelectedValueIndex := 1;
  end;

  { second page, to allow user to input command line parameters }
  CmdLineParamsPage := CreateInputQueryPage(wpSelectDir,
  'Command Line Parameters - Optional', 'PAMGuard Command Line Parameters',
  'Please enter any command line parameters to use when starting PAMGuard.  ' +
  'Parameters should start with a dash ("-") character.  Multiple ' +
  'parameters should be separated by a space.  ' + #13#10 + #13#10 + 'Parameters are typically used ' +
  'to run bespoke modules for specific projects.  If you don''t know any parameters ' +
  'or don''t need any for the current application, simply leave this blank.');

  // Add items (False means it's not a password edit)
  CmdLineParamsPage.Add('Parameters:', False);

end;


// save previous data
procedure RegisterPreviousData(PreviousDataKey: Integer);
var
  UsageMode: String;
begin
  { Store the settings so we can restore them next time }
  case UsagePage.SelectedValueIndex of
    0: UsageMode := '32';
    1: UsageMode := '64';
  end;
  SetPreviousData(PreviousDataKey, 'UsageMode', UsageMode);
end;


// skip the 32/64bit selection page if windows is only 32 bit, or if we're installing Java12 (which is only 64 bit)
function ShouldSkipPage(PageID: Integer): Boolean;
begin
  if (PageID = UsagePage.ID) then
    begin
    
    { Skip the 32/64bit selection if this isn't 64bit windows }
    if (not IsWin64) then
      begin
        Result := True
        UsagePage.SelectedValueIndex := 0;
      end
    { Skip the 32/64bit selection if this is Java12) }
    else if ({#bundleJRE} = 2) then
      begin
        Result := True
        UsagePage.SelectedValueIndex := 1;
      end
    else
      begin
        Result := False
      end;
    end;
end;


// Run procedure when page changes
procedure CurPageChanged(CurPageID: Integer);
begin
  // update the default install path depending on whether the user has selected 32 or 64 bit
  if (CurPageID = wpSelectDir) then begin
      if (UsagePage.SelectedValueIndex = 0) then
        // if this is a 32 bit installation, add 32 to the end of the folder name to distinguish from 64 bit installations
        begin
          WizardForm.DirEdit.Text := ExpandConstant('{pf32}') + '\{#MyAppName}32';
        end
      else
        begin
          // if this is Java 12, don't bother adding the 64 to the folder name
          if ({#bundleJRE} = 2) then
            begin
              WizardForm.DirEdit.Text := ExpandConstant('{pf64}') + '\{#MyAppName}';
            end
          // if this is not Java 12, add 64 to the end of the folder name to distinguish from 32 bit installations
          else
            begin
              WizardForm.DirEdit.Text := ExpandConstant('{pf64}') + '\{#MyAppName}64';
            end
        end;
  end;
end;


// Both DecodeVersion and CompareVersion functions where taken from the  wiki
procedure DecodeVersion (verstr: String; var verint: array of Integer);
var
  i,p: Integer; s: string;
begin
  // initialize array
  verint := [0,0,0,0];
  i := 0;
  while ((Length(verstr) > 0) and (i < 4)) do
  begin
    p := pos ('.', verstr);
    if p > 0 then
    begin
      if p = 1 then s:= '0' else s:= Copy (verstr, 1, p - 1);
      verint[i] := StrToInt(s);
      i := i + 1;
      verstr := Copy (verstr, p+1, Length(verstr));
    end
    else
    begin
      verint[i] := StrToInt (verstr);
      verstr := '';
    end;
  end;

end;

function CompareVersion (ver1, ver2: String) : Integer;
var
  verint1, verint2: array of Integer;
  i: integer;
begin

  SetArrayLength (verint1, 4);
  DecodeVersion (ver1, verint1);

  SetArrayLength (verint2, 4);
  DecodeVersion (ver2, verint2);

  Result := 0; i := 0;
  while ((Result = 0) and ( i < 4 )) do
  begin
    if verint1[i] > verint2[i] then
      Result := 1
    else
      if verint1[i] < verint2[i] then
        Result := -1
      else
        Result := 0;
    i := i + 1;
  end;

end;

// Code to check for version of java, and bring user to java download page if not adequate.  Since we're using
// the ISSI code for the splash screen, we can't use the InitializeSetup function here (because ISSI uses it).
// Instead, we call our function ISSI_InitializeSetup, make sure we #define ISSI_UseMyInitializeSetup in the
// beginning of this script, and #include _issi.isi at the end of the [Code] section
function ISSI_InitializeSetup(): Boolean;
var
  ErrorCode: Integer;
  JavaVer : String;
  Result1 : Boolean;
  regRoot : Integer;
  begin

    // only run this check if we aren't bundling the JRE with the installer
    if ({#bundleJRE} = 0) then
    begin

      // set the registry root location based on 32 or 64 bit machine
      regRoot := HKLM
      begin
        if IsWin64 then
        begin
          regRoot := HKLM64
        end;
      end;

      // check the registry for the version of JRE currently installed
      Result := false;
      RegQueryStringValue(regRoot, 'SOFTWARE\JavaSoft\Java Runtime Environment', 'CurrentVersion', JavaVer);
      //MsgBox('Java Version Found: ' + JavaVer, mbInformation, MB_OK)
      if Length( JavaVer ) > 0 then
      begin
        if CompareVersion(JavaVer,'{#javaMinVersion}') >= 0 then
        begin
            Result := true;
        end;
      end; 
      
      // if the JRE is missing or the version is too old, check if there is a newer JDK
      if Result = false then
      begin
        RegQueryStringValue(regRoot, 'SOFTWARE\JavaSoft\Java Development Kit', 'CurrentVersion', JavaVer);
        //MsgBox('Java Version Found: ' + JavaVer, mbInformation, MB_OK)
        if Length( JavaVer ) > 0 then
        begin
            if CompareVersion(JavaVer,'{#javaMinVersion}') >= 0 then
            begin
                Result := true;
            end;
        end;
      end;

       // if we don't find java in the normal registry locations, check the special case
      // of 32bit java JRE installed on a 64bit machine
      if Result = false then
      begin
        RegQueryStringValue(HKLM, 'SOFTWARE\Wow6432Node\JavaSoft\Java Runtime Environment', 'CurrentVersion', JavaVer);
        //MsgBox('Java Version Found: ' + JavaVer, mbInformation, MB_OK)
        if Length( JavaVer ) > 0 then
        begin
            if CompareVersion(JavaVer,'{#javaMinVersion}') >= 0 then
            begin
                Result := true;
            end;
        end;
      end;
        
      // if Java wasn't found, warn the user and offer to go to the website to download
      if Result = false then
      begin
          Result1 := MsgBox('PAMGuard requires Java Runtime Environment version {#javaMinVersion} or newer to run. ' + 
          'Please download and install the correct JRE before trying to run PAMGuard.  ' +  #13 + #10 + #13 + #10 +
          'NOTE: Make sure to download the correct java version (32-bit or 64-bit) to match ' +
          'the version of PAMGuard you will be installing.  If you are installing 64-bit PAMGuard, ' +
          'click on the Java webpage link that specifically states 64-bit.  ' + #13 + #10 + #13 + #10 +
          'If you''ve installed java but the Java website pops up whenever you try to start PAMGuard, ' +
          'you have probably installed the wrong version.' + #13 + #10 + #13 + #10 +
           'Do you want to download Java now?',
            mbConfirmation, MB_YESNO) = idYes;
          if Result1 = true then
          begin
              ShellExec('open',
                'http://www.java.com/en/download/manual.jsp#win',
                '','',SW_SHOWNORMAL,ewNoWait,ErrorCode);
          end;
          Result := true; // let the user continue with the installation, whether they installed java or not
      end;
   end;
end;

[/Code]
#include ISSI_IncludePath+"\_issi.isi"