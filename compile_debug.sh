#!/bin/sh

inputFile=$1

echo "Compile for $inputFile...\n"

#create GameDesigner.java
cd PLTFrontEnd
python traverse.py ../$inputFile > ../warning.log
cp GameDesigner.java ../PLTImplementation/src/GameDesigner.java
echo "GameDesigner.java Done. \n"