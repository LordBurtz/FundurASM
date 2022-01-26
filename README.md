# FundurASM - A Assembly-like language interpreter
## This interpreter was written by [LordBurtz](https://github.com/Lordburtz)
#### Licensed under GPLv2, all rights reserved

## Running it
(Sadly no .jar release atm, soon™)  
Clone this project via git (or download it directly)
```shell
git clone https://github.com/LordBurtz/FundurASM.git

```  
Open it in your favourite IDE or whatever  
Specify your filepath in the `Main.java`.
Enjoy your code being compiled!

## Changes made
| Instruction | change |
| --- | ---|
| Alloc | Brand new command, it allocates space in the register. You know have to allocate space in your registry. The interpreter checks whether you are out of bounds or not. |
| End | New syntax: `END x` where `x` is a number referring to a cell in your registry. The content of that cell is the return code of the dispatched process. |

## The intention
This interpreter is written to help students in germany who use assembly-like code to get that code interpreted.
Please use with caution, at the moment only absolute filepaths are supported.