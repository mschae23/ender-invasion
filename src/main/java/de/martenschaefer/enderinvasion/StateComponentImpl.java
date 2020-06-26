package de.martenschaefer.enderinvasion;

import net.minecraft.nbt.CompoundTag;

public class StateComponentImpl implements StateComponent {

 private State value;

 public StateComponentImpl(State state) {

  this.value = state;
 }
 @Override
 public State value() {

  return this.value;
 }
 @Override
 public void setValue(State state) {

  this.value = state;
 }
 @Override
 public void fromTag(CompoundTag tag) {

  this.value = State.values()[tag.getInt("value")];
 }
 @Override
 public CompoundTag toTag(CompoundTag tag) {

  tag.putInt("value", value.ordinal());
  return tag;
 }
}
