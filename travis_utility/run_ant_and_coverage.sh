#!/bin/bash

ant test && travis_utility/run_ant_and_coverage.sh
