#!/bin/bash

ant test && \
    travis_utility/push-javadoc-to-docs.sh && \
    travis_utility/run_coverage.sh
