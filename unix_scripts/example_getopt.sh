#!/bin/bash

# display the help:
displayHelp () 
{
    echo "Usage: $0 [option...] {start|stop|restart}" >&2
    echo
    echo "   -r, --resolution           run with the given resolution WxH"
    echo "   -d, --display              Set on which display to host on "
    echo
    # echo some stuff here for the -a or --add-options 
    exit 1
} 


while getopts ":a:b::h" opt; do
  case $opt in
    a)
      echo "-a was triggered, Parameter: $OPTARG" >&2
      ;;
    b)
      echo "-b was triggered with optional Parameter: $OPTARG" >&2
      ;;
    h)
      displayHelp
      exit 0
      ;;
    \?)   
      echo "Invalid option: -$OPTARG" >&2
      exit 1
      ;;
    :)
      echo "Option -$OPTARG requires an argument." >&2
      exit 1
      ;;
  esac
done 

