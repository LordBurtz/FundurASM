# FundurASM - A Assembly-like language interpreter
## This interpreter was written by [LordBurtz](https://github.com/Lordburtz)
#### Licensed under GPLv2, all rights reserved

## Running it
Download the latest version from the [Release] tab.  
Interpret a certain file (e.g. `test.fasm`) by running:
```bash
java -jar asm.jar test.fasm
```
(asm.jar being the downloaded one)

## Changes made
All interpreter settings such as `alloc` have to have `#` prepended, otherwise the compiler won't read them

| Instruction     | change                                                                                                                                                                    |
|-----------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Alloc `<i>`     | Brand new command, it allocates `i` spaces in the register. You now have to allocate space in your registry. The interpreter checks whether you are out of bounds or not. |
| Include `<lib>` | The library `lib` gets loaded and its method registered. After loading `math` you can call `math.mod` now.                                                                | 
| End `<x>`       | New syntax: `END x` where `x` is a number referring to a cell in your registry. The content of that cell is the return code of the dispatched process.                    |

## Changes
| Version   | changes                                                                                                                                             |
|-----------|-----------------------------------------------------------------------------------------------------------------------------------------------------|
| Alpha-0-1 | Initial version with some bugs (eg. jumps sometimes failing). All the standard instructions added                                                   |
| Alpha-0-2 | Rewrite of the instruction loading system allowing for more flexibility. Jumps work properly. Custom instructions can be registered now             |
| Alpha-0-3 | Utilizing the flexibility introduced in the last update, now custom libraries can be added and loaded. Considering switching into beta              |
| Beta-1-0  | Hex und Binary Numbers supported! Switched to Beta, Refactoring and language updates.. added an argument parser to support multiple number formats. |
| Beta 1-1  | Arg parser put to use, now Hex and Binary can actually be used, Parser can now be instantiated!                                                     |
 | Beta 1-3  | First Pre-release, maven introduced. No language syntax changes.                                                                                    |

## The intention
This interpreter is written to help students in germany who use assembly-like code to get that code interpreted.

## Contributing
#### Feel free to contribute!
This project is intended to be easily extendable, therefore contribute your own libraries, tests, etc.
It is in no way finished and still in beta, changes will be made, but you can be part of them!
If you find something you'd like to work on, just fork this repo and open a pull request when you are done.
It'll be reviewed (and hopefully merged) in no time.  
Even if coding isn't your thing, you can help out by making this README better and/or by opening an issue 
about what still needs some work!  

<details>
  <summary></summary>
    Any% glitch-less speedrun, interpreter & parser finished @2.29h
</details>