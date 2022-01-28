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
Enjoy your code being interpreted!

## Changes made
All interpeter settings such as `alloc` have to have `#` prepended, otherwise the compiler won't read them
| Instruction | change |
| --- | ---|
| Alloc `<i>`| Brand new command, it allocates `i` spaces in the register. You now have to allocate space in your registry. The interpreter checks whether you are out of bounds or not. |
| Include `<lib>`| The library `lib` gets loaded and its method registered. After loading `math` you can call `math.mod` now.| 
| End `<x>`| New syntax: `END x` where `x` is a number referring to a cell in your registry. The content of that cell is the return code of the dispatched process. |

## Changes
| Version | changes |
| --- | ---|
| Alpha-0-1 | Initial version with some bugs (eg. jumps sometimes failing). All the standard instructions added|
| Alpha-0-2 | Rewrite of the instruction loading system allowing for more flexibility. Jumps work properly. Custom instructions can be registered now |
| Alpha-0-3 | Utilizing the flexibility introduced in the last update, now custom libraries can be added and loaded. Considering switching into beta |

## The intention
This interpreter is written to help students in germany who use assembly-like code to get that code interpreted.
Please use with caution, at the moment only absolute filepaths are supported.  
<details>
  <summary></summary>
    Any% glitchless speedrun, interpreter & parser finished @2.29h
</details>