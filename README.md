# Covid Data Processor

Implementation of a program via DAO pattern in java with the capability of reading a CSV file (Covid dataset), saving values in objects and processing them to output results based on user arguments.
Find project report [here](https://drive.google.com/file/d/11g_pO8jAKlMfL8W7byl-GIBVfo7C8aDR/view?usp=sharing)

## Getting Started

### Dependencies

Java JDK 17 or newer.
Older versions might also work.

### Executing program

Submit as command line arguments:
```
-stat [min, max] -by [NC, NCS, ND, NDS, NT, NDPC] -limit [1, 100] -display [Date, Country, Continent] -file [filePath]
```
For testing this program the arguments were passed in:
```IntelliJ->Edit Configurations->Program Arguments/CLI Arguments```

### Sample Output
Note: Outputs may change based on new records of csv file <br />
```
07/08/2020
04/26/2020
08/11/2020
08/02/2020
05/01/2020
```
```
New Zealand
Estonia
Philippines
Ireland
Sao Tome and Principe
Belize
```

## Authors

Contributors names:

Erges Mema  

## Notes

Many fields of the csv resulted empty when reading the file, and some of them have been filtered out during processing of the data. The iso codes, continent names and location were not filtered since the csv introduces many distinct values that might not match the commonly known variables. Adhere that new values are always added and filtering them would result pointless.<br />
There are currently two formats of dates that are parsed. If new date formats are used in the csv in the future, they must be added to the list in utils/MemUtils.java in the respective methods.
As an example, output of -display Country might show: World, even though World doesn't qualify as a country, it is stated so in the csv column of 'location'.

## Version History

* 0.1
    * Initial Release


## Acknowledgments

Covid Dataset From:

Mathieu, E., Ritchie, H., Ortiz-Ospina, E. et al. A global database of COVID-19 vaccinations. Nat Hum Behav (2021). https://doi.org/10.1038/s41562-021-01122-8
