#!/bin/bash

regex="(https:\/\/|git@)github.com(\/|:)(.+)\/(.+).git"

remote="origin"
result="https"

if [ ! -z "$1" ]; then
    if [ "$1" == "https" -o "$1" == "ssh" ]; then
        result="$1"
    else
        remote="$1"
    fi
fi

if [ ! -z "$2" -a "$2" == "https" -o "$2" == "ssh" ]; then
    result="$2"
fi

remote_url="$(git remote get-url $remote)"

if [[ $remote_url =~ $regex ]]; then
    user="${BASH_REMATCH[3]}"
    repo="${BASH_REMATCH[4]}"
    
    if [ "$result" == "https" ]; then
        url="https://github.com/$user/$repo.git"
    elif [ "$result" == "ssh" ]; then
        url="git@github.com:$user/$repo.git"
    else
        echo "Unkown Result, $result, specified. Should be https or ssh."
        exit 1
    fi
    
    git remote set-url origin "$url"
    echo "Remote, $remote, changed to $result: $(git remote get-url $remote)"
else
    echo "Remote, $remote, URL doesn't match pattern: $remote_url"
fi
