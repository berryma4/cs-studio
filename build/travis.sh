#!/bin/bash

folderChangeSet={`git diff-tree --name-status HEAD`}
for((i=0; i<${#folderChangeSet[@]}; i++))
do
  if [ ${folderChangeSet[$i]} == "M" ]; then
    project=${folderChangeSet[$i+1]}
    print $project
  fi
done
