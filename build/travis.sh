#!/bin/bash

folderChangeSet={`git diff-tree --name-status HEAD`}
for i in "${folderChangeSet[@]}"
do
  if [ ${folderChangeSet[$i]} == "M" ]; then
    project=${folderChangeSet[$i+1]}
    print $project
  fi
done
