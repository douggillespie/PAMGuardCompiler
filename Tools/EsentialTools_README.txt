Several tools are required for the Compiler to run and these are not all included in the GIT repository. 
You will have to download these yourself and add them to the Tools folder. 

It's possible that later versions of some of this software may work, but we've not tested them, so 
recommend that you use the same versions that we've used wherever possible. 

apache-ant-1.8.2
Download the archive apache-ant-1.8.2-bin.tar.gz from https://ant.apache.org/bindownload.cgi
and put the unpacked files into \Tools\apache-ant-1.8.2

Inno_Setup_5
There is no need to install Inno, but the compiler calls it's runtime which is 
needed from \Tools\Inno_Setup_5\ISCC.exe. We've been using version 5.5.8 or 5.5.9 available at
https://files.jrsoftware.org/is/5/. You'll probably have to run the installer, then copy the files 
from c:/Program Files (x86) into the tools folder. 

jdk1.6.0_30
The ANT build needs JDK 6 and won't work with later versions.  You should be able to find it 
at https://www.oracle.com/uk/java/technologies/javase-java-archive-javase6-downloads.html. 
Install it to Tools/jdk1.6.0_30

JRE12Plus
This folder must contain a built java runtime which will be packaged into the installer. Instructions
for building a jre are in "Creating a bespoke JRE.docx"

launch4jForPamguard. I'm not sure what we did to this, so have included the entire thing for now. 

