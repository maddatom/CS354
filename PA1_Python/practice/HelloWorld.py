# Need to import sys in order to read from std-in
import re
import sys

# MSICS.
'''print("hello, world!")  # How come the world never responds?
print(sys.argv)  # Print the entire argv array
print(sys.argv[0])  # Get the first command line argument. Python makes it the name of the program/script
# so user input starts at argv[1]
print(sys.argv.__sizeof__())  # Initially wanted to get number of elements in argv. This is NOT it
print(len(sys.argv))  # Get the number of elements in a list/array
'''
# String manipulation & Dictionary
# Stand input
'''print("******** STRING MANIPULATION ********")
WORD = word.upper()  # GO ALL CAPS
print(word)
print(WORD)
print(word[0:5])  # Substring
print("******** STRING MANIPULATION ********")
'''
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