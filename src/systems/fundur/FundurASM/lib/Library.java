package systems.fundur.FundurASM.lib;

import systems.fundur.FundurASM.lib.base.Instruction;

import java.util.Map;

public abstract class Library {
    protected String identifier;
    protected Map<String, Instruction> instructions;

    public boolean contains(String instr) {
        return instructions.containsKey(instr);
    }

    public String getIdentifier() {
        return identifier;
    }

    public Instruction getInstruction(String instr) {
        return instructions.get(instr);
    }

    protected void register(String key, Instruction instruction) {
        this.instructions.put(key, instruction);
    };
}
