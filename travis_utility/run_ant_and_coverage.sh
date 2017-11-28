#!/bin/bash

ant test && travis_utility/run_coverage.sh
