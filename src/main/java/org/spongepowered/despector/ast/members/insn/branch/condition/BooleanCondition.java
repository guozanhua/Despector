/*
 * The MIT License (MIT)
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.despector.ast.members.insn.branch.condition;

import static org.spongepowered.despector.util.TypeHelper.checkType;

import org.spongepowered.despector.ast.members.insn.InstructionVisitor;
import org.spongepowered.despector.ast.members.insn.arg.Instruction;

/**
 * A condition based off of a simple boolean value.
 */
public class BooleanCondition extends Condition {

    private Instruction value;
    private boolean inverse;

    public BooleanCondition(Instruction value, boolean inverse) {
        this.value = checkType(value, (t) -> "I".equals(t) || "Z".equals(t), "value");
        this.inverse = inverse;
    }

    /**
     * Gets the instruction for the value of this condition.
     */
    public Instruction getConditionValue() {
        return this.value;
    }

    /**
     * Sets the instruction for the value of this condition.
     */
    public void setConditionValue(Instruction insn) {
        this.value = checkType(insn, "Z", "value");
    }

    /**
     * Gets if this condition is marked as inverted.
     */
    public boolean isInverse() {
        return this.inverse;
    }

    /**
     * Sets if this condition is marked as inverted.
     */
    public void setInverse(boolean state) {
        this.inverse = state;
    }

    @Override
    public void accept(InstructionVisitor visitor) {
        visitor.visitBooleanCondition(this);
        this.value.accept(visitor);
    }

    @Override
    public String toString() {
        return (this.inverse ? "!" : "") + this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BooleanCondition)) {
            return false;
        }
        BooleanCondition and = (BooleanCondition) o;
        return this.value.equals(and.value) && this.inverse == and.inverse;
    }

}
