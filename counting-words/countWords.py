#!/usr/bin/python

import re
import sys
from collections import Counter

file_name = sys.argv[1]
words = re.findall(r'\b\w+\b', open(file_name).read().lower())
with open("./output.txt", 'w') as f:
    for k, v in Counter(words).most_common():
        f.write("{} {}\n".format(k, v))
