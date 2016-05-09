# phoneticalmatch
A program that matches phonetically equivalent words from the STDIN and a text file.

## Installation
I understand that you are using Linux or Mac and you have installed on your machine these programs:

* GIT
* Java Environment
* Maven

So first of all clone this repository to your computer:
```
$ git clone https://github.com/lassounski/phoneticalmatch.git
```
Next, access the folder and issue a Maven command to build the project:
```
$ cd phoneticalmatch
$ mvn install
```
Last but not least, execute the program with Java through Maven(beware of the version number that needs to be filled correctly):
```
$ mvn exec:java -Dexec.args="word1 word2 word3 ./path_to/dictionary.txt"
```
The parameters passed to the program are the words that will be matched to the dictionary. Pay attention to the last parameter passed, it is the path to the dictionary, it can be relative to the folder where the program is being run or an absoulte path. The dictionary file MUST be a .txt file.

To run an example program you could run:
```
$ mvn exec:java -Dexec.args="1ton# brief soon word_dict.txt"
```
