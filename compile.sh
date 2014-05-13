#!/bin/sh

inputFile=$1

echo "Compile for $inputFile...\n"

#create GameDesigner.java
cd PLTFrontEnd
python traverse.py ../$inputFile > ../warning.log
cp GameDesigner.java ../runnable/GameDesigner.java
echo "GameDesigner.java Done. \n"
#make
cd ../runnable
make clean
make