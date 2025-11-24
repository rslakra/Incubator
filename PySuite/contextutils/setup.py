#!/usr/bin/env python
import sys

try:
    from setuptools import setup
except:
    print("Please install setuptools")
    sys.exit(1)

import contextutils

setup(
    name = "contextutils",
    description = "Context handling framework for Python",
    
    py_modules = ["contextvars"],
    #test_suite = "tests",

    version = contextutils.__version__,
    author = contextutils.__author__,
    author_email = contextutils.__email__,
    url = "https://github.com/rslakra/contextutils",
    license = contextutils.__license__,
    classifiers = [
        "Development Status :: 5 - Production/Stable",
        "License :: OSI Approved :: MIT License",
        "Operating System :: OS Independent",
        "Topic :: Software Development :: Libraries :: Application Frameworks",
    ],
)
