# Import sys to read from std-in, re for regular expression parsing
import sys, re

wordFreq = {}
for line in sys.stdin:  # Read line by line from std-in
    cleanLine = re.sub(r'[^\w\s]', '', line)  # Clean up the line
    wordList = cleanLine.split()  # Split each word separated by space; place each word in the words list
    for word in wordList:  # Go through the word list to look at each word
        token = word.lower()
        if token not in wordFreq:
            wordFreq[token] = 1
        else:
            wordFreq[token] += 1

for key, value in sorted(wordFreq.items(), key=lambda y:y[1], reverse=True):
    print("%s:%s" % (key, value))