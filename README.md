# chuck-norris-cipher-encoder
A simple encoder that takes a string as input > converts to binary > then converts the binary string to chuck norris unary code > then back to binary > and to the original readable input string
###### Disclaimer: The string is converted to a 7 bit binary code (not 8)

### The program provides a simple user interface with 3 options
- Encode (encodes a string and prints the encoded chuck norris unary code)
- Decode (decodes a chuck norris unary code string to readable letters/numbers)
- Exit (exits the program)

### Example:

#### Assume we want to encode/decode the following string: `liviu`
---
- User chooses `Encode` option:
---
##### The program takes the string and converts it to binary string, in this case `liviu` => `11011001101001111011011010011110101`
##### Then the program starts encoding the binary string into the chuck norris unary code (it replaces 1s with 0s with certain patterns i.e. 11 becomes -> 0 0 or 0 00 or 0000 becomes 00 0000)
##### The conversion of the binary string goes like this for the `liviu` string:
- `11011001101001111011011010011110101` 
###### to
- `0 00 00 0 0 00 00 00 0 00 00 0 0 0 00 00 0 0000 00 0 0 00 00 0 0 00 00 0 0 0 00 00 0 0000 00 0 0 0 00 0 0 0`
---
- User chooses `Decode` option:
---
##### The program takes the chuck norris unary code string 
- `0 00 00 0 0 00 00 00 0 00 00 0 0 0 00 00 0 0000 00 0 0 00 00 0 0 00 00 0 0 0 00 00 0 0000 00 0 0 0 00 0 0 0`
##### Checks a couple of rules that the string must not break, like:
1. The encoded string provided as input must contain only `0` or `spaces`
2. The first block of each sequence must be either `0` or `00`
3. The number of blocks must be even
4. The length of the decoded binary string must be a multiple of 7
##### If any of these rules fails, the program will throw a message `not valid` and prompts the user to try again (choose any option again)
##### After the validation of rules passes, the program takes the chuck norris unary code and reverts it back to binary string (it replaces specific patterns like 0 0 with 1 or 00 0000 with 0000)
##### Then the new binary code that becomes like this after the conversion from chuck norris unary code `11011001101001111011011010011110101` is split into groups of 7
##### And finally after being split into groups of 7s `1101100 1101001 1110110 1101001 1110101` we convert each block to letters/numbers and eventually append each one of them to a readable string; i.e. `liviu` our initial string
---
