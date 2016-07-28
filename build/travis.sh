#!/bin/bash
set -e

function doCompile {
  mvn clean verify -fcore/pom.xml -Declipse.p2.mirrors=false -Dtycho.localArtifacts=ignore
  mvn clean verify -fapplications/pom.xml -Declipse.p2.mirrors=false -Dtycho.localArtifacts=ignore -Dcsstudio.composite.repo=core/p2repo
}

function doCompileWithDeploy {
  mvn clean verify -fcore/pom.xml -Declipse.p2.mirrors=false -Dtycho.localArtifacts=ignore -PuploadRepo
  mvn clean verify -fapplications/pom.xml -Declipse.p2.mirrors=false -Dtycho.localArtifacts=ignore -Dcsstudio.composite.repo=core/p2repo -PuploadRepo
}

function catTests {
  find ./ -type d -name "surefire-reports" -print0 | xargs -0 -I {} find {} -iname "*.txt" -type f | xargs cat
}

REPO=`git config remote.origin.url`
SSH_REPO=${REPO/https:\/\/github.com\//git@github.com:}
SHA=`git rev-parse --verify HEAD`

echo $REPO
echo $SSH_REPO
echo $SHA

# Pull requests and commits to other branches shouldn't try to deploy, just build to verify
if [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$SSH_REPO" == "ControlSystemStudio/cs-studio" ] ([[ "$TRAVIS_BRANCH" =~ ^[0-9]+\.[0-9]+\.x ]] || [ "$TRAVIS_BRANCH" == "master" ]); then
    echo "Deploying"
    doCompileWithDeploy
    catTests
else
    echo "Skipping deploy; just doing a build."
    doCompile
    catTests
fi

exit 0
