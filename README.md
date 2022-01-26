# FundurASM - A Assembly-like language interpreter
## This interpreter was written by [LordBurtz](https://github.com/Lordburtz)
#### Licensed under GPLv2, all rights reserved

## The intention
This interpreter is written to help students in germany who use assembly-like code to get that code interpreted.
Please use with caution, at the moment only absolute filepaths are supported.

## Changes made
| Instruction | change |
| --- | ---|
| Alloc | Brand new command, it allocates space in the register. You know have to allocate space in your registry. 
The interpreter checks whether you are out of bounds or not. |
| End | New syntax: `END x` where `x` is a number referring to a cell in your registry. The content of that cell is returned. |