# DaylightLowTides

Daylight Low Tides for Given Locations

I wrote this code as part of a coding interview. When I accepted the project, I was traveling without my computer. So, on borrowed computers, I set up a portable IDE and did the project.

The actual assignment is:

Write a simple web scraper to help us visit the tide pools. Use any language and/or tools that you like.

Go to https://www.tide-forecast.com/ to get tide forecasts for these locations:

*	Half Moon Bay, California
*	Huntington Beach, California
*	Providence, Rhode Island
*	Wrightsville Beach, North Carolina
Load the tide forecast page for each location and extract information on low tides that occur after  sunrise and before sunset. Return the time and height for each daylight low tide.

I used Java with the jsoup library to aid in scrapping the web data. I used the BlueJ IDE. Having just used  the IDE to teach a class on Java Programming I knew how to quickly set up a portable version of BlueJ.  Also, I had not used jsoup with BlueJ, so I thought I would learn something new as well.

The project currently directs output to the console. As such it needs to run in a console. I have created a batch file to do this. To execute the project, run the file daylightLowTides.bat. Just double clicking on the file should work. The batch file will run the executable JAR file inside a console. The executable JAR file does require the Java Runtime Environment (JRE) to be installed on the computer. Most computers have this installed. However, if needed, the JRE can be installed from the website https://java.com/en/download/.

