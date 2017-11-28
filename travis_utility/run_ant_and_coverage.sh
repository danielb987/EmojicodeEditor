#!/bin/bash

ant test && push-javadoc-to-docs.sh && travis_utility/run_coverage.sh
