# Import sys to read from std-in, re for regular expression parsing
import sys, re

if len(sys.argv) <=1:
  print("A file to parse must be specified")
  print("Usage: \n python wf.py <file>")
  sys.exit(1)

f = open(sys.argv[1], 'r')  # Open the file in read only mode
print("Statistics for " + sys.argv[1])
wordFreq = {}
for line in f:  # Read line by line from std-in
    cleanLine = re.sub(r'[^\w\s]', '', line)  # Clean up the line
    wordList = cleanLine.split()  # Split each word separated by space; place each word in the words list
    for word in wordList:  # Go through the word list to look at each word
        token = word.lower()
        if token not in wordFreq:
            wordFreq[token] = 1
        else:
            wordFreq[token] += 1
f.close()  # Close the file
# Print the stats to standard out
for key, value in sorted(wordFreq.items(), key=lambda y:y[1], reverse=True):
    print("%s %s" % (key, value))