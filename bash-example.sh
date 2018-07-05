
basedir=$(dirname $0)
BIN_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
LCP_HOME="$(dirname "$BIN_DIR")"

solc Deliverables.sol --bin --abi --optimize -o ./
web3j solidity generate compiled_contract/Deliverables.sol:Deliverables.bin compiled_contract/Deliverables.sol:Deliverables.abi -o src/main/java -p com.yopiter
solc --gas 

#remove blank line 
sed -i '/^$/d' file.txt

for i in */; 
   do 
	zip -r "${i%/}.zip" "$i"; 
done


for i in */; 
   do 
	zip -r "../${i%/}" "$i"; 
done



for i in *.zip;
    do 
	mkdir "${i%/}_folder"
	echo unzip "${i%}" -d "${i%}"
	unzip "${i%}" -d "${i%/}_folder"
done


for i in *.zip;
    do 
	mkdir "folder/${i%/}"
	echo unzip "${i%}" -d "folder/${i%/}"
	unzip "${i%}" -d "folder/${i%/}"
done

#---------------Comman in both
comm -12 a.txt b.txt

#---------------unique in first
comm -23 a.txt b.txt


#-------------sort by date coming in filename
ls | grep 2018 | sort -r -t_ -k3,3  #-k3,3 - 3 is column no

#-------------------------------------ZIP GREP

for FILE in `ls 2017-May-31.*/*.zip`; do zipgrep "SYD " $FILE; done | grep done

#---------------------------------------------------------------------------------------------------------------------------------------

#sort content of table
#-n for numeric, k1 for column 1
#-u for merge 
sort -nk1 numbers.txt 
sort -nk1 -u numbers.txt number2.txt


#-------
grep -v "ayush" filename

#----
for i in {01..09} {20..22}; do ssh solr-$i du -sh ~/solr/data/ ; echo "SOLR-$i";echo ""; done


#read file
while read line; do echo -e "$line\n"; done < numbers.txt
while read p; do echo $p; done <peptides.txt
while read p; do
  echo $p
done <peptides.txt


#!/bin/sh
yn="Y"
while true; do
    case $yn in
        [Yy]* ) echo "Himashu "
		echo "Bhushan";;
        [Nn]* ) exit;;
        * ) echo "Please answer yes or no.";;
    esac
    read -p "Continue?" yn
done




#--------SED----------

#print partial test of a file
sed -n 5,12p text.txt


#remove a line from file
sed 5d test.txt


#remove the number of lines
sed '5-12d' test.txt


#remove the lines other than number specified
sed '5-12!d' test.txt


#replacing a word
sed 's/ayush/vasu' test.txt


#replacing the perticular occurrence of word
sed 's/ayush/dev/5' test.txt


#replaceing the word of perticuler line or line
sed '9 s///' test.txt
sed '9-12 s///' test.txt


#adding blank line after every line 
sed G test.txt

#removing the comment
sed -e 's/#.*//' test.txt
sed -e 's/^$/d' test.txt
